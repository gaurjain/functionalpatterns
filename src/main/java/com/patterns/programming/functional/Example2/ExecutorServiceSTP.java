package com.patterns.programming.functional.Example2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceSTP {

    public static void main(String[] args)
    {
        /*
        Instantiate a new executor service
        that maintains a pool of threads
        to be assigned tasks to be performed.
        */
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

        /*
        Executor service with 'Scheduled thread pool' type
        helps to queue tasks in a delay queue,
        schedule tasks to run based on a time delay
        and retrigger for fixed rate or fixed delay.
        */

        for (int i = 0; i<20; i++) {

            //keep triggering tasks after 10 seconds delay
            service.schedule(new Task(),10,TimeUnit.SECONDS);

            //keep triggering tasks repeatedly after every 10 second
            service.scheduleAtFixedRate(new Task(),15,10,TimeUnit.SECONDS);

            //keep triggering tasks repeatedly after 10 seconds of every previous completion
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

