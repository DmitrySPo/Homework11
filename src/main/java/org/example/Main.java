package org.example;

public class Main {
    public static void main(String[] args) {

        ThreadPool pool1 = new FixedThreadPool(5);
        pool1.start();

        for (int i = 0; i < 10; i++) {
            pool1.execute(() -> {
                System.out.println("Задача выполнена в потоке " +
                        Thread.currentThread().getName());
            });
        }

        ThreadPool pool2 = new ScalableThreadPool(5,20);
        pool2.start();

        for (int i = 0; i < 15; i++) {
            pool2.execute(() -> {
                System.out.println("Задача выполнена в потоке " +
                        Thread.currentThread().getName());
            });
        }
        pool1.shutdown();
        pool2.shutdown();
    }
}