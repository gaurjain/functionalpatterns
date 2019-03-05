package com.patterns.programming.functional.Example2;

import java.util.concurrent.*;

public class ExecutorServiceFTPRejectionHandling {

    public static void main(String[] args)
    {
        ExecutorService service;
        service = new ThreadPoolExecutor(10,
                100,
                120,TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(300)); // A bounded queue of size 300.
        try {
            service.execute(new Task());

        }catch (RejectedExecutionException e) {
            System.err.println("task rejected"+ e.getMessage());
        }
        /*


        */

        for (int i = 0; i<400; i++) {

            service.execute(new Task());
            /*
                Create new 'Runnable' tasks and
                submitting them to the ExecutorService for execution.
                Inside Executor Service, each thread performs two tasks,
                fetch next task from blocking queue
                and execute the task (concurrently).

            */
        }

        System.out.println( "Jai Jinendra !!! from the main thread bearer Gaurav P Jain" );
        System.out.println("Main thread bearer name : " + Thread.currentThread().getName());
        // service.shutdown(); //The threads in the pool will exist until it is explicitly shutdown.
        List<Runnable> runnables = service.shutdownNow();
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

