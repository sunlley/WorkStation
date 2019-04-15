package com.kayo.workstation;

import android.util.Log;

import com.kayo.lib.worker.enums.TaskResult;
import com.kayo.lib.worker.orders.SimpleOrder;
import com.kayo.lib.worker.taskes.MainTask;
import com.kayo.lib.worker.taskes.SimpleTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Kayo
 * 2019/01/13
 * 22:32
 */
public class TestStation {

    public static void main(String[] args){
        new SimpleOrder()
                .work(new SimpleTask<Integer>(){
                    @Override
                    public TaskResult doing() {
                        System.out.println("TestStation"+ "doing: ");
                        return super.doing();
                    }

                    @Override
                    public void done(TaskResult result) {
                        super.done(result);
                        System.out.println("TestStation"+ "done: ");
                    }
                })
//                .and()
//                .and()
//                .and()
//                .and()
//                .then()
//                .then()
//
//                .work()
//                .and()
//                .then()
//

                .execute();
    }
}
