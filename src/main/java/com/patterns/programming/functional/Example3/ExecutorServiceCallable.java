package com.patterns.programming.functional.Example3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ExecutorServiceCallable {

    public static void main(String[] args)
    {
        ExecutorService service = Executors.newCachedThreadPool();

        List<Future> allFutures = new ArrayList<>(); // A list to hold all Futures

        for (int i = 0; i<100; i++) {
            /*
                A {@code Future} represents the result of an asynchronous
                computation.  Methods are provided to check if the computation is
                complete, to wait for its completion, and to retrieve the result of
                the computation.  The result can only be retrieved using method
                {@code get} when the computation has completed, blocking if
                necessary until it is ready.  Cancellation is performed by the
                {@code cancel} method.
            */
            Future<Integer> future = service.submit(new Task()); //Future expecting an integer from the callable task.
            allFutures.add(future); //add the future to the array list.

        }    // 100 futures, with 100 placeholders.


        System.out.println("perform some unrelated operations"); // perform some unrelated operations

        for (int i = 0; i<100; i++) {
            Future<Integer> future = allFutures.get(i); //get the futures from the array list one at a time.
            try {  // check if the computation is complete.
                System.out.println("Is the future task " + i + " completed: " + future.isDone());
            } catch (CancellationException e) {
                e.printStackTrace();
            }

            try {
                Integer result = future.get(1000,TimeUnit.MILLISECONDS); //blocking operation,
                // wait for amount of 1 second and if future is not ready in that time, throw a timeout exception.
                System.out.println("Result of future "+ i + "=" + result);
            }
            catch (CancellationException e) {
                e.printStackTrace();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            catch (ExecutionException e) {
                e.printStackTrace();
            }
            catch (TimeoutException e) {
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

