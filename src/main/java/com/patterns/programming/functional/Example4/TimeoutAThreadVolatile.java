package com.patterns.programming.functional.Example4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class TimeoutAThreadVolatile {

    public static void main(String[] args)
    {
        //Step 1: Create a task and submit it to a thread.
        Task task = new Task();
        Thread t1 = new Thread(task);
        t1.start();

        //Step 2: Print something
        System.out.println("main thread is executing steps in parallel...going to sleep");

        //Step 3: Timeout main thread for 1 minute (unrelated operation)
        try {
            TimeUnit.SECONDS.sleep(30);
            System.out.println("main thread's sleep is over!!");
        }
        catch (InterruptedException ex) {
            throw new IllegalStateException(ex);
        }

        //Step 4: Stop the thread by requesting thread-pool shutdown.
        task.stop();
        System.out.println(task.keepRunning);
        //service.shutdownNow();

    }

    static class Task implements Runnable {

        public AtomicBoolean keepRunning = new AtomicBoolean(true);

        @Override
        public void run() {
            while (keepRunning.get() == true) {
                try{
                    System.out.println("Hello World, Task bearer's name : " + Thread.currentThread().getName() + "..going to sleep");
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("I am " + Thread.currentThread().getName() + " my sleep is over");
                } catch (InterruptedException ex) {
                    throw new IllegalStateException(ex);
                }
            }

            System.out.println("I died");
        }

        public void stop(){
            keepRunning.set(false);
        }

    }

}
