package cn.cocowwy.tinyframwork.spring.springframework.beans.factory.beans;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class PropertyValue {
    private final String name;
    // BeanReference 则表示是bean引用
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
