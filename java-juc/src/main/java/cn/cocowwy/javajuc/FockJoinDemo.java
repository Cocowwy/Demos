package cn.cocowwy.javajuc;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

/**
 * Fock/Join
 * 用于并行执行把一个大任务拆成多个小任务并行执行，最终汇总每个小任务结果得到大任务结果的特殊任务
 *
 *  ForkJoinPool的invoke是同步阻塞，excute是异步
 *
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class FockJoinDemo {

    static class SumTask extends RecursiveTask<Long> {
        // 默认子任务长度
        private final Integer DEFAULT_THRESHOLD = 50;

        private final List<Long> data;

        SumTask(List<Long> data) {
            this.data = data;
        }

        // 处理逻辑
        @Override
        protected Long compute() {
            // 当数据低于阈值的时候，则计算合
            if (data.size() <= DEFAULT_THRESHOLD) {
                long sum = data.stream().mapToLong(Long::valueOf).sum();
                System.out.format("sum list [%s]   sum [%s]\n", Arrays.toString(data.toArray()), sum);
                return sum;
            }

            // 拆分任务
            int split = data.size() / 2;
            SumTask prefixSplit = new SumTask(data.subList(0, split));
            SumTask suffixSplit = new SumTask(data.subList(split, data.size()));

            // 执行任务
            prefixSplit.fork();
            suffixSplit.fork();

            // 等待任务执行完成，获取结果
            return prefixSplit.join() + suffixSplit.join();
        }
    }


    public static void main(String[] args) {
        Random random = new Random();

        List<Long> data = random
                .longs(1_000_000, 1, 100)
                .boxed()
                .collect(Collectors.toList());

        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(data);

        System.out.format("sum %s (forkjoin) \n", pool.invoke(task));
    }
}