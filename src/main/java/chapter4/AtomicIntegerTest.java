package chapter4;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by NJLT004 on 2018/12/2.
 */
public class AtomicIntegerTest {
    static AtomicInteger i = new AtomicInteger();

    public static class AddThread implements Runnable {
        @Override
        public void run() {
            for (int k = 0; k < 10000; k++) {
                i.incrementAndGet();
            }
        }
    }

    public static void main(String[] str) throws InterruptedException {
        Thread[] td = new Thread[10];
        for (int k = 0; k < 10; k++) {
            td[k] = new Thread(new AddThread());
        }
        for (int k = 0; k < 10; k++) {
            td[k].start();
        }
        for (int k = 0; k < 10; k++) {
            td[k].join();
        }
        System.out.print(i);
    }
}
