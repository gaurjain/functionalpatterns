package com.patterns.programming.functional;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Thread thread1 = new Thread(new Task());
        thread1.start();
        System.out.println( "Jai Jinendra !!! from Gaurav P Jain" );
        System.out.println("Thread name : " + Thread.currentThread().getName());

    }

    static class Task implements Runnable {
        public void run() {
            System.out.println("Hello World bhai, Thread name : " + Thread.currentThread().getName());
        }
    }
}
