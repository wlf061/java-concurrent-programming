package ch3.s1.collection;

import scala.collection.mutable.HashTable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author nancy.wang
 * @Time 2019/1/4
 */
public class ConcurrentHashMapTest {
    public static void main(String[] str){
        //ConcurrentHashMap<String, String> testCHM = new ConcurrentHashMap<String, String>();  //一个线程在读concurrentHashMap, 另外一个线程在写concurrentHashMap,
        //遍历的过程中写入没法读到当前的值.
        HashMap<String, String> testCHM = new HashMap<String, String>();  //HashMap 中一个线程读，一个线程写会抛出异常ConcurrentModificationException
        testCHM.put("1","11111");
        testCHM.put("2","22222");
        testCHM.put("3","33333");
        testCHM.put("4","44444");
        testCHM.put("5","55555");
        testCHM.put("7","77777");
        testCHM.put("8","88888");

        new Thread(new Runnable() {
            @Override
            public void run(){
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("------开始插入66666-------");
                testCHM.put("6","66666");
                System.out.println("----------结束插入666-----");

            }
        }).start();
       for(Map.Entry entry: testCHM.entrySet()){
            System.out.println(entry.getValue());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
