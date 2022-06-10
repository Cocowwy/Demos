package cn.cocowwy.beautifulcode.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class ProxyDo implements MethodInterceptor {
    private final Object real;

    public ProxyDo(Object real) {
        this.real = real;
    }

    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        // 设置父类以及
        enhancer.setSuperclass(real.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before use cglib proxy..");
        return methodProxy.invoke(real, objects);
    }
}
