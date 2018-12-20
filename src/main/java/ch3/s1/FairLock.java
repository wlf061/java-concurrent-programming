package ch3.s1;

import java.util.concurrent.locks.ReentrantLock;

/***
 * 公平锁: 性能相对于非公平锁 性能要差很多,
 */
public class FairLock implements Runnable {
    public static ReentrantLock fairLock = new ReentrantLock(true);
//    public static ReentrantLock fairLock = new ReentrantLock();

    public void run() {
        while (true){
            try {
                fairLock.lock();
                System.out.println(Thread.currentThread().getName());
            }finally {
                fairLock.unlock();
            }
        }
    }

    public static void main(String [] args){
        FairLock r1 = new FairLock();
        Thread t1 = new Thread(r1,"Thread_t1");
        Thread t2 = new Thread(r1,"Thread_t2");
        t1.start();
        t2.start();
    }
}
