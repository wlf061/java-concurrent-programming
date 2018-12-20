package chapter2;

/**
 * Created by NJLT004 on 2018/11/11.
 */
public class InterruptedThread2 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run(){
                while(true){
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("Interrupted");
                        break;
                    }
                    // 线程在sleep 的时候 如果有人中断这个线程
                    try{
                        Thread.sleep(2000);
                    }catch(InterruptedException e){
                        System.out.println("Interrupted when Sleep");
                        Thread.currentThread().interrupt();   // 在线程 抛出InterruptedException 后，中断标志已经清空了， 需要手动再次执行interrupt
                    }
                    Thread.yield();
                }
            }
        };
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }
}
