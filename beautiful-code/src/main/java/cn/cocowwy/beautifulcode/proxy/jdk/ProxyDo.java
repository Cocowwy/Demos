package cn.cocowwy.beautifulcode.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class ProxyDo implements InvocationHandler {

    private final Object real;

    public ProxyDo(Object real) {
        this.real = real;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("do proxy way..");
        // 调用真是对象的方法
        return method.invoke(real, args);
    }
}
