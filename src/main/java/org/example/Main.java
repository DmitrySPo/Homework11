package org.example;

public class Main {
    public static void main(String[] args) {
        int threadCount = 5;

        MyThreadPool threadPool= new MyThreadPool(threadCount);
        threadPool.start();

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Runnable runnable = () -> {
                System.out.println("Задача " + finalI + " выполняется в потоке " +
                        Thread.currentThread().getName());
            };


            threadPool.execute(runnable);
        }
        threadPool.shutdown();
        }
}