package chapter_6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ThreadPoolExecutorExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        IntStream.iterate(1, i -> i + 1)
                .limit(30)
                .parallel()
                .mapToObj(i -> (Runnable) () -> {
                    try {
                        Thread.sleep(2000);
                        System.out.println(Thread.currentThread().getName() + " : " + i);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                .forEach(executorService::execute);

        executorService.shutdown();
    }
}
