package core.java.test.concurrency;

import core.java.test.util.StopWatch;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleDeadLocakTest {

    private int counter = 0;
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();
    private volatile int vcounter = 0;

    private void increaseVCounter() {
        vcounter++;
    }

    private void increaseCounter() throws InterruptedException {
        //wait(100);
        counter++;
    }

    private void runDeadLock() {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(100);

                lock1.lock();
                lock2.lock();
                for (int i=0; i<10000; i++) {
                    increaseCounter();
                }
                lock1.unlock();
                lock2.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(100);

                lock2.lock();
                lock1.lock();
                for (int i=0; i<10000; i++) {
                    increaseCounter();
                }
                lock2.unlock();
                lock1.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        SimpleDeadLocakTest test = new SimpleDeadLocakTest();
        test.runDeadLock();

        stopWatch.stop();

        System.out.println("time spent: " + stopWatch);
        System.out.println("counter: " + test.getCounter());
    }

    private int getCounter() {
        return counter;
    }

    private int getVCounter() {
        return vcounter;
    }

}
