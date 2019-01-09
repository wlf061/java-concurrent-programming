package ch3.s2.simple;

/**
 * 类的描述
 *
 * @author nancy.wang
 * @Time 2019/1/9
 */
public class Worker extends Thread {
    //线程池
    private ThreadPool pool;
    private Runnable target;
    private boolean isShutDown = false;
    private boolean isIdle = false;

    public Worker(Runnable target, String name, ThreadPool pool){
        super(name);
        this.pool = pool;
        this.target = target;
    }

    public Runnable getTarget(){
        return target;
    }

    public boolean isIdle(){
        return isIdle;
    }

    @Override
    public void run() {
        while(!isShutDown){
            isIdle = false;
            if(target != null){
                target.run();
            }
            //任务结束了
            isIdle = true;
            try{
                pool.repool(this);
                synchronized (this){
                    wait();
                }
            }
            catch (InterruptedException ie){

            }
            isIdle = false;
        }

    }

    public synchronized  void setTarget(Runnable newTarget){
        target = newTarget;
        //设置了任务之后，通知run 方法， 开始执行这个任务
        notifyAll();
    }
    public synchronized  void shutDown(){
        isShutDown = true;
        notifyAll();
    }
}
