package chapter2.sync;

public class WaitAndNotify {
    final static Object object = new Object();
    public static class T1 extends Thread{
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + " T1:start!");
                try {
                    System.out.println(System.currentTimeMillis() + " T1 wait for object");
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + " T1:end");
            }
        }
    }

    public static class T2 extends Thread{
        @Override
        public void run(){
            synchronized (object){
                System.out.println(System.currentTimeMillis()+" T2 start ! notify one thread");
                object.notify();
                System.out.println(System.currentTimeMillis()+" :T2 end");
                try{
                    Thread.sleep(2000);
                } catch (InterruptedException e){

                }
            }
        }
    }
    public static void main(String[] args){
        Thread t1 = new T1();
        Thread t2 = new T2();
        t1.start();
        t2.start(); //t2 通知t1 后， t1 并不能立刻执行，需要等2000ms后，拿到Object 对象锁 然后再往下执行
    }

}
