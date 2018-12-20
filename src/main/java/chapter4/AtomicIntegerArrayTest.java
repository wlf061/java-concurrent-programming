package chapter4;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayTest {
    static AtomicIntegerArray arr = new AtomicIntegerArray(10);
    public static class AddThread implements Runnable{
        @Override
        public void run(){
            for(int k=0; k < 10000; k++){
                arr.getAndIncrement(k % arr.length());
            }
        }
    }

    public static void main(String[] str) throws InterruptedException {
        Thread[] ts = new Thread[10];
        for(int k=0; k < 10; k++){
            ts[k] = new Thread(new AddThread());
        }
        for(int k=0; k < 10; k++){
            ts[k].start();
        }
        for(int k=0; k < 10; k++){
            ts[k].join();
        }
        System.out.println(arr);
    }
}
