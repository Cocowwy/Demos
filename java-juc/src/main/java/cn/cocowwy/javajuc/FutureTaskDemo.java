package cn.cocowwy.javajuc;

import cn.hutool.core.util.RandomUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * FutureTask 接受一个 Callable 接口
 * run() 阻塞
 * get() 获取异步结果
 *
 * 可以在在主线程中 将任务丢出去执行，主线程进行完自己的操作之后，再来获取结果
 *
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Slf4j
public class FutureTaskDemo {
    @SneakyThrows
    public static void main(String[] args) {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            Thread.sleep(RandomUtil.randomLong(6000L, 10000L));
            return 1 + 2;
        });

        log.info("run task");
        // 将futureTask提交给异步的线程进行处理
        new Thread(futureTask).start();

        // 期间主线程执行其它的操作
        log.info("main do sth.");
        Thread.sleep(3000L);
        log.info("main do sth. done");

        log.info("main exit " + futureTask.get(5, TimeUnit.SECONDS));
    }
}
