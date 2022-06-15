package cn.cocowwy.tinyframwork.spring.springframework.beans.factory.service;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class UserService {
    private String name;

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }

    public void doSth() {
        System.out.println(name + "hi");
    }


}
