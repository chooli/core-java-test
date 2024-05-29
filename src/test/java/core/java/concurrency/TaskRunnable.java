package core.java.concurrency;

import core.java.concurrency.CommonConcurrentTest.ExecutableTask;

public class TaskRunnable implements Runnable {

    private final ExecutableTask task;
    private final String taskName;
    private final String PREFIX = "TASK-";

    public TaskRunnable(String name, ExecutableTask task) {
        this.task = task;
        this.taskName = PREFIX + name;
    }

    @Override
    public void run() {
        try {
            Thread.currentThread().setName(this.taskName);
            task.execute();
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
