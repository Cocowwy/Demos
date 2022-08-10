package cn.cocowwy.javajuc;

import cn.hutool.core.util.RandomUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

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
    @SneakyThrows
    public static void main(String[] args) {
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
