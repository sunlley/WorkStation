package com.kayo.lib.worker.taskes;

import com.kayo.lib.worker.enums.TaskPriority;
import com.kayo.lib.worker.enums.TaskResult;
import com.kayo.lib.worker.enums.TaskStatus;
import com.kayo.lib.worker.interfaces.ITask;

/**
 * Kayo
 * 2019/01/12
 * 23:53
 * 抽象任务
 */
public abstract class AbsTask<D> implements ITask<D> {

    protected D mData;//数据
    protected TaskPriority mPriority;//任务优先级
    protected int mSequence;//添加顺序
    protected TaskStatus mStatus = TaskStatus.DONE;//任务状态
    protected TaskResult mResult = TaskResult.COMPLETE;//任务结果
    protected long mDelay;//延迟时间

    @Override
    public void priority(TaskPriority priority) {
        this.mPriority = priority;
    }

    @Override
    public TaskPriority priority() {
        return mPriority;
    }

    @Override
    public void sequence(int sequence) {
        this.mSequence = sequence;
    }

    @Override
    public int sequence() {
        return mSequence;
    }

    @Override
    public void status(TaskStatus status) {
        this.mStatus = status;
    }

    @Override
    public TaskStatus status() {
        return mStatus;
    }

    @Override
    public void result(TaskResult result) {
        this.mResult = result;
    }

    @Override
    public TaskResult result() {
        return mResult;
    }

    @Override
    public void inputData(D data) {
        this.mData = data;
    }

    @Override
    public D outData() {
        return mData;
    }

    @Override
    public long delay() {
        return mDelay;
    }

    @Override
    public ITask delay(long time) {
        this.mDelay = time;
        return this;
    }

    @Override
    public int compareTo(ITask o) {
        TaskPriority p1 = this.priority();
        TaskPriority p2 = o.priority();
        if (p1 == p2) {
            return this.sequence() - o.sequence();
        }
        return p2.ordinal() - p1.ordinal();
    }

    @Override
    public void preDone() {
        done(mResult);
    }

    @Override
    public String toString() {
        return "{sequence:"+sequence()+"  priority:"+priority().name()+"}";
    }
}
