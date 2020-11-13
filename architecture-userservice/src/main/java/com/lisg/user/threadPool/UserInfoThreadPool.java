package com.lisg.user.threadPool;

import java.util.concurrent.*;

/**
 * Created by lisg on 2018/11/5.
 */
public class UserInfoThreadPool {

    private static final int QUEUE_SIZE = 10000;

    private static final int THREAD_SIZE = Runtime.getRuntime().availableProcessors() / 2;

    private static ExecutorService userInfoLoader = new ThreadPoolExecutor(THREAD_SIZE,
            THREAD_SIZE,
            0L,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(QUEUE_SIZE),
            r -> {
                Thread t = new Thread(r);
                t.setName("load_user_info");
                return t;
            },
            new RejectedExecutionHandler() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                    System.err.println("------------打印拒绝策略---------");
                    //do something ......
                }
            });

    public static void submit(Runnable task) {
        userInfoLoader.submit(task);
    }
}
