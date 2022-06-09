package cn.cocowwy.beautifulcode.proxy.jdk;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class RealDo implements Say {

    public void eatFood() {
        System.out.println("eat food");
    }

    @Override
    public void sayHi() {
        System.out.println("hi ~");
    }
}
