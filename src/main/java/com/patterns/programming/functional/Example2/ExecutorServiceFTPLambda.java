package com.patterns.programming.functional.Example2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceFTPLambda {

    public static void main(String[] args)
    {
        ExecutorService service = Executors.newFixedThreadPool(10);
        /*
            Instantiate a new {@code thread-pool} executor service
            that maintains a fixed pool of threads with
            corePoolSize:10, maxPoolSize:10, keepAliveTime:0Sec (not applicable)
            and workQueue:LinkedBlockingQueue<Runnable>
            to be assigned Runnable 'tasks' to be performed.
            Fixed thread pool assures fixed number of threads
            available to fetch {@code Runnable} 'tasks' from
            a shared,unbounded, blocking queue, which is threadsafe.
            At any point, at most 'nThreads' threads
            will be active processing tasks.
            If additional tasks are submitted when all threads are active,
            they will wait in the unbounded queue until a thread is available.
        */

        for (int i = 0; i<100; i++) {
            // creation and submission of the runnable task
            // to the service thread pool for execution using lambda expression ()->{}
            //service.execute(new Task());
            service.execute(()->{
                //task step 1
                try {
                System.out.println("Hello World bhai, CPU Thread bearer's name : " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(5);
                }
                catch (InterruptedException ex) {
                throw new IllegalStateException(ex);
                }
            });
            /*
                Create new 'Runnable' tasks and
                submitting them to the ExecutorService for execution.
                Inside Executor Service, each thread performs two tasks,
                fetch next task from blocking queue
                and execute the task (concurrently).
                All active threads attempt to take the
                tasks from the queue concurrently, and
                hence the queue has to be threadsafe.
            */
        }

        System.out.println( "Jai Jinendra !!! from the main thread bearer Gaurav P Jain" );
        System.out.println("Main thread bearer name : " + Thread.currentThread().getName());
        service.shutdown(); //The threads in the pool will exist until it is explicitly shutdown.
        //List<Runnable> runnables = service.shutdownNow();
        System.out.println("Executor Service is shutting down: "+ service.isShutdown());
        //System.out.println((runnables));
    }





}

