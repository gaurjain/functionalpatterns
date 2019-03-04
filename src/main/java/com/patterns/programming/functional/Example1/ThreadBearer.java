package com.patterns.programming.functional.Example1;

import java.util.concurrent.TimeUnit;

/**
 * Hello world! Thread bearer
 *
 */
public class ThreadBearer
{
    public static void main( String[] args )
    {
        for (int i = 0; i<10; i++) {
            //loop to create a new threads and
            //assign a new instance of runnable task
            //to the thread.
            Thread thread = new Thread(new Task());
            thread.start(); //start the created thread.
        }
        System.out.println( "Jai Jinendra!!!from the task bearer Gaurav P Jain" );
        System.out.println("Task-bearer thread's name : " + Thread.currentThread().getName());
    }


    static class Task implements Runnable {
        //Definition of the Runnable task
        //which is is executed by a thread and
        //the control returned to the main thread
        //after the tasks are completed by the thread.

        public void run() {
            // Overriding the run method of the runnable object.
            System.out.println("Hello World!!!bhai,from the Thread named : " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException ex) {
                throw new IllegalStateException(ex);
            }

        }
    }
}
