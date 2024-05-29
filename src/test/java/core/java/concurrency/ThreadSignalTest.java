package core.java.concurrency;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ThreadSignalTest extends CommonConcurrentTest {

    private final CounterObject counterObject1 = new CounterObject(0);
    private final CounterObject counterObject2 = new CounterObject(1);
    private boolean wasSignalled = false;

    @Test
    @DisplayName("Two thread signal each other")
    void waitAndNotifyTest() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            println("T-1 get lock");
            synchronized (counterObject1) {
                while (!wasSignalled) {  // To prevent spurious wake-ups, make sure notify before wake up
                    try {
                        println("T-1 got lock and wait");
                        counterObject1.wait();
                        println("T-1 resume");
                        timeConsumingTask();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //clear signal and continue running.
                wasSignalled = false;
            }
            println("T-1 end");
        });

        Thread thread2 = new Thread(() -> {
            println("T-2 get lock");
            synchronized (counterObject1) {
                println("T-2 got lock");
                timeConsumingTask();
                println("T-2 notify");
                wasSignalled = true;
                counterObject1.notify();
            }
            println("T-2 end");
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        assertTrue(true);
    }

    @Test
    @DisplayName("Multiple thread get lock from share obj")
    void getLockFromObj() {
        this.setNumOfThreads(10);
        executeTaskWithThreads(() -> {
            synchronized (counterObject1) {
                synchronized (counterObject2) {
                    println(Thread.currentThread().getName() + " got lock");
                    //timeConsumingTask();
                }
            }
        });
        executeTaskWithThreads(() -> {
            synchronized (counterObject2) {
                synchronized (counterObject1) {
                    println(Thread.currentThread().getName() + " | got lock");
                    //timeConsumingTask();
                }
            }
        });

        assertTrue(true);
    }

    static class CounterObject {
        private int counter = 0;

        public CounterObject(int counter) {
            this.counter = counter;
        }
        public void increase() { this.counter++; }
        public synchronized void syncIncrease() { this.counter++; }
        public int getCounter() { return this.counter; }
    }
}
