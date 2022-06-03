package cn.cocowwy.tinyframwork.mybatis.test;

import cn.cocowwy.tinyframwork.mybatis.binding.MapperProxyFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cocowwy.cn
 * @create 2022-06-06-12:46
 */
public class TestMain {
    public static void main(String[] args) {
        MapperProxyFactory<UserMapeer> factory = new MapperProxyFactory<>(UserMapeer.class);
        Map<String, String> sqlSession = new HashMap<>();

        UserMapeer userMapeer = factory.newInstance(sqlSession);
        System.out.println(userMapeer.queryUserNameByUerId(1L));
    }
}
