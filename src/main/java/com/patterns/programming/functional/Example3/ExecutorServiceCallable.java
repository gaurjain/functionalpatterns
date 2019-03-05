package com.patterns.programming.functional.Example3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ExecutorServiceCallable {

    public static void main(String[] args)
    {
        ExecutorService service = Executors.newFixedThreadPool(100);

        List<Future> allFutures = new ArrayList<>(); // A list to hold all Futures

        for (int i = 0; i<100; i++) {
            Future<Integer> future = service.submit(new Task()); //Future expecting an integer from the callable task.
            allFutures.add(future); //add the future to the list.
        }    // 100 futures, with 100 placeholders.

        // perform some unrelated operations
        System.out.println("perform some unrelated operations");

        for (int i = 0; i<100; i++) {
            Future<Integer> future = allFutures.get(i);
            try {
                Integer result = future.get(1000,TimeUnit.MILLISECONDS); //blocking operation,
                // wait for amount of 1 second and if future is not ready in that time, throw a timeout exception.
                System.out.println("Result of future "+ i + "=" + result);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                System.out.println("Couldn't complete task before timeout");
            }
        }

        System.out.println("the main thread is unblocked and runnable again, since all futures have received values from the callable task.");
        service.shutdown(); //The threads in the pool will exist until it is explicitly shutdown.
        System.out.println("Executor Service is shutting down: "+ service.isShutdown());

    }

    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return new Random().nextInt();
        }

    }

}

