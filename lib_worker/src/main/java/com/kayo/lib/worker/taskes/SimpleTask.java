package com.kayo.lib.worker.taskes;

import com.kayo.lib.worker.enums.TaskResult;
import com.kayo.lib.worker.taskes.AbsTask;

/**
 * Kayo
 * 2019/01/12
 * 22:26
 * 默认任务
 */
public class SimpleTask<D> extends AbsTask<D> {
    @Override
    public void prepare() {

    }

    @Override
    public TaskResult doing() {
        return TaskResult.COMPLETE;
    }

    @Override
    public void done(TaskResult result) {

    }
}
