package com.patterns.programming.functional.Example4;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * How to Timeout a Thread?
 * Tip : Java threads cannot be killed,
 * They are cooperative in nature.
 * You need to ask them politely by using interrupts.
 */

public class TimeoutAThreadCallable {

    public static void main(String[] args)
    {   //step 1: instantiate a thread-pool.
        ExecutorService service = Executors.newFixedThreadPool(10);

        List<Future> allFutures = new ArrayList<>(); // Step 2: Declare and instantiate a list to hold all Futures

        //step 3: pass on callable tasks to the service thread pool for execution.
        for(int i =0 ; i<20; i++) {
            Future<Integer> future = service.submit(() -> {
                if (!Thread.currentThread().isInterrupted()) {
                    System.out.println("Hello World, Task bearer's name : " + Thread.currentThread().getName() + "..going to sleep");
                    try {
                        TimeUnit.SECONDS.sleep(30);
                    } catch (InterruptedException ex) {
                        throw new IllegalStateException(ex);
                    }
                    System.out.println("I am " + Thread.currentThread().getName() + " my sleep is over");
                    //return new Random().nextInt();
                }
                Integer randomnumber = new Random().nextInt();
                System.out.println("my number is:" + randomnumber);
                return randomnumber;
            });
            allFutures.add(future); //add the future to the array list

        }
        //step 4: print something
        System.out.println("main thread is executing steps in parallel...going to sleep");

        //step 5: timeout  main thread for 1 minute (unrelated operation)
        try {
            TimeUnit.SECONDS.sleep(30);
            System.out.println("main thread's sleep is over!!");
        }
        catch (InterruptedException ex) {
            throw new IllegalStateException(ex);
        }

        //step 6: stop the thread by requesting thread-pool shutdown.
        List<Runnable> runnables = service.shutdownNow();
        System.out.println((runnables));


    }

}
