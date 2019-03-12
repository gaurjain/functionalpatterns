package com.patterns.programming.functional.Example5;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class ScatterGatherPattern {

    //data fields
    private static String url1 = "www.amazon.com";
    private static String url2 = "www.alibaba.com";
    private static String url3 = "www.google.comm";
    private static Set<Integer> prices = Collections.synchronizedSet(new HashSet<>());

    //method belongs to the class and not the object and can be accessed from within.
    private static Set<Integer> getPrices()
    {
        CompletableFuture<Void> task1 = CompletableFuture.runAsync(new Task(url1, 1, prices));
        CompletableFuture<Void> task2 = CompletableFuture.runAsync(new Task(url2, 2, prices));
        CompletableFuture<Void> task3 = CompletableFuture.runAsync(new Task(url3, 5, prices));

        CompletableFuture<Void> allTasks = CompletableFuture.allOf(task1, task2, task3);

        try {
               allTasks.get(6, TimeUnit.SECONDS);

        }catch(TimeoutException ex)
        {
            System.out.println("some API futures response delayed");
        }catch(ExecutionException ex)
        {
            throw new IllegalStateException(ex);
        }catch(InterruptedException ex)
        {
            throw new IllegalStateException(ex);
        }finally{
            return prices;
        }
    }

    private static class Task implements Runnable {

        //fields
        private String urlname;
        private int productId;
        private int price;

        //constructor
        public Task(String urlname, int productId, Set<Integer> prices)
        {
            this.urlname = urlname;
            this.productId=productId;
        }

        @Override
        public void run() {
            try {
                    System.out.println("Hello World bhai, CPU Thread bearer's name : " + Thread.currentThread().getName());
                    TimeUnit.SECONDS.sleep(productId);
                    price = productId ;
                    //http call
                    prices.add(price);

            } catch (InterruptedException ex) {
                    throw new IllegalStateException(ex);
            }
        }
    }

    public static void main(String[] args)
    {

        System.out.println("The prices from all the vendors are " + getPrices());

    }

}
