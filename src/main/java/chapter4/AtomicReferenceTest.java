package chapter4;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by NJLT004 on 2018/12/2.
 */
public class AtomicReferenceTest {
    public final static AtomicReference<String> atomicStr = new AtomicReference<String>("abc");

    public static void main(String[] str){
        for(int i=0; i< 10;i++){
            final int num = i;
            new Thread(){
                @Override
                public void run(){
                    try {
                        Thread.sleep(Math.abs((int)(Math.random() *100)));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (atomicStr.compareAndSet("abc","def")){
                        System.out.println("Thread:"+Thread.currentThread().getId()+" Success");
                    } else{
                        System.out.println("Thread:" + Thread.currentThread().getId()+ " Failed");

                    }
                }
            }.start();
        }
    }
}
