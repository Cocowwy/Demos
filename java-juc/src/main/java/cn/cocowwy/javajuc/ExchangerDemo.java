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

//class ExchangeDemo1 {
//    /**
//     * 实现功能
//     * 主线程发布一个任务
//     * 多个线程去执行任务，哪个最先完成，就接着执行主线程
//     * <p>
//     * 注意 exchange 的时候需要加入超时时间
//     */
//    public static void main(String[] args) throws InterruptedException {
//        Exchanger<String> exchanger = new Exchanger<>();
//
//        new Thread(() -> {
//            String data = "Thread-1's data";
//            try {
//                // 模拟 RPC
//                Thread.sleep(1000l);
//                String exchange = exchanger.exchange(data, 3, TimeUnit.SECONDS);
//                System.out.println("Thread 1 done，get main data " + exchange);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();
//        new Thread(() -> {
//            String data = "Thread-2's data";
//            try {
//                // 模拟 RPC
//                Thread.sleep(1200l);
//                String exchange = exchanger.exchange(data, 3, TimeUnit.SECONDS);
//                System.out.println("Thread 2 done，get main data " + exchange);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();
//        new Thread(() -> {
//            String data = "Thread-3's data";
//            try {
//                // 模拟 RPC
//                Thread.sleep(1500l);
//                String exchange = exchanger.exchange(data, 3, TimeUnit.SECONDS);
//                System.out.println("Thread 3 done，get main data " + exchange);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();
//
//
//        System.out.println("ok   " + exchanger.exchange("done"));
//
//    }
//}
