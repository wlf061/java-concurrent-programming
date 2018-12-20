package chapter2;


class ThreadRunnale implements Runnable{

    @Override
    public void run() {
        System.out.println("I'm test1 thread");
    }
}

public class CreateThread {
    public static void main(String[] str) {
        Thread t1 = new Thread(new ThreadRunnale());
        t1.start();
    }
}
