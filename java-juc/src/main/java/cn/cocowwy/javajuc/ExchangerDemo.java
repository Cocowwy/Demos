package cn.cocowwy.javajuc;

import java.util.concurrent.Exchanger;

/**
 * Exchanger 就是线程之间的数据交换器，只能用于两个线程之间的数据交换
 * - 只带泛型 V（交换的数据对象）的方法，线程一直阻塞，直到其他任意线程和它交换数据
 * - 另外一个带时间的方法，如果超过设置时间还没有线程和它交换数据，就会抛出 TimeoutException 异常
 *
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(() -> {
            String data = "Thread-1's data";
            try {
                // 交换数据
                String exchange = exchanger.exchange(data);
                System.out.println("Thread1 exchanger get data is " + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            String data = "Thread-2's data";
            try {
                // 交换数据
                String exchange = exchanger.exchange(data);
                System.out.println("Thread2 exchanger get data is " + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
