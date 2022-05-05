package cn.cocowwy.privacymybatis.interceptor;

import java.lang.annotation.*;

/**
 * 标记实体类有字段在持久化到数据库时需要进行加密，从数据库读取数据时进行解密。
 *
 * <pre>
 *     使用事项：
 *     1. 实体类和其中要进行加密的字段上都必须有该注解
 *     2. 数据库字段必须为字符串类型
 * </pre>
 *
 * @author cocowwy.cn
 * @since 2022/4/21
 */
@Documented
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Privacy {
}
