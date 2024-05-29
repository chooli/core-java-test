package core.java.test.concurrency;

import core.java.test.util.CalculateUtils;
import core.java.test.util.StopWatch;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleThreadTest {

    private static int numOfThreads = 2;
    private static volatile Integer counter = 0;
    private static boolean counterUpdated = false;
    private static final ThreadLocal<Integer> localCounter = ThreadLocal.withInitial(() -> {
        return 0;
    });
    private static final AtomicInteger atomicCounter = new AtomicInteger(0);
    private static final MyThreadObject counterObj = new MyThreadObject(0);

    private Semaphore sem = new Semaphore(2);

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        //runParallelThreads();
        runDifferentThreads();
        stopWatch.stop();
        System.out.println("time spent: " + stopWatch);
    }

    private static void runDifferentThreads() {
        Thread thread1 = new Thread(() -> {
            while(counter < 10) {
                if (counter == 5) {
                    System.out.print("^_^");
                }
            }
            System.out.println("Thread:"+Thread.currentThread().getName() + " counter: " + counter);
        }, "TASK-1");

        Thread thread2 = new Thread(() -> {
            try {
                for (int i=0;i<10;i++) {
                    counter++;
                    System.out.println("Thread:"+Thread.currentThread().getName()+" counter:" + counter);
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "TASK-2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void runParallelThreads() {
        List<Thread> threadList = new ArrayList<>(numOfThreads);
        for (int i=0;i<numOfThreads;i++) { threadList.add(Thread.ofVirtual().start(new TaskRunnable(i))); }
        counterUpdated = true;
        try {
            for (int i=0;i<numOfThreads;i++) {
                threadList.get(i).join(); // main thread wait for all child threads to complete
            }
            //Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void timeConsumingTask() {
        BigInteger factorial = CalculateUtils.factorial(BigInteger.valueOf(20000));
        System.out.println(factorial);
    }

    private static void increaseThreadLocalCounterTask() {
        for (int i=0;i<1000;i++) {
            localCounter.set(localCounter.get()+1);
        }
        System.out.println("Thread:"+Thread.currentThread().getName()+" threadLocalCounter:" + localCounter.get());
        localCounter.remove();
    }

    /**
     * With atomic counter, all the increment should be counted but the print out of each thread is unpredictable.
     * However, the max count should be numOfThreads times i here
     */
    private static void increaseAtomicCounter() {
        for (int i=0;i<10000;i++) {
            atomicCounter.incrementAndGet();
        }
        System.out.println("Thread:"+Thread.currentThread().getName()+" atomicCounter:" + atomicCounter.get());
    }

    private static void increaseCounterObj() {
        for (int i=0;i<10000;i++) {
            counterObj.syncIncrease();
        }
        System.out.println("Thread:"+Thread.currentThread().getName()+" counterObj:" + counterObj.getCounter());
    }

    static class TaskRunnable implements Runnable {

        private final int num;

        public TaskRunnable(int i) { this.num = i; }

        @Override
        public void run() {
            try {
                Thread.currentThread().setName("TASK-" + this.num);
                increaseThreadLocalCounterTask();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class MyThreadObject {
        private int counter = 0;

        public MyThreadObject(int counter) {
            this.counter = counter;
        }
        public void increase() { this.counter++; }
        public synchronized void syncIncrease() { this.counter++; }
        public int getCounter() { return this.counter; }
    }

}
