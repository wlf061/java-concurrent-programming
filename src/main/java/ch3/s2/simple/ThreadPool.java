package ch3.s2.simple;
import java.util.List;
import java.util.Vector;

/**
 * @author nancy.wang
 * @Time 2019/1/9
 */
public class ThreadPool {
    private static ThreadPool instance = null;
    //空闲的线程队列
    private List<Worker> idleThreads;

    //已有的线程总数
    private int threadCounter;

    private boolean isShutDown = false;

    private ThreadPool(){
        this.idleThreads = new Vector(5);
        this.threadCounter = 0;
    }

    public int getCreatedThreadsCount(){
        return threadCounter;
    }

    //获取instance 实例
    public synchronized static ThreadPool getInstance(){
        if(instance == null){
            instance = new ThreadPool();
        }
        return instance;
    }

    // 将线程放入池中
    protected  synchronized  void repool(Worker repoolingThread){
        if(!isShutDown){
            idleThreads.add(repoolingThread);
        }
        else{
            repoolingThread.shutDown();
        }
    }

    //停止 池中所有的线程
    public synchronized  void shutDown(){
        isShutDown = true;
        for(int threadIndex = 0; threadIndex < idleThreads.size(); threadIndex++){
            Worker idleThread = (Worker) idleThreads.get(threadIndex);
            idleThread.shutDown();
        }
    }

    // 执行任务

    public synchronized  void start(Runnable target){
        Worker thread=null;
        //如果有空闲线程， 则直接使用
        if(idleThreads.size() > 0){
            int lastIndex = idleThreads.size() - 1;
            thread = (Worker) idleThreads.get(lastIndex);
            idleThreads.remove(lastIndex);
            //立即执行这个任务
            thread.setTarget(target);
        }
        else{
            threadCounter++;
            thread= new Worker(target, "PThread#"+ threadCounter, this);
            //启动线程
            thread.start();
        }
    }


}
