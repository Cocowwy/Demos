package cn.cocowwy.beautifulcode.proxy;

import cn.cocowwy.beautifulcode.proxy.jdk.ProxyDo;
import cn.cocowwy.beautifulcode.proxy.jdk.RealDo;
import cn.cocowwy.beautifulcode.proxy.jdk.Say;

import java.lang.reflect.Proxy;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class Test4Jdk {
    public static void main(String[] args) {
        // jdk
        RealDo realDo = new RealDo();
        ProxyDo proxyDo = new ProxyDo(realDo);
        Say o = (Say)Proxy.newProxyInstance(realDo.getClass().getClassLoader(), realDo.getClass().getInterfaces(), proxyDo);
        o.sayHi();
        realDo.sayHi();
    }
}
