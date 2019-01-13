package com.kayo.lib.worker.orders;

import com.kayo.lib.worker.ThreadExecutor;
import com.kayo.lib.worker.callbacks.OrderCallback;
import com.kayo.lib.worker.callbacks.QueueCallback;
import com.kayo.lib.worker.enums.TaskPriority;
import com.kayo.lib.worker.interfaces.IOrder;
import com.kayo.lib.worker.interfaces.IQueue;
import com.kayo.lib.worker.interfaces.ITask;
import com.kayo.lib.worker.queues.LinkedQueue;

import java.util.HashMap;
import java.util.Map;

/**
 * Kayo
 * 2019/01/13
 * 00:29
 */
public class AbsOrder implements IOrder<AbsOrder> {
    private int workCount;
    private int onDoneCount;//完成数
    private Map<Integer, IQueue> taskGroup;
    private OrderCallback callback;

    public AbsOrder() {
        this(null);
    }

    public AbsOrder(OrderCallback callback) {
        this.callback = callback;
        this.workCount = 0;
        this.onDoneCount = 0;
        this.taskGroup = new HashMap<>();
    }

    @Override
    public final AbsOrder work(ITask task) {
        System.out.println("添加任务");
        if (task != null) {
            workCount++;
            LinkedQueue queue = new LinkedQueue();
            task.priority(TaskPriority.HIGH);
            queue.add(task);
            taskGroup.put(workCount,queue);
        }

        return this;
    }

    @Override
    public final AbsOrder and(ITask task) {
        IQueue queue = taskGroup.get(workCount);
        if (queue != null) {
            task.priority(TaskPriority.DEFAULT);
            queue.add(task);
        }
        return this;
    }

    @Override
    public final AbsOrder then(ITask task) {
        IQueue queue = taskGroup.get(workCount);
        if (queue != null) {
            task.priority(TaskPriority.LOW);
            queue.add(task);
        }
        return this;
    }

    @Override
    public final int workCount() {
        return workCount;
    }

    @Override
    public final Map<Integer, IQueue> workGroup() {
        return taskGroup;
    }

    @Override
    public void execute() {
        for (int i = 0; i < workCount; i++) {
            IQueue iQueue = taskGroup.get(i+1);
            ThreadExecutor.getExecutor().execute(new SimpleRunnable(iQueue, new QueueCallback() {
                @Override
                public void onDone() {
                    onDoneCount++;
                    if (callback != null) {
                        callback.onDone(workCount,onDoneCount);
                    }
                }
            }));
        }
    }
}
