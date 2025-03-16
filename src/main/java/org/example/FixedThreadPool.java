package org.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedThreadPool implements ThreadPool {
    private final BlockingQueue<Runnable> taskQueue;
    private final AtomicInteger threadCounter;
    private volatile boolean isShutdown;

    public FixedThreadPool(int numThreads) {
        if (numThreads <= 0) {
            throw new IllegalArgumentException("Количество потоков должно быть больше нуля");
        }

        this.taskQueue = new ArrayBlockingQueue<>(100);
        this.threadCounter = new AtomicInteger(1);
        this.isShutdown = false;

        for (int i = 0; i < numThreads; i++) {
            createWorker();
        }
    }

    private void createWorker() {
        new Worker(this).start();
    }

    @Override
    public void start() {
        // Потоки уже запущены
    }

    @Override
    public void execute(Runnable runnable) {
        if (isShutdown) {
            throw new IllegalStateException("Пул потоков остановлен");
        }
        try {
            taskQueue.put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void shutdown() {
        isShutdown = true;
        while (!taskQueue.isEmpty()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private class Worker extends Thread {
        private final FixedThreadPool pool;

        public Worker(FixedThreadPool pool) {
            super("Worker-" + threadCounter.getAndIncrement());
            this.pool = pool;
        }

        @Override
        public void run() {
            Runnable task;
            while (!pool.isShutdown) {
                try {
                    task = pool.taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}