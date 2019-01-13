package com.kayo.lib.worker.queues;

import com.kayo.lib.worker.callbacks.QueueCallback;
import com.kayo.lib.worker.interfaces.IQueue;
import com.kayo.lib.worker.interfaces.ITask;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Kayo
 * 2019/01/12
 * 22:40
 * 线性队列
 */
public class LinkedQueue implements IQueue {

    private QueueCallback mCallback;
    private AtomicInteger mAtomicInteger = new AtomicInteger();
    private final BlockingQueue<ITask> mTaskQueue = new PriorityBlockingQueue<>();

    public LinkedQueue() { }
    public LinkedQueue(QueueCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public <T extends ITask> int add(T task) {
        if (!mTaskQueue.contains(task)) {
            task.sequence(mAtomicInteger.incrementAndGet());
            mTaskQueue.add(task);
        }
        return size();
    }

    @Override
    public <T extends ITask> int remove(T task) {
        if (mTaskQueue.contains(task)){
            mTaskQueue.remove(task);
        }
        if (mTaskQueue.size() == 0) {
            mAtomicInteger.set(0);
        }
        return size();
    }

    @Override
    public <T extends ITask> T take() throws InterruptedException {
        ITask take = mTaskQueue.take();
        if (take != null) {
            return (T) take;
        }
        return null;

    }

    @Override
    public int size() {
        return mTaskQueue.size();
    }

    @Override
    public void callback(QueueCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public QueueCallback callback() {
        return mCallback;
    }
}
