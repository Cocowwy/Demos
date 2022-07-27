package cn.cocowwy.javajuc;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

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
            Thread thread = new Thread(() -> {
                try {
                    // 模拟不同玩家超时的时间
                    Thread.sleep(RandomUtil.randomLong(4000L, 6000L));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // 这里设置成守护进程，即当超时了 就退出
                log.info("玩家 【{}】加载完成", user);
                cdl.countDown();
            });
            thread.setDaemon(true);
            thread.start();
        }

        boolean res = false;
        try {
            res = cdl.await(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            // ignore..
        }
        if (res) {
            log.info("加载完成，进入游戏......");
        } else {
            log.info("加载失败，玩家超时......");
        }
    }
}

/**
 * 实现功能  同时从多个数据源进行请求，先返回数据就继续往后执行代码
 */
class CountDownLatchDemo1 {
    public static void main(String[] args) {

    }
}