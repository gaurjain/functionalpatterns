package com.patterns.programming.functional.Example4;


import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * How to Timeout a Thread?
 * Tip : Java threads cannot be killed,
 * They are cooperative in nature.
 * You need to ask them politely by using interrupts or volatile.
 */

public class TimeoutAThread {

    public static void main(String[] args)
    {   //step 1: instantiate a threadpool.
        ExecutorService service = Executors.newCachedThreadPool();
        //step 2: pass on a task to the service thread pool for execution.
            service.submit(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        System.out.println("Hello World, Task bearer's name : " + Thread.currentThread().getName());
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException ex) {
                        throw new IllegalStateException(ex);
                    }
                }
            });

        //step 3: print something
        System.out.println("main thread is executing steps in parallel");
        //step 4: timeout for 1 minute (unrelated operation
        try {
            TimeUnit.MINUTES.sleep(1);
            System.out.println("main thread's sleep is over!!");
        }
        catch (InterruptedException ex) {
            throw new IllegalStateException(ex);
        }

        //step 5: stop the thread by requesting thread-pool shutdown.
        List<Runnable> runnables = service.shutdownNow();
        System.out.println((runnables));
        //This internally calls thread.interrupt
        // for all running threads.
    }

}
