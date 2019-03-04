package com.patterns.programming.functional.Example2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceSingleTP {

    public static void main(String[] args)
    {
        /*
        Instantiate a new executor service
        that maintains only one threads
        to be assigned tasks to be performed from the blocking queue.
        */
        ExecutorService service = Executors.newSingleThreadExecutor();

        /*
        Single thread pool assures that single thread takes
        one task at a time and ensure
        that task 1 runs before task 2 and so forth.

        */

        for (int i = 0; i<10; i++) {

            service.execute(new Task());
            /*
            Creating new 'runnable' tasks and
            submitting them to the ExecutorService for execution.
            These tasks are queued by the Executor service
            in an internal blocking queue, which is threadsafe.
            Inside Executor Service, the thread performs two tasks,
            fetch next task from blocking queue
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
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException ex) {
                throw new IllegalStateException(ex);
            }
            System.out.println("Hello World bhai, CPU Thread bearer's name : " + Thread.currentThread().getName());

        }
    }



}

