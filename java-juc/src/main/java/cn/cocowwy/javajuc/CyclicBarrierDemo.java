package cn.cocowwy.javajuc;

import cn.hutool.core.util.RandomUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * CyclicBarrier可以使一定数量的线程反复地在栅栏位置处汇集。当线程到达栅栏位置时将调用await方法，
 * 这个方法将阻塞直到所有线程都到达栅栏位置。如果所有线程都到达栅栏位置，那么栅栏将打开，此时所有的线程都将被释放，
 * 而栅栏将被重置以便下次使用。
 *
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Slf4j
public class CyclicBarrierDemo {

    public static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            2,
            4,
            3,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(1),
            Executors.defaultThreadFactory(), new ThreadPoolExecutorDemo.MyRejectedExecutionHandler());
    @SneakyThrows
    public static void main(String[] args) {
        /**
         * 使用
         */
        test1();

        /**
         * 测试：
         * 核心线程为2  添加的循环栅栏大于核心线程，导致死锁等待
         */
        test2();
    }

    private static void test2() {
        CyclicBarrier cb = new CyclicBarrier(3);
        System.out.println("start..");
        threadPoolExecutor.submit(() -> {
            System.out.println("向线程池提交 A");
            try {
                Thread.sleep(5000L);
                cb.await(5, TimeUnit.SECONDS);
                System.out.println("A");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            } catch (TimeoutException e) {
                throw new RuntimeException(e);
            }
            System.out.println("");
        });

        threadPoolExecutor.submit(() -> {
            try {
                System.out.println("向线程池提交 B");
                Thread.sleep(5000L);
                cb.await(5, TimeUnit.SECONDS);
                System.out.println("B");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            } catch (TimeoutException e) {
                throw new RuntimeException(e);
            }
            System.out.println("");
        });

        threadPoolExecutor.submit(() -> {
            try {
                System.out.println("向线程池提交 C");
                cb.await(5, TimeUnit.SECONDS);
                System.out.println("C");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            } catch (TimeoutException e) {
                throw new RuntimeException(e);
            }
            System.out.println("C");
        });

        System.out.println("main exit");
    }

    private static void test1() {
        CyclicBarrier cbr = new CyclicBarrier(10, () -> {
            System.out.println("CyclicBarrier is open");
        });

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                String name = Thread.currentThread().getName();
                try {
                    Thread.sleep(RandomUtil.randomLong(1000L, 3000L));
                    System.out.println(name + " 到达栅栏，进行阻塞...");
                    cbr.await(5L, TimeUnit.SECONDS);
                    System.out.println(name + " 阻塞完成");
                } catch (Exception e) {
                    log.error(name + "等待栅栏开放超时");
                }
            }, "线程" + i).start();
        }
    }
}


