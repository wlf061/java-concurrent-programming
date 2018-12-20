package chapter2;

public class CreateThread2 extends Thread {
    @Override
    public  void run(){
        System.out.println("I'm test2 thread");
    }

    public static void main(String[] str){
        Thread t2 = new CreateThread2();
        t2.start();
    }
}
