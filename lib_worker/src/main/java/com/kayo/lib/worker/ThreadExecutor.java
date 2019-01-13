package com.kayo.lib.worker;

import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Kayo
 * 2019/01/12
 * 22:08
 * 线程执行
 */
public class ThreadExecutor {
    private int minSize = 1;//线程池维护线程的最少数量
    private int maxSize = 20;//线程池维护线程的最大数量
    private int keepAliveTime = 5;//线程池维护线程所允许的空闲时间
    private TimeUnit unit = TimeUnit.SECONDS;//线程池维护线程所允许的空闲时间的单位
    private LinkedBlockingQueue<Runnable> queue;//线程池所使用的缓冲队列,,先进先出
//    private PriorityBlockingQueue queue2;//实现 Comparator接口
    private ThreadPoolExecutor threadPoolExecutor;
    private final static ThreadExecutor threadExecutor = new ThreadExecutor();

    private ThreadExecutor() {
        queue = new LinkedBlockingQueue<>();
//        queue2 = new PriorityBlockingQueue();
        threadPoolExecutor = new ThreadPoolExecutor(minSize, maxSize, keepAliveTime, unit, queue, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

            }
        });
    }

    public static ThreadExecutor getExecutor(){
        return threadExecutor;
    }

    public Future<?> execute(Runnable runnable){
        return threadPoolExecutor.submit(runnable);
    }

}
