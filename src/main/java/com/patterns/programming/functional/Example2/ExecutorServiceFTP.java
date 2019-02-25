package com.patterns.programming.functional.Example2;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceFTP {

    public static void main(String[] args)
    {
        /*
        Instantiate a new executor service
        that maintains a pool of threads
        to be assigned tasks to be performed.
        */
        ExecutorService service = Executors.newFixedThreadPool(10);

        /*
        Fixed thread pool assures fixed number of threads
        available to fetch tasks from a blocking queue.
        In this case, as many as 10 threads are pooled upfront.
        */

        for (int i = 0; i<100; i++) {

            service.execute(new Task());
            /*
            Creating new 'runnable' tasks and
            submitting them to the ExecutorService for execution.
            These tasks are queued by the Executor service
            in an internal blocking queue, which is threadsafe.
            Inside Executor Service, each thread performs two tasks,
            fetch next task from blocking queue
            and execute the task (concurrently).
            All active threads attempt to take the tasks from the queue concurrently
            hence the queue has to be threadsafe.
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
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException ex) {
                throw new IllegalStateException(ex);
            }
            System.out.println("Hello World bhai, CPU Thread bearer's name : " + Thread.currentThread().getName());

        }
    }



}

