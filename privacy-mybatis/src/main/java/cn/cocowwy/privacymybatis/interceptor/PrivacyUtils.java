package cn.cocowwy.privacymybatis.interceptor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author cocowwy.cn
 * @since 2022/4/21
 */
public class PrivacyUtils {
    
    public static boolean isAnnotatedWithPrivacy(Field field) {
        return field.getAnnotation(Privacy.class) != null;
    }

    public static boolean isAnnotatedWithPrivacy(Object object) {
        return object.getClass().isAnnotationPresent(Privacy.class);
    }

    public static List<Field> getAllFields(Object object) {
        Class<?> clazz = object.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (null != clazz) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }

        return fieldList;
    }
}
