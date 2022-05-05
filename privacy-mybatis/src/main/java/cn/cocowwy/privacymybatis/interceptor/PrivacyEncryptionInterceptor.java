package cn.cocowwy.privacymybatis.interceptor;

import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author cocowwy.cn
 * @since 2022/4/21
 */
@Slf4j
@Component
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class PrivacyEncryptionInterceptor implements Interceptor {

    private static final String PARAM1 = "param1";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement statement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = invocation.getArgs()[1];
        SqlCommandType sqlCommandType = statement.getSqlCommandType();
        if (null == parameter) {
            return invocation.proceed();
        }

        if (PrivacyUtils.isAnnotatedWithPrivacy(parameter)) {
            if (SqlCommandType.INSERT == sqlCommandType) {
                encryptFields(parameter);
            } else if (SqlCommandType.UPDATE == sqlCommandType) {
                if (parameter instanceof MapperMethod.ParamMap) {
                    MapperMethod.ParamMap<?> paramMap = (MapperMethod.ParamMap<?>) parameter;
                    if (paramMap.containsKey(Constants.ENTITY)) {
                        parameter = paramMap.get(Constants.ENTITY);
                    } else {
                        parameter = paramMap.get(PARAM1);
                    }

                    if (null == parameter) {
                        return invocation.proceed();
                    }
                }
                encryptFields(parameter);
            }
        }

        return invocation.proceed();
    }

    private void encryptFields(Object parameter) {
        List<Field> fieldList = PrivacyUtils.getAllFields(parameter);

        fieldList.forEach(it -> {
            if (PrivacyUtils.isAnnotatedWithPrivacy(it)) {
                it.setAccessible(true);
                try {
                    Object value = it.get(parameter);
                    if (null != value) {
                        String valueString = value.toString();
                        if (StringUtils.isNotBlank(valueString)) {
                            it.set(parameter, AesBase64Utils.encrypt(valueString));
                        }
                    }
                } catch (IllegalAccessException ignored) {
                }
                it.setAccessible(false);
            }
        });
    }
}
