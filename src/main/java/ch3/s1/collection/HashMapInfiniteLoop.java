package ch3.s1.collection;

import java.util.HashMap;

/**
 * 并发多线程下 HashMap 的不安全性
 *
 * @author nancy.wang
 * @Time 2019/1/3
 */
public class HashMapInfiniteLoop {
    private static HashMap<Integer,String> map = new HashMap<Integer,String>(2,0.75f);

    public static void main(String[] str){
        map.put(5,"C");
        map.put(6,"D");
        new Thread("Thread1") {
            @Override
            public void run() {
                map.put(7, "B");
                System.out.println(map);
            };
        }.start();
        new Thread("Thread2") {
            @Override
            public void run() {
                map.put(3, "A");
                        System.out.println(map);
            };
        }.start();
    }

}
