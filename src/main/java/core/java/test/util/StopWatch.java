package core.java.test.util;

public class StopWatch {

    private long startTime;
    private long stopTime;

    /**
     starting the stop watch.
     */
    public void start(){
        startTime = System.nanoTime();
    }

    /**
     stopping the stop watch.
     */
    public void stop() { stopTime = System.nanoTime(); }

    public long millionSecondPassed() { stop(); return time() / 1000000; }

    public long secondPassed() { stop(); return time() / 1000000000; }

    /**
     elapsed time in nanoseconds.
     */
    private long time(){
        return (stopTime - startTime);
    }

    public String toString(){
        return "elapsed time: " + millionSecondPassed() + " millisecond.";
    }
}
