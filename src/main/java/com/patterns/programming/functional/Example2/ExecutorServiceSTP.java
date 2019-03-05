package com.patterns.programming.functional.Example2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceSTP {

    public static void main(String[] args)
    {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        /*
            Executor service with 'Scheduled thread pool' type
            helps to queue tasks in a delay queue,
            It schedule tasks to run based on a time delay
            and re-trigger for fixed rate or fixed delay.
            It maintains a fixed pool of threads with
            corePoolSize:10, maxPoolSize:Integer.MAX_VALUE, keepAliveTime:0 Sec
            and workQueue: delayedWorkQueue to be assigned Runnable 'tasks' to be performed.
            It returns the runnable tasks to a thread for which the scheduled-time has passed.

            A unbounded,blocking 'DelayedWorkQueue' is based on a heap-based data structure
            in this implementation, every ScheduledFutureTask also records
            its index into the heap array.
            This eliminates the need to find a task upon cancellation,
            greatly speeding up removal (down from O(n)
            to O(log n)), and reducing garbage retention that would
            otherwise occur by waiting for the element to rise to top
            before clearing.
        */

        for (int i = 0; i<20; i++) {

            //Keep triggering tasks with the first task enabled after 10 seconds delay from now.
            service.schedule(new Task(),10,TimeUnit.SECONDS);

            //keep triggering tasks repeatedly after every 10 second
            //with first task enabled after 15 sec delay from now.
            service.scheduleAtFixedRate(new Task(),15,10,TimeUnit.SECONDS);

            //keep triggering tasks repeatedly after 10 seconds of every previous completion
            //with first task enabled after 15 sec from now.
            service.scheduleWithFixedDelay(new Task(),15,10,TimeUnit.SECONDS);

        }
        System.out.println( "Jai Jinendra !!! from the main thread bearer Gaurav P Jain" );
        System.out.println("Main thread bearer name : " + Thread.currentThread().getName());
        service.shutdown();
        System.out.println("Executor Service is shutting down: "+ service.isShutdown());
    }

    static class Task implements Runnable {
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                throw new IllegalStateException(ex);
            }
            System.out.println("Hello World bhai, CPU Thread bearer's name : " + Thread.currentThread().getName());

        }
    }



}

