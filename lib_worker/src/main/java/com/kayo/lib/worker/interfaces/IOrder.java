package com.kayo.lib.worker.interfaces;

import java.util.Map;

/**
 * Kayo
 * 2019/01/12
 * 22:07
 * 指令
 */
public interface IOrder<O extends IOrder> {

    //主任务
    O work(ITask task);
    //并行任务(目前伪并行)
    O and(ITask task);
    //串行任务
    O then(ITask task);

    int workCount();
    Map<Integer,IQueue> workGroup();

    void execute();
}
