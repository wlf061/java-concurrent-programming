package chapter4;

import java.util.concurrent.atomic.AtomicReference;

/*
* 对过程变化比较敏感的业务，
* 简单用AtomicReference 会出现ABA的问题：
* 业务说明： 如果有一家蛋糕店，为了挽留客户，决定为贵宾卡里
* 余额 小于20元的客户一次性赠送20元； 但是，每个客户只能被赠送一次;
*
* 下面案例中会出先用户的账户被多次充值的情况
* */
public class AtomicReferenceFailTest {
    public static AtomicReference<Integer> money = new AtomicReference<Integer>(19);

    public static void main(String[] str) {
            /*模拟多个线程同时后台更新数据，为用户充值*/
        for (int k = 0; k < 10; k++) {
            new Thread() {
                public void run() {
                    while (true) {
                        while (true) {
                            Integer m = money.get();
                            if (m < 20) {
                                if (money.compareAndSet(m, m + 20)) {
                                    System.out.println("余额小于20元， 充值成功, 余额：" + money.get() + "元");
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
                        Integer m = money.get();
                        if (m > 10) {
                            System.out.println("大于10元");
                            if (money.compareAndSet(m, m - 10)) {
                                System.out.println("成功消费10元，余额：" + money.get());
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
