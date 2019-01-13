package com.kayo.workstation;

import com.kayo.lib.worker.enums.TaskResult;
import com.kayo.lib.worker.orders.SimpleOrder;
import com.kayo.lib.worker.taskes.SimpleTask;

/**
 * Kayo
 * 2019/01/13
 * 22:32
 */
public class TestStation {

    public static void main(String[] args){
        new SimpleOrder()
                .work(new SimpleTask<String>(){
                    @Override
                    public void done(TaskResult result) {
                        super.done(result);
                        System.out.println("任务完成");
                    }
                })
                .execute();
    }
}
