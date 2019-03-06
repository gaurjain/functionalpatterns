package com.patterns.programming.functional.Example1;

import java.util.concurrent.TimeUnit;

/**
 * Hello world! Thread bearer
 *
 */
public class ThreadBearerLambda
{
    public static void main( String[] args )
    {
        for (int i = 0; i<10; i++) {
            //loop to create a new threads and
            //assign a new instance of runnable task
            //to the thread.
            Thread thread = new Thread(()->{
                //task step 1
                System.out.println("Hello World,from the Thread named : " + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(30);
                } catch (InterruptedException ex) {
                    throw new IllegalStateException(ex);
                }
                //task step 2
                //task step 3
            });

            thread.start(); //start the created task/thread.
        }

        System.out.println( "Jai Jinendra!!!from the task bearer Gaurav P Jain" );
        System.out.println("Task-bearer thread's name : " + Thread.currentThread().getName());
    }



}
