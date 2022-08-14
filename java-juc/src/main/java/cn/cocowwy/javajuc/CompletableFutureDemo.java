package cn.cocowwy.javajuc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * https://blog.csdn.net/qq_31865983/article/details/106137777?spm=1001.2101.3001.6661.1&utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-106137777-blog-89756110.t0_layer_eslanding_sa&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-106137777-blog-89756110.t0_layer_eslanding_sa&utm_relevant_index=1
 *
 * runAsync()和supplyAsync()均是直接异步执行一个任务，并且均通过get()阻塞主线程等待返回
 * 前者无返回值，后者有返回值
 *
 * thenApply()和thenApplyAsync()均是CompletableFuture的方法，可以监听到返回值之后进行处理
 * 其返回值均为CompletableFuture
 *
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class CompletableFutureDemo {
    // 无返回值
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> testRunAsync = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("execute testRunAsync");
        System.out.println("testRunAsync reture:" + testRunAsync.get());
        CompletableFuture<String> testRunAsyncThenApply = testRunAsync.thenApply((thenApplyResult) -> {
            return "testRunAsync done ，testRunAsync result is " + thenApplyResult;
        });
        System.out.println(testRunAsyncThenApply.get());

        // supplyAsync 结果异步任务和线程池  有返回值
        CompletableFuture<Integer> testSupplyAsync = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 10;
        }, ThreadPoolExecutorDemo.threadPoolExecutor);
        System.out.println("execute testSupplyAsync");
        System.out.println("testSupplyAsync reture:" + testSupplyAsync.get());
        CompletableFuture<String> testSupplyAsyncThenApplyAsync = testSupplyAsync.thenApplyAsync((testSupplyAsyncResult) -> {
            return "testSupplyAsync done ，testSupplyAsync result is " + testSupplyAsyncResult;
        }, ThreadPoolExecutorDemo.threadPoolExecutor);
        System.out.println("testSupplyAsyncThenApplyAsync get result " + testSupplyAsyncThenApplyAsync.get());


    }
}
