package com.kayo.lib.worker.interfaces;

import com.kayo.lib.worker.enums.TaskPriority;
import com.kayo.lib.worker.enums.TaskResult;
import com.kayo.lib.worker.enums.TaskStatus;

/**
 * Kayo
 * 2019/01/12
 * 22:07
 * 任务 接口
 */
public interface ITask<D> extends Comparable<ITask>{

    //任务优先级(主要)
    void priority(TaskPriority priority);
    TaskPriority priority();

    //任务顺序(次要)
    void sequence(int sequence);
    int sequence();

    //任务状态
    void status(TaskStatus status);
    TaskStatus status();

    //任务结果
    void result(TaskResult result);
    TaskResult result();

    //任务准备
    void prepare();
    //任务进行中
    TaskResult doing();
    void preDone();
    //任务结束
    void done(TaskResult result);

    //延迟
    ITask delay(long time);
    long delay();

    void inputData(D data);
    D outData();
}
