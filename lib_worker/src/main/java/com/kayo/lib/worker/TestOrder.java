package com.kayo.lib.worker;

import com.kayo.lib.worker.enums.TaskResult;
import com.kayo.lib.worker.orders.SimpleOrder;
import com.kayo.lib.worker.taskes.MainTask;
import com.kayo.lib.worker.taskes.SimpleTask;

/**
 * Kayo
 * 2019/01/12
 * 23:32
 */
public class TestOrder {

//    public static void main(String[] args) {
//        for (int i = 0; i < 1; i++) {
//            final int ii = i;
//
//            //任务2
//            new SimpleOrder()
//                    .work(new SimpleTask<String>() {
//
//                        @Override
//                        public TaskResult doing() {
//                            System.out.println("标识：" + ii + "  主 任务执行：" + this.toString());
//                            try {
//                                Thread.sleep(200);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            inputData("标识：" + ii + "  主任务测试数据====================");
//
//                            return super.doing();
//                        }
//
//                        @Override
//                        public void done(TaskResult result) {
//                            super.done(result);
//                            System.out.println("标识：" + ii + "  主 任务执行完成：" + result.name() + "   " + outData());
//                        }
//                    }
//                            //任务延迟时间
//                            .delay(1000))
//                    .execute();
//
//
//            new SimpleOrder()
//                    .work(
//                            //任务后主线程任务
//                            new MainTask<String>() {
//                                @Override
//                                public void done(TaskResult result) {
//                                    super.done(result);
//                                    String data = outData();
//                                    //更新主线程
//                                }
//                            })
//                    .execute();
//
//
//        }
//
//    }

    public static void test(final int ii) {
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
