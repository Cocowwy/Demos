package cn.cocowwy.javajuc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * https://blog.csdn.net/qq_31865983/article/details/106137777?spm=1001.2101.3001.6661.1&utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-106137777-blog-89756110.t0_layer_eslanding_sa&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-106137777-blog-89756110.t0_layer_eslanding_sa&utm_relevant_index=1
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
