package com.patterns.programming.functional.Example2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceCTP {

    public static void main(String[] args)
    {
        /*
        Instantiate a new executor service
        that maintains a pool of threads
        to be assigned tasks to be performed.
        */
        ExecutorService service = Executors.newCachedThreadPool();

        /*
        Executor service with 'Cached thread pool' type
        is defined with a synchronous queue that can
        only hold one task at a time.
        Every time a task is submitted,
        the pool will hold this task in the sync queue and
        search for an idle thread for assignment.
        At a given time, If all threads are busy,
        then it creates a new thread and
        place it in the pool and assign it the task.
        Executor service kills threads that are idle for more than 60 seconds.
        */

        for (int i = 0; i<100; i++) {

            service.execute(new Task());
            /*
            Creating new 'runnable' tasks and
            submitting them to the ExecutorService for execution.
            Inside Executor Service, an idle or new thread fetch next task
            from the synchronous queue
            and execute the task (concurrently).
            All threads attempt to take the tasks from the queue concurrently
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
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                throw new IllegalStateException(ex);
            }
            System.out.println("Hello World bhai, CPU Thread bearer's name : " + Thread.currentThread().getName());

        }
    }



}

