package cn.cocowwy.javajuc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Slf4j
public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            int user = i;
            new Thread(() -> {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("玩家 【%s】加载完成", user);
            }).start();
            cdl.countDown();
        }

        log.info("加载完成，进入游戏");
    }
}
