package com.patterns.programming.functional.Example2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceCTP {

    public static void main(String[] args)
    {
        ExecutorService service = Executors.newCachedThreadPool();
        /*
            Executor service with 'Cached thread pool' type
            instantiate a thread pool that creates new threads as needed,
            but reuse previously constructed threads when available.
            Here the thread-pool executor is defined with
            corePoolSize:0, maxPoolSize:Integer.MAX_VALUE, keepAliveTime:60 Sec,
            and workQueue: SynchronousQueue<Runnable>, which can
            only hold one task at a time.
            The cached pools will typically improve the performance
            of programs that execute many short-lived asynchronous tasks.
            Every time a task is submitted,
            the pool will hold this task in the sync queue and
            search for an idle thread for assignment.
            At a given time, If all threads are busy,
            then it creates a new thread and place it in the pool
            and assign it the task.
            Executor service kills threads that are idle for more than 60 seconds.
        */

        for (int i = 0; i<100; i++) {

            service.execute(new Task());
            /*
                Creating new 'runnable' tasks and
                submitting them to the ExecutorService for execution.
                Inside Executor Service, an idle or new thread
                fetch available task from the synchronous queue
                and execute the task (concurrently).
            */
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

