package com.kayo.lib.worker.taskes;

import android.os.Handler;
import android.os.Message;

import com.kayo.lib.worker.WeakHandler;
import com.kayo.lib.worker.enums.TaskResult;

/**
 * Kayo
 * 2019/01/12
 * 23:52
 * 主线程回调任务
 */
public class MainTask<D> extends AbsTask<D> {

    private WeakHandler mHandler;

    public MainTask() {
        mHandler = new WeakHandler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                done(mResult);
                return false;
            }
        });
    }

    @Override
    public void prepare() {

    }

    @Override
    public TaskResult doing() {
        return TaskResult.COMPLETE;
    }

    @Override
    public final void preDone() {
        mHandler.sendEmptyMessage(1);
    }

    @Override
    public void done(TaskResult result) {

    }
}
