package org.example;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThreadPool implements ThreadPool {
    private  final ExecutorService executorService;

    public MyThreadPool(int threadCount) {
        executorService = Executors.newFixedThreadPool(threadCount);
    }

    @Override
    public void start() {
        // потоки уже созданы при инициализации пула потоков

    }

    @Override
    public void execute(Runnable runnable) {
        executorService.execute(runnable);
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
