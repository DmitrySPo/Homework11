package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScalableThreadPool implements ThreadPool {
    private final ExecutorService executorService;

    public ScalableThreadPool(int minThreads, int maxThreads) {
        executorService = new ThreadPoolExecutor(minThreads, maxThreads, 60L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>());
    }

    @Override
    public void start() {
        //потоки уже готовы к работе
    }

    @Override
    public void execute(Runnable runnable) {
        executorService.execute(runnable);
    }

    @Override
    public void shutdown() {
        executorService.shutdown();
    }
}