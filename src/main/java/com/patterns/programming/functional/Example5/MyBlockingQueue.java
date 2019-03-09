package com.patterns.programming.functional.Example5;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


//Implementing my own Linked-list based blocking queue.
public class MyBlockingQueue<E> {

    /* First instantiate a Queue collection for holding elements
     * prior to processing and applying queue operations - insertion, extraction, and inspection operations.
     * Set the max size to 16.
    */
    private Queue<E> queue;
    private int max = 16;

    /* Create a reentrant mutual exclusion object.
     * A ReentrantLock is owned by the thread
     * last successfully locking, but not yet unlocking it.
     * A thread invoking lock will return, successfully acquiring the lock,
     * when the lock is not owned by another thread.
     * The method will return immediately if the current thread already owns the lock.
     * Instantiate the object with fairness parameter set {@code true}.
     * A 'True' parameter value assures that when under contention,
     * locks favor granting access to the longest-waiting thread.
    */
    private ReentrantLock lock = new ReentrantLock(true);

    /* Create condition queues or condition variables objects
     * - notFull and notEmpty - to provide a means for one thread
     * to suspend execution(to wait) while the queue is full or Empty,
     * and until notified by another thread that some state condition may now be true.
     *
     * The reentrant lock associated with these conditions is atomically released and
     * the current thread becomes disabled for thread scheduling
     * purposes and lies dormant until signalled by another threads.
     * 'not Full' or 'not Empty' condition object causes the current thread to wait
     * until it is signalled or Thread is interrupted.
     */
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public MyBlockingQueue(int size) {
        queue = new LinkedList<>(); //Initialize queue as a linked list.
        this.max = size;           // Set the max with the parameter received.
    }

    // Put an element in the blocking queue by acquiring a lock on it.
    public void offer (E e) throws InterruptedException {
        lock.lock(); //Thread to acquire lock on the queue, before performing the put.
        try {
                while (queue.size() == max)
                {    //while queue size is max, make the thread(s) release the lock
                    // and wait until queue size is not max.
                    notFull.await();
                }
                    queue.add(e);         //add element to the queue.
                    notEmpty.signalAll();//Wakes up all waiting threads and signal that the queue is 'not empty' now.
        } finally
        {
            lock.unlock(); //make the thread release the lock on the queue
        }
    }

    // removing an element from the blocking queue by acquiring a lock on it.
    public E poll() throws InterruptedException {
        lock.lock(); //Thread acquire lock on the queue, before removing element from the queue.
        try {
            //while the queue is empty,
            //cause the current thread to wait until queue is not empty, and release the lock.
            while (queue.size() == 0) {
                notEmpty.await();
            }
            E item = queue.remove();
            notFull.signalAll(); //Wakes up all waiting threads and signal that the queue is 'not full' now.
            return item;
        }finally {
            lock.unlock(); // make the thread release the lock on the queue.
        }
    }
    //Size method
    public int size() {
        return queue.size();
    }
}

