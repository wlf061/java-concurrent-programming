package chapter4;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by NJLT004 on 2018/12/2.
 */
public class AtomicIntegerFieldUpdaterTest {
    public static class Candiate{
        int id;
        volatile int score;
    }
    public final static AtomicIntegerFieldUpdater<Candiate> scoreUpdater = AtomicIntegerFieldUpdater.newUpdater(Candiate.class,"score");

    //检查update 是否工作正确
    public static AtomicInteger allScore = new AtomicInteger(0);

    public static void main(String[] str) throws InterruptedException {
        final Candiate stu = new Candiate();
        Thread[] t = new Thread[10000];
        for(int i=0; i< 10000; i++){
            t[i]= new Thread(){
                @Override
                public void run(){
                    if(Math.random()> 0.4){
                        scoreUpdater.incrementAndGet(stu);
                        allScore.incrementAndGet();
                    }
                }
            };
             t[i].start();
        }
        for(int i=0; i < 10000;i++){
            t[i].join();
        }
        System.out.println("score="+stu.score);
        System.out.println("allScore="+allScore);
    }
}
