package ch3.s1.collection;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author nancy.wang
 * @Time 2019/1/3
 */
public class ArrayBlockingQueueTest {
    private static Queue<String> queue = new ArrayBlockingQueue<String>(2);
    public static void main(String[] args) {

        // 同时启动两个线程对queue进行操作！
        new MyThread("ta").start();
        new MyThread("tb").start();
    }

    private static void printAll() {
        String value;
        Iterator iter = queue.iterator();
        while(iter.hasNext()) {
            value = (String)iter.next();
            System.out.print(value+", ");
        }
        System.out.println("currentThread:"+Thread.currentThread().getName());
    }

    private static class MyThread extends Thread {
        MyThread(String name) {
            super(name);
        }
        @Override
        public void run() {
            int i = 0;
            while (i++ < 4) {
                // “线程名” + "-" + "序号"
                String val = Thread.currentThread().getName()+i;
                queue.offer(val);
                printAll();
            }
        }
    }

}
