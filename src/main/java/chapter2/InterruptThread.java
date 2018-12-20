package chapter2;

/**
 * Created by NJLT004 on 2018/11/11.
 */
public class InterruptThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run(){
                while(true){
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("Interrupted");
                        break;
                    }
                    Thread.yield();
                }
            }
        };
        t1.start();
        Thread.sleep(2000);
        //Thread.interrupt 是一个实例方法， 设置目标线程的中断标志位，中断标志位表示当前线程已经被中断
        //Thread.isInterrupted 判断线程是否被中断
        //Thread.interrupted() 判断线程是否被中断，并清除当前中断状态
        t1.interrupt();    //设置线程中断标志位，需要处理中断逻辑
    }

}
