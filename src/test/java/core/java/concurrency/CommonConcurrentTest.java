package core.java.concurrency;

import core.java.CommonTest;
import core.java.test.util.CalculateUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class CommonConcurrentTest extends CommonTest{

    protected int numOfThreads = 2;

    protected void setNumOfThreads(int numOfThreads) {
        this.numOfThreads = numOfThreads;
    }

    protected void executeTaskWithThreads(ExecutableTask task) {
        List<Thread> threadList = new ArrayList<>(numOfThreads);
        for (int i=0; i<numOfThreads; i++) { threadList.add(Thread.ofVirtual().start(new TaskRunnable(String.valueOf(i), task))); }
        try {
            for (int i=0; i<numOfThreads; i++) { threadList.get(i).join(); }
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void timeConsumingTask() {
        println("start time consuming calculation...");
        BigInteger factorial = CalculateUtils.factorial(BigInteger.valueOf(50000));
        println("time consuming calculation is done");
    }

    @FunctionalInterface
    public interface ExecutableTask {
        void execute();
    }
}
