package ch3.s2.simple;
/**
 * 类的描述
 *
 * @author nancy.wang
 * @Time 2019/1/9
 */
public class ThreadPoolTest {
    public static void main(String[] str){

        ThreadPool pool = ThreadPool.getInstance();
        pool.start(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable");
            }
        });

        // 在这里 如果 不 sleep 3000 s, 直接执行 shutDown 有可能 线程仍然挂起，
        // 先执行 shutdown, 此时worker 进入了run, 然后isShutDown 为 True,
        //这样线程 就进入了wait 状态不能唤醒。
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutDown();
    }
}
