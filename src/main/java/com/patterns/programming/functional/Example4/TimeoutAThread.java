package com.patterns.programming.functional.Example4;


import java.util.List;
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
    {   //step 1: instantiate a thread-pool.
        ExecutorService service = Executors.newFixedThreadPool(10);

        //step 2: pass on runnable tasks to the service thread pool for execution.
        for(int i =0 ; i<20; i++) {
            service.submit(() -> {
                if (!Thread.currentThread().isInterrupted()) {
                    try {
                        System.out.println("Hello World, Task bearer's name : " + Thread.currentThread().getName() + "..going to sleep");
                        TimeUnit.SECONDS.sleep(60);
                        System.out.println("I am "+ Thread.currentThread().getName() +" my sleep is over");
                    } catch (InterruptedException ex) {
                        throw new IllegalStateException(ex);
                    }
                }
            });
        }

        //step 3: print something
        System.out.println("main thread is executing steps in parallel...going to sleep");

        //step 4: timeout  main thread for 1 minute (unrelated operation)
        try {
            TimeUnit.MINUTES.sleep(1);
            System.out.println("main thread's sleep is over!!");
        }
        catch (InterruptedException ex) {
            throw new IllegalStateException(ex);
        }

        //step 5: stop the thread by requesting thread-pool shutdown.
        List<Runnable> runnables = service.shutdownNow();
        /*
        This internally calls thread.interrupt
        for all running threads.
        */

        //step 6: print not yet shutdown runnables tasks
        System.out.println((runnables));
    }

}
