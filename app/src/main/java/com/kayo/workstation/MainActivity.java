package com.kayo.workstation;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.kayo.lib.worker.enums.TaskResult;
import com.kayo.lib.worker.orders.SimpleOrder;
import com.kayo.lib.worker.taskes.SimpleTask;


public class MainActivity extends AppCompatActivity {

    private static String TAG = "MAINACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.v_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });

    }

    private void test(){
        final int ii =10;
        new SimpleOrder()
                //任务1
                .work(new SimpleTask<String>() {
                    @Override
                    public TaskResult doing() {
                        System.out.println("标识：" + ii + "  主 任务执行：" + this.toString());
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        inputData("标识：" + ii + "  主任务测试数据====================");

                        return super.doing();
                    }

                    @Override
                    public void done(TaskResult result) {
                        super.done(result);
                        System.out.println("标识：" + ii + "  主 任务执行完成：" + result.name() + "   " + outData());
                    }
                })
                //次任务1
                .then(new SimpleTask() {
                    @Override
                    public TaskResult doing() {
                        try {
                            System.out.println("标识：" + ii + "  串行 任务执行：" + this.toString());
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return super.doing();
                    }

                    @Override
                    public void done(TaskResult result) {
                        super.done(result);
                        System.out.println("标识：" + ii + "  串行 任务执行完成：" + result.name());
                    }
                })
                //从任务任务1
                .and(new SimpleTask() {
                    @Override
                    public TaskResult doing() {
                        try {
                            System.out.println("标识：" + ii + "  并行 任务执行：" + this.toString());
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return super.doing();
                    }

                    @Override
                    public void done(TaskResult result) {
                        super.done(result);
                        System.out.println("标识：" + ii + "  并行 任务执行完成：" + result.name());
                    }
                })
                //从任务任务2
                .and(new SimpleTask() {
                    @Override
                    public TaskResult doing() {
                        try {
                            System.out.println("标识：" + ii + "  并行 任务执行：" + this.toString());
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return super.doing();
                    }

                    @Override
                    public void done(TaskResult result) {
                        super.done(result);
                        System.out.println("标识：" + ii + "  并行 任务执行完成：" + result.name());
                    }
                })
                //次任务2
                .then(new SimpleTask() {
                    @Override
                    public TaskResult doing() {
                        try {
                            System.out.println("标识：" + ii + "  串行 任务执行：" + this.toString());
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return super.doing();
                    }

                    @Override
                    public void done(TaskResult result) {
                        super.done(result);
                        System.out.println("标识：" + ii + "  串行 任务执行完成：" + result.name());
                    }
                })
                .execute();



    }
}

