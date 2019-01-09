package ch3.s1;

import java.util.concurrent.locks.ReentrantLock;

public class ReenterLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    public void run() {
        lock.lock();
        try {
            i++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLock r1 = new ReenterLock();
        Thread t1 = new Thread(r1,"t1");
        Thread t2 = new Thread(r1,"t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("the value of i is:"+i);
    }
}
