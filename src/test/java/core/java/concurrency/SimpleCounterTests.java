package core.java.concurrency;

import core.java.CommonTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleCounterTests extends CommonConcurrentTest {

    private static Integer counter = 0;

    @Test
    @DisplayName("Dead loop in task 1 -> manually stop the run!")
    void invisibleWriteTest() {
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
                    Thread.sleep(1);
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

        assertTrue(true);
    }

    @Test
    @DisplayName("Race condition with threads")
    void increaseShareCounter() {
        executeTaskWithThreads(() -> {
            for (int i=0;i<1000;i++) {
                counter++;
            }
            System.out.println("Thread:"+Thread.currentThread().getName()+" counter:" + counter);
        });

        assertTrue(true);
    }

    @Test
    @DisplayName("Thread safe counter update")
    void increaseLocalCounter() {
        executeTaskWithThreads(() -> {
            int localCounter = 0;
            for (int i=0;i<10000;i++) {
                localCounter++;
            }
            System.out.println("Thread:"+Thread.currentThread().getName()+" localCounter: " + localCounter);
        });

        assertTrue(true);
    }

}
