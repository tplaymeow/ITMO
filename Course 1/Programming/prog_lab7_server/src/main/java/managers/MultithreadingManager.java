package managers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class MultithreadingManager {
    private static final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(25);
    private static final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    private static final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

    public static void fixedThreadPoolSubmit(Runnable task) {
        fixedThreadPool.submit(task);
    }

    public static void cachedThreadPoolSubmit(Runnable task) {
        cachedThreadPool.submit(task);
    }

    public static void forkJoinPoolSubmit(Runnable task) {
        forkJoinPool.submit(task);
    }

    public static void newThreadSubmit(Runnable task) {
        new Thread(task).start();
    }
}
