package com.patterns.programming.functional.Example5;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerPattern {

    public static void main(String[] args) {

        /* Create a blocking queue */
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

        /* Create a producer */
        final Runnable producer = () -> {
            if (true) {
                try {
                    Integer x = new Random().nextInt();
                    x = x/10000000;
                    queue.put(x);  // thread blocks
                    System.out.println("size of the queue post " + x + " is enqueued " + queue.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        /* Create a consumer */
        final Runnable consumer = () -> {
            if (true) {
                Integer i = null;
                    try {
                        i = queue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                System.out.println("size of the queue after "+ i +" is dequed " + queue.size());
                process(i); //process i
            }
        };

        //-------------

        // Start producer threads
        new Thread(producer).start();
        new Thread(producer).start();
        new Thread(producer).start();
        new Thread(producer).start();


        // Start Consumer threads
        new Thread(consumer).start();
        new Thread(consumer).start();
        new Thread(consumer).start();
        new Thread(consumer).start();

        // Start one more producer thread
        //new Thread(producer).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("final queue size "+ queue.size());

    } // main ends

    private static void process(Integer i) {
        System.out.println("unprocessed value :" + i);
        int y = i + 200;
        System.out.println("processed value :" + y);

    }

}
