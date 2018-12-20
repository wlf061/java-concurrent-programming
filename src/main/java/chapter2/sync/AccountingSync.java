package chapter2.sync;

/*
* Synchronized 的三种用法：
* 1. 指定加锁对象：对给定对象加锁，进入同步代码前需要获得给定对象的锁
* 2. 直接作用于实例方法: 相当于对当前实例加锁，进入同步代码前需要获得当前实例的锁
* 3. 直接作用于静态方法： 相当于对当前类加锁， 进入同步代码前需要获得当前类的锁
*
* */

/*
* 拿到对象锁
* */
public class AccountingSync implements Runnable {
    static AccountingSync instance = new AccountingSync();
    static int i=0;
    @Override
    public void run() {
        for(int j=0; j < 1000; j++){
            synchronized (instance){
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);  //2000
    }
}
