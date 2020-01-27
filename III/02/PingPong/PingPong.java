package pingpong;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * PingPong class prints a given word to the console and waits 10 seconds.
 * This class implements Runnable and is designed to work alongside another
 * PingPong object to alternatively print to the console and wait.
 * It does this by issuing a lock when printing a word, printing the word, then
 * awaiting a signal that a word has been printed. Each time through the loop,
 * the object signals that it has printed a word.
 */
public class PingPong implements Runnable {
    // lock and condition for creating synchronization and preventing deadlock
    private static Lock lock = new ReentrantLock();
    private static Condition printed = lock.newCondition();

    // word to print
    private String word;

    // constants to use for how many times to print a word and how long to wait between printing
    private static final int LOOPS = 10;
    private static final long SLEEP_TIME = 10_000; // 10 seconds

    public PingPong(String word) {
        this.word = word;
    }

    /**
     * This method prints a word to the console and waits for any other instances
     * of PingPong to print a word before looping and printing again.
     * It signals during each loop that something has been printed, allowing the lock
     * condition to be met and other objects to use the method.
     * Dead lock is prevented by again signalling that the loop is finished by
     * using signalAll after the for loop is run. Without this, the executor
     * that runs any threads will never terminate.
     */
    private void printWord() {
        // start by locking the method
        lock.lock();

        try {
            for (int i = 0; i < LOOPS; i++) {
                System.out.println(word);
                // let everyone know that something has been printed to the console
                // this lets anyone else use the method
                printed.signalAll();
                // wait for a bit
                Thread.sleep(SLEEP_TIME);
                // wait to hear that printing has occurred
                printed.await();
            }
            // prevent deadlock by signalling the loop is finished
            printed.signalAll();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        printWord();
    }
}