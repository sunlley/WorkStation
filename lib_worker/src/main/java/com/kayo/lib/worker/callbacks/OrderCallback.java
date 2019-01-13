package com.kayo.lib.worker.callbacks;

/**
 * Kayo
 * 2019/01/12
 * 23:28
 */
public interface OrderCallback {
    void onDone(int workCount,int doneCount);
}
