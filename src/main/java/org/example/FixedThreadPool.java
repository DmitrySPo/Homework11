package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool implements ThreadPool {
    private final ExecutorService executorService;

    public FixedThreadPool(int numThreads) {
        executorService = Executors.newFixedThreadPool(numThreads);
    }

    @Override
    public void start() {
        // потоки уже готовы к работе
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