package ch3.s2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 类的描述
 *
 * @author nancy.wang
 * @Time 2019/1/29
 */
public class CountTask2 extends RecursiveTask<Long> {

    private static final int THRESHOLD = 100;
    private long start;
    private long end;

    public CountTask2(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        if (end - start <= THRESHOLD) {
            for (long i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            long mid = start + (end - start) / 2;
            CountTask2 leftTask = new CountTask2(start, mid - 1);
            CountTask2 rightTask = new CountTask2(mid, end);
            System.out.println("线程的名字："+Thread.currentThread().getName() + "，线程的 id："+Thread.currentThread().getId());

            //提交子任务
            leftTask.fork();
            rightTask.fork();
            //合并小任务结果
            sum += leftTask.join();
            sum += rightTask.join();
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask2 task = new CountTask2(0, 1000L);
        ForkJoinTask<Long> result = forkJoinPool.submit(task);
        try {
            long res = result.get();
            System.out.println("sum=" + res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
