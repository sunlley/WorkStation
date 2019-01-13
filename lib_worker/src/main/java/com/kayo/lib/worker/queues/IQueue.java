package com.kayo.lib.worker.queues;

import com.kayo.lib.worker.callbacks.QueueCallback;
import com.kayo.lib.worker.taskes.ITask;

/**
 * Kayo
 * 2019/01/12
 * 22:19
 */
public interface IQueue {

    //添加任务
    <T extends ITask> int  add(T task);
    //删除任务
    <T extends ITask> int remove(T task);
    //获取任务
    <T extends ITask> T take() throws InterruptedException;
    //任务量
    int size();

    //队列回调
    void callback(QueueCallback callback);
    QueueCallback callback();

}
