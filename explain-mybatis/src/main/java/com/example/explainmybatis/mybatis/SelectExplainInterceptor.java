package com.example.explainmybatis.mybatis;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * 在 dev 和 test 环境  打印sql未命中索引的日志
 *
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Slf4j
@Profile({"dev", "test"})
@Component
@Intercepts({@Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class, Integer.class})})
public class SelectExplainInterceptor implements Interceptor {

    private static final String CLASS_PREFIX = "com.xxxx";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        // 通过MetaObject优雅访问对象的属性，这里是访问statementHandler的属性
        // MetaObject是Mybatis提供的一个用于方便、优雅访问对象属性的对象，通过它可以简化代码、不需要try/catch各种reflect异常
        // 同时它支持对JavaBean、Collection、Map三种类型对象的操作。
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        // 先拦截到RoutingStatementHandler，里面有个StatementHandler类型的delegate变量
        // 其实现类是BaseStatementHandler，然后就到BaseStatementHandler的成员变量mappedStatement
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        // sql语句类型
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        BoundSql boundSql = statementHandler.getBoundSql();
        if (SqlCommandType.SELECT.equals(sqlCommandType)) {
            Object param = boundSql.getParameterObject();
            Connection connection = (Connection) invocation.getArgs()[0];
            String explainSql = "explain " + boundSql.getSql();
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            BoundSql explainBoundSql = new BoundSql(mappedStatement.getConfiguration(), explainSql, parameterMappings, param);
            ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, param, explainBoundSql);
            try (PreparedStatement ps = connection.prepareStatement(explainSql)) {
                parameterHandler.setParameters(ps);
                try (ResultSet rs = ps.executeQuery()) {
                    handleExplainResult(rs);
                }
            } catch (SQLException e) {
                log.error("执行explain语句时发生异常", e);
            }
        }
        log.info("full sql：{}", formatSql(boundSql, mappedStatement.getConfiguration()));

        return invocation.proceed();
    }

    private String formatSql(BoundSql boundSql, Configuration configuration) {
        String sql = boundSql.getSql();
        Object parameterObject = boundSql.getParameterObject();
        if (sql == null || sql.length() == 0 || configuration == null) {
            return "";
        }

        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        sql = sql.replaceAll("[\\s\n ]+", " ");
        /*
         * 参考Mybatis原生方法(org.apache.ibatis.scripting.defaults.DefaultParameterHandler)进行参数处理
         */
        if (parameterMappings != null) {
            for (ParameterMapping parameterMapping : parameterMappings) {
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else {
                        MetaObject metaObject = configuration.newMetaObject(parameterObject);
                        value = metaObject.getValue(propertyName);
                    }
                    String paramValueStr = "";
                    if (value instanceof String || value instanceof LocalDate || value instanceof LocalTime) {
                        paramValueStr = "'" + value + "'";
                    } else if (value instanceof LocalDateTime) {
                        LocalDateTime ldt = (LocalDateTime) value;
                        paramValueStr = "'" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(ldt) + "'";
                    } else {
                        paramValueStr = value + "";
                    }
                    sql = sql.replaceFirst("\\?", paramValueStr);
                }
            }
        }
        return sql;
    }

    private void handleExplainResult(ResultSet rs) throws SQLException {
        List<ExplainResult> results = new ArrayList<>();
        while (rs.next()) {
            ExplainResult er = mapToExplainResult(rs);
            results.add(er);
        }

        if (results.stream().anyMatch(it -> it.getKey() == null || MySQLExplainTypeEnum.ALL.getName().equalsIgnoreCase(it.getType()))) {
            List<StackTraceElement> stackTraces = Arrays.stream(Thread.currentThread().getStackTrace())
                    .filter(it -> it.getClassName().startsWith(CLASS_PREFIX))
                    .collect(toList());
            log.warn("存在未命中索引的查询语句，查看接下来的栈帧信息定位问题");
            results.forEach(it -> log.warn("explain result: {}", it));
            stackTraces.forEach(it -> log.warn("stack trace: {}", it));
        }
    }

    private ExplainResult mapToExplainResult(ResultSet rs) throws SQLException {
        ExplainResult er = new ExplainResult();
        er.setId(rs.getLong("id"));
        er.setSelectType(rs.getString("select_type"));
        er.setTable(rs.getString("table"));
        er.setPartitions(rs.getString("partitions"));
        er.setType(rs.getString("type"));
        er.setPossibleKeys(rs.getString("possible_keys"));
        er.setKey(rs.getString("key"));
        er.setKeyLen(rs.getString("key_len"));
        er.setRef(rs.getString("ref"));
        er.setRows(rs.getLong("rows"));
        er.setFiltered(rs.getDouble("filtered"));
        er.setExtra(rs.getString("Extra"));
        return er;
    }

    @Data
    static class ExplainResult {
        private Long id;
        private String selectType;
        private String table;
        private String partitions;
        private String type;
        private String possibleKeys;
        private String key;
        private String keyLen;
        private String ref;
        private Long rows;
        private Double filtered;
        private String extra;
    }
}
