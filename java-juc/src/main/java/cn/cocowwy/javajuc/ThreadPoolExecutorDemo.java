package cn.cocowwy.javajuc;

import java.util.concurrent.*;

/**
 * Note:
 * 32位的 int 在线程池中表示的最大线程数是： 2^29-1 约5亿个线程 高3位表示线程池的状态，低29位表示同意线程个数
 * SHUTDOWN： 不接受新任务，但处理排队任务  高三位 000
 * STOP： 不接受新任务，不处理排队任务，并中断正在进行的任务  高三位 001
 * TIDYING： 所有任务都已种植，workerCount 为0零，转换到状态TIDYING的线程将运行terminate()钩子方法  高三位 010
 * TERMINATED：线程池彻底种植，就变成TERMINATED状态，执行完terminated()之后，就会由 TIDYING->TERMINATED  高三位 011
 *
 * 线程池使用:
 * 首先检测线程池运行状态，如果不是RUNNING，则直接拒绝，线程池要保证在RUNNING的状态下执行任务。
 * 如果workerCount < corePoolSize，则创建并启动一个线程来执行新提交的任务。
 * 如果workerCount >= corePoolSize，且线程池内的阻塞队列未满，则将任务添加到该阻塞队列中。
 * 如果workerCount >= corePoolSize && workerCount < maximumPoolSize，且线程池内的阻塞队列已满，则创建并启动一个线程来执行新提交的任务。
 * 如果workerCount >= maximumPoolSize，并且线程池内的阻塞队列已满, 则根据拒绝策略来处理该任务, 默认的处理方式是直接抛异常。
 *
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        /**
         * {@link ThreadPoolExecutor#ThreadPoolExecutor(int, int, long, java.util.concurrent.TimeUnit, java.util.concurrent.BlockingQueue, java.util.concurrent.ThreadFactory, java.util.concurrent.RejectedExecutionHandler)}
         * corePoolSize：核心线程数，提交任务时，线程池创建一个新的线程执行任务，直到当前线程数为corePoolSize，即使有中途有空闲的线程能
         *               够执行任务，也会继续创建线程，直到线程数为corePoolSize，之后提交的任务会被保存到阻塞队列，等待执行，
         *               prestartAllCoreThreads() 线程池会提前创建并启动所有核心线程
         * maximumPoolSize：线程池中允许的最大线程数，如果当前阻塞队列满了，且在继续提交任务，则创建新的线程执行任务，直到达到
         *                  maximumPoolSize
         * keepAliveTime：线程空闲时的存户时间，当线程没有任务执行时，该线程继续存活的时间，默认情况下，该参数只有线程数大于corePoolSize
         *                时才有用，超过这个时间的空闲线程将被终止
         * unit：keepAliveTime的时间单位
         * workQueue：阻塞队列
         * threadFactory：创建线程的工厂，通过自定义的线程工厂可以给每个线程设置线程名等
         * handler：线程池的拒绝策略，当阻塞队列满了，且没有空闲的工作线程，如果继续提交任务，则必须采取一种策略处理该任务
         *              - AbortPolicy 直接抛出异常，默认策略
         *              - CallerRunsPolicy 用调用者所在的线程执行任务
         *              - DiscardOldestPolicy 丢弃阻塞队列中最靠前的任务，并执行当前任务
         *              - DiscardPolicy 直接丢弃任务
         */

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1,
                2,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1),
                Executors.defaultThreadFactory(), new MyRejectedExecutionHandler());

        // 按道理 只会执行3个 （max2个（其中一个是core），还有一个在阻塞队列，其余任务均被抛弃）
        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.submit(() -> {
                System.out.println("start..");
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("done..");
            });
        }
    }


    public static class MyRejectedExecutionHandler implements RejectedExecutionHandler {
        private static volatile Integer rejectSum = 0;

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.format("自定义拒绝策略，丢弃的第 %s 个任务\n", ++rejectSum);
        }
    }
}