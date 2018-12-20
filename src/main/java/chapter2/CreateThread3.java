package chapter2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyCallable implements Callable<String> {

    private long waitTime;
    public MyCallable(int timeInMillis){
        this.waitTime = timeInMillis;
    }
    @Override
    public String call() throws Exception {
        Thread.sleep(waitTime);
        return Thread.currentThread().getName();
    }
}

public class CreateThread3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = new MyCallable(10000);
        FutureTask<String> task = new FutureTask<>(callable);
        long beginTime = System.currentTimeMillis();
        // 创建线程
        new Thread(task).start();
        String result = task.get();  //阻塞主线程
        long endTime = System.currentTimeMillis();
        System.out.println("hello : " + result);
        System.out.println("cast : " + (endTime - beginTime) / 1000 + " second!");
    }

}

