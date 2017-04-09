package studio.geek.core;

import org.apache.log4j.Logger;
import studio.geek.util.SimpleLogger;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池工具类，监视ThreadPoolExecutor执行情况
 */
public class ThreadPoolMonitor implements Runnable{
    private static Logger logger = SimpleLogger.getSimpleLogger(ThreadPoolMonitor.class);
    private ThreadPoolExecutor executor;
    public static volatile boolean isStopMonitor = false;
    private String name = "";
    public ThreadPoolMonitor(ThreadPoolExecutor executor,String name){
        this.executor = executor;
        this.name = name;
    }

    public void run(){
        while(!isStopMonitor){
            logger.debug(name +
                    String.format("[monitor] [%d/%d] Active: %d, Completed: %d, queueSize: %d, Task: %d, isShutdown: %s, isTerminated: %s",
                            this.executor.getPoolSize(),
                            this.executor.getCorePoolSize(),
                            this.executor.getActiveCount(),
                            this.executor.getCompletedTaskCount(),
                            this.executor.getQueue().size(),
                            this.executor.getTaskCount(),
                            this.executor.isShutdown(),
                            this.executor.isTerminated()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.error("InterruptedException",e);
            }
        }
    }
}