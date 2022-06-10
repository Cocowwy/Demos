package cn.cocowwy.beautifulcode.proxy;

import cn.cocowwy.beautifulcode.proxy.cglib.ProxyDo;
import cn.cocowwy.beautifulcode.proxy.jdk.RealDo;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class Test4Cglib {
    public static void main(String[] args) {
        // cglib
        RealDo realDo = new RealDo();
        ProxyDo proxyDo = new ProxyDo(realDo);
        RealDo proxy = (RealDo)proxyDo.getProxy();
        proxy.sayHi();
    }
}
