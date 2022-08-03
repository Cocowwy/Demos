package cn.cocowwy.javajuc;

import lombok.SneakyThrows;

import java.util.concurrent.CyclicBarrier;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class CyclicBarrierDemo {
    @SneakyThrows
    public static void main(String[] args) {
        CyclicBarrier cbr = new CyclicBarrier(2, () -> {
            System.out.println("CyclicBarrier right");
        });
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    cbr.await();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

        System.out.println("主线程～～～");
    }
}
