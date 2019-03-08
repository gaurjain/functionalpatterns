package com.patterns.programming.functional.Example5;

import java.util.Random;

public class ProducerConsumerPattern {

    public static void main(String[] args) {

        /* Create a blocking queue */
        MyBlockingQueue<Integer> queue = new MyBlockingQueue<>(10);

        /* Create a producer */
        final Runnable producer = () -> {
            if (true) {
                try {
                    Integer x = new Random().nextInt();
                    x = x/10000000;
                    queue.put(x);  // thread blocks
                    System.out.println("size of the queue after," + x + " is enqueued: " + queue.size());
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
                    System.out.println("size of the queue after, "+ i +" is dequeued: " + queue.size());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
        //new Thread(consumer).start();


        // Start one more producer thread
        //new Thread(producer).start();

        try { //minor delay in main thread
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

