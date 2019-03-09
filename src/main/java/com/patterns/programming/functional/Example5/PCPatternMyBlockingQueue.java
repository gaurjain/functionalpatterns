package com.patterns.programming.functional.Example5;

import java.util.Random;

public class PCPatternMyBlockingQueue {

    public static void main(String[] args) {

        /* Create a blocking queue */
        MyBlockingQueue<Integer> queue = new MyBlockingQueue<>(10);

        /* Create a producer */
        final Runnable producer = () -> {
            if (true) {

                Integer x = new Random().nextInt();
                x = x/10000000;
                try {
                    queue.offer(x);  // add element to the queue after acquiring a lock.
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                System.out.println("size of the queue after," + x + " is enqueued: " + queue.size());
            }
        };

        /* Create a consumer */
        final Runnable consumer = () -> {
            if (true) {
                Integer i = null;
                try {
                    i = queue.poll(); // remove element from head of the queue after acquired a lock.
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("size of the queue after, "+ i +" is dequeued: " + queue.size());
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

