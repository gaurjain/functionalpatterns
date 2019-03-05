package com.patterns.programming.functional.Example2;

import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceFTPRejectionHandling {

    public static void main(String[] args)
    {
        ExecutorService service;
        service = new ThreadPoolExecutor(10,
                10,
                120,TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(50)); // A bounded queue of size 50.

        /*
           Rejection Handling using a Bounded ArrayBlockingQueue
           assuring that the executor pool
           gets more requests than what it can handle
           with a queue and thread pools combination.
        */

        for (int i = 0; i<100; i++) {

            try {
                   service.execute(new Task());

            }catch (RejectedExecutionException e) {
                System.err.println("task rejected"+ e.getMessage());
            }
            /*
                Create new 'Runnable' tasks and
                submitting them to the ExecutorService for execution.
                Inside Executor Service, each thread performs two tasks,
                fetch next task from blocking queue
                and execute the task (concurrently).

            */
        }

        System.out.println( "Jai Jinendra !!! from the main thread bearer Gaurav P Jain" );
        System.out.println("Main thread bearer name : " + Thread.currentThread().getName() + "\n");

        service.shutdown(); //The threads in the pool will exist until it is explicitly shutdown.
        System.out.println("Has shutdown begun "+ service.isShutdown());
        System.out.println("Are all tasks completed including the queued ones "+ service.isTerminated());
    }

    static class Task implements Runnable {
        public void run() {
            try {
                System.out.println("Hello World Bhai, CPU Thread bearer's name : " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException ex) {
                throw new IllegalStateException(ex);
            }
        }
    }



}

