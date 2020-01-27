package pingpong;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Stephen Gerkin
 * Programming Lab 02 - PingPong
 * Program to practice multiple threads acting in a controlled for loop concurrently
 * Works by creating a log and using conditions on the lock to allow printing.
 * You cannot determine which thread will execute first, (Ping! or Pong!), but
 * once it starts, the order should always be the same.
 */
public class Driver {
    public static void main(String[] args) {
        // create PingPongs and put them in a list
        ArrayList<PingPong> pingPongs = new ArrayList<PingPong>() {
            {
                add(new PingPong("Ping!"));
                add(new PingPong("Pong!"));
                /*
                // Uncomment this loop to show thread unpredictability.
                // Once order is determined, it should always be the same
                // but the threads will not always execute in sequential order
                // from the list.

                // Make sure to change sleepTime in PingPong before doing this
                // or you'll be sitting around waiting for 20 minutes!
                for (int i = 0; i < 10; i++) {
                    add(new PingPong("DEBUG " + i));
                }
                 */
            }
        };

        // create a thread pool for the PingPongs
        ExecutorService executorService = Executors.newFixedThreadPool(pingPongs.size());

        // execute all the PingPongs
        for (PingPong pingPong : pingPongs) {
            executorService.execute(pingPong);
        }
        // shut down the thread pool
        executorService.shutdown();

        // wait for threads to finish
        while (!executorService.isTerminated()) {
            //debug_printWaitEverySecond();
        }

        // signal program is finished
        System.out.println("All done!");
    }

    /**
     * For verifying the threads are still running. If the executor is still
     * "running" but nothing is printing to console, this will keep printing
     * to signal the executor is not terminated when it should be.
     */
    private static void debug_printWaitEverySecond() {
        System.out.println("Waiting for termination...");
        try {
            Thread.sleep(1_000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}