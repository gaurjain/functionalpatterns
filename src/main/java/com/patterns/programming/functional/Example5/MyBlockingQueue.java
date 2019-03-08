package com.patterns.programming.functional.Example5;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


//Implementing my own Linked-list based blocking queue.
public class MyBlockingQueue<E> {

    private Queue<E> queue;
    private int max = 16;
    private ReentrantLock lock = new ReentrantLock(true);
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();


    public MyBlockingQueue(int size) {
        queue = new LinkedList<>();
        this.max = size;
    }

    // Put an element in the blocking queue by acquiring a lock on it.
    public void put(E e) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == max) {
                notFull.await();
            }
            queue.add(e);
            notEmpty.signalAll(); //Signal for not empty now.
        } finally {
            lock.unlock();
        }
    }

    // Taking an element from the blocking queue by acquiring a lock on it.
    public E take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == 0) {
                notEmpty.await();
            }
            E item = queue.remove();
            notFull.signalAll(); //Signal for not full now.
            return item;
        }finally {
            lock.unlock();
        }
    }
    //Size method
    public int size() {
        return queue.size();
    }
}

