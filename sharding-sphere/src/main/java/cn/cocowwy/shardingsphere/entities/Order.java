package cn.cocowwy.shardingsphere.entities;

/**
 * @author cocowwy.cn
 * @create 2022-01-01-18:12
 */
public class Order {
    private Long id;

    private String number;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
