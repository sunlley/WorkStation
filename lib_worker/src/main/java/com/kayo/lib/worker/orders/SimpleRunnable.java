package com.kayo.lib.worker.orders;

import com.kayo.lib.worker.callbacks.QueueCallback;
import com.kayo.lib.worker.enums.TaskResult;
import com.kayo.lib.worker.enums.TaskStatus;
import com.kayo.lib.worker.interfaces.IQueue;
import com.kayo.lib.worker.interfaces.ITask;

import java.util.concurrent.TimeUnit;

/**
 * Kayo
 * 2019/01/12
 * 23:03
 */
class SimpleRunnable implements Runnable {
    protected boolean isRunning = true;
    protected IQueue queue;
    protected QueueCallback callback;

    public SimpleRunnable(IQueue queue, QueueCallback callback) {
        this.queue = queue;
        this.callback = callback;
    }

    @Override
    public void run() {
        if (queue != null) {
            isRunning = true;
            while (isRunning){
                try {
                    ITask take = queue.take();
                    if (take != null) {
                        take.status(TaskStatus.PREPARE);
                        long delay = take.delay();
                        //延迟操作
                        if (delay >0){
                            System.out.println("延迟任务：开始");
                            TimeUnit.MILLISECONDS.sleep(delay);
                            System.out.println("延迟任务：结束");
                        }
                        take.prepare();
                        take.status(TaskStatus.DOING);
                        TaskResult result = take.doing();
                        take.result(result);
                        take.status(TaskStatus.DONE);
                        take.preDone();
                        if (queue.size() == 0){
                            onDone();
                        }
                    }else {
                        onDone();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    onDone();
                }
            }
        }else {
            onDone();
        }
    }

    private void onDone(){
        System.out.println("线程结束");
        isRunning = false;
        if (callback != null) {
            callback.onDone();
        }
    }
}
