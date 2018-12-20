package chapter4;

import java.util.concurrent.atomic.AtomicStampedReference;

/*
* 加入时间戳的比较，能解决ABA的问题
* */
public class AtomicStampReferenceTest {
    public static AtomicStampedReference<Integer> money = new AtomicStampedReference<Integer>(19,0);

    public static void main(String[] str) {
            /*模拟多个线程同时后台更新数据，为用户充值*/
        for (int k = 0; k < 3; k++) {
            final int timeStamp = money.getStamp();
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        while (true) {
                            Integer m = money.getReference();
                            if (m < 20) {
                                if (money.compareAndSet(m, m + 20,timeStamp, timeStamp+1)) {
                                    System.out.println("余额小于20元， 充值成功, 余额：" + money.getReference() + "元");
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                    }

                }
            }.start();
        }

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    while (true) {
                        Integer m = money.getReference();
                        int timeStamp = money.getStamp();
                        if (m > 10) {
                            System.out.println("大于10元");
                            if (money.compareAndSet(m, m - 10, timeStamp, timeStamp+1)) {
                                System.out.println("成功消费10元，余额：" +money.getReference());
                                break;
                            }
                        } else {
                            System.out.println("没有足够的金额");
                            break;
                        }
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
