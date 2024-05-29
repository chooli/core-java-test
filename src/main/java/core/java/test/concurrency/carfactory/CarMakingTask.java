package core.java.test.concurrency.carfactory;

import java.util.TimerTask;
import java.util.UUID;

public class CarMakingTask extends TimerTask {

    private CarMakingPlan plan;

    public CarMakingTask(CarMakingPlan plan) {
        this.plan = plan;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (CarPool.class) {
            for (int i=0;i<plan.getQuantity();i++) {
                String id = new UUID(1l, 1000000l).toString();
                ToyCar toyCar = new ToyCar(id, plan.getCarName(), plan.getModelName());
                CarPool.getInstance().addCar(toyCar);
            }
        }
    }

}
