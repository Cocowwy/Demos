package cn.cocowwy.javajuc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<Integer> submit = ThreadPoolExecutorDemo.threadPoolExecutor.submit(() -> {
            Thread.sleep(3000L);
            return 10;
        });

        doSomethingInMainThread();

        System.out.println("wait 4 get()..");
        System.out.println("exit get() return: " + submit.get());
    }

    private static void doSomethingInMainThread() {
        System.out.println("doSomethingInMainThread");
    }
}
