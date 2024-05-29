package core.java.test.concurrency.carfactory;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CarMakerThread implements Runnable {

    private CarMakingPlan plan;
    private int secondCounter = 1;
    private final CountDownLatch latch;
    private final Lock lock = new ReentrantLock();

    public CarMakerThread(CarMakingPlan plan) {
        latch = null;
        this.plan = plan;
    }

    public CarMakerThread(CarMakingPlan plan, CountDownLatch latch) {
        this.plan = plan;
        this.latch = latch;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int interval = plan.getInterval();
            if (secondCounter % interval == 0) {
                for (int i=0;i<plan.getQuantity();i++) {
                    String id = new UUID(1l, 1000000l).toString();
                    ToyCar toyCar = new ToyCar(id, plan.getCarName(), plan.getModelName());
                    synchronized (CarPool.class) {
                        CarPool.getInstance().addCar(toyCar);
                    }
                }
            }
            secondCounter++;

            if (latch != null) latch.countDown();
        }
    }

}
