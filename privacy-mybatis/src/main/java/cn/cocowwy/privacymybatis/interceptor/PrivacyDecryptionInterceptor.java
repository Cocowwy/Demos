package cn.cocowwy.privacymybatis.interceptor;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cocowwy.cn
 * @since 2022/4/21
 */
@Slf4j
@Component
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class,
                ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class,
                ResultHandler.class, CacheKey.class, BoundSql.class}),
})
@SuppressWarnings({"rawtypes", "unchecked"})
public class PrivacyDecryptionInterceptor implements Interceptor {

    private static void decryptFields(Object obj) {
        List<Field> fieldList = PrivacyUtils.getAllFields(obj);

        fieldList.forEach(it -> {
            if (PrivacyUtils.isAnnotatedWithPrivacy(it)) {
                it.setAccessible(true);
                try {
                    Object value = it.get(obj);
                    if (null != value) {
                        String valueString = value.toString();
                        if (StringUtils.isNotBlank(valueString)) {
                            it.set(obj, AesBase64Utils.decrypt(valueString));
                        }
                    }
                } catch (IllegalAccessException ignored) {
                }
                it.setAccessible(false);
            }
        });
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object result = invocation.proceed();

        if (result instanceof ArrayList) {
            ArrayList list = (ArrayList) result;
            if (list.size() == 0) {
                return result;
            }

            if (PrivacyUtils.isAnnotatedWithPrivacy(list.get(0))) {
                list.forEach(PrivacyDecryptionInterceptor::decryptFields);
                return result;
            }
        }

        if (PrivacyUtils.isAnnotatedWithPrivacy(result)) {
            decryptFields(result);
        }

        return result;
    }
}
