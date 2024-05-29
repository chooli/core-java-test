package core.java.test.concurrency.carfactory;

import core.java.test.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CarFactory {

    private List<CarMakingPlan> plans;

    private CarPool carPool;

    public CarFactory(List<CarMakingPlan> carMakingPlans) {
        this.plans = carMakingPlans;
        carPool = CarPool.getInstance();
    }

    public static void main(String[] args) {

        List<CarMakingPlan> carMakingPlans = new ArrayList<>();
        carMakingPlans.add(new CarMakingPlan(1,"s", "sedan", 1, 10000));
        carMakingPlans.add(new CarMakingPlan(2,"3", "sedan", 1, 10000));
        carMakingPlans.add(new CarMakingPlan(3,"x", "sedan", 1, 10000));
        carMakingPlans.add(new CarMakingPlan(4,"y", "sedan", 1, 10000));
        carMakingPlans.add(new CarMakingPlan(5,"c", "sedan", 1, 10000));
        carMakingPlans.add(new CarMakingPlan(6,"c", "sedan", 1, 10000));
        carMakingPlans.add(new CarMakingPlan(7,"c", "sedan", 1, 10000));

        CarFactory carFactory = new CarFactory(carMakingPlans);

        try {
            //carFactory.startCarMakingThreads();
            carFactory.startCarMakingTasks();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void startCarMakingThreads() throws InterruptedException {
        System.out.println(String.format("Car factory has total %d car makers to run", plans.size()));

        ExecutorService executorService = Executors.newFixedThreadPool(plans.size());

        for( CarMakingPlan plan : this.plans ) {
            executorService.submit(new CarMakerThread(plan, null));
        }

        monitorCarPoolReport(10, null);

        System.out.print("Production threads started: ");

        //System.out.println("\nProduction threads stopped");
    }

    private void startCarMakingTasks() throws InterruptedException{
        System.out.println(String.format("Car factory has total %d car makers to run", plans.size()));

        Timer timer = new Timer();
        for( CarMakingPlan plan : this.plans ) {
            CarMakingTask task = new CarMakingTask(plan);
            timer.schedule(task, 0, plan.getInterval() * 10L);
        }

        System.out.print("Production tasks started: ");
        monitorCarPoolReport(3, timer);

        //timer.cancel();
    }

    private void monitorCarPoolReport(int duration, Timer timer) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Thread mornitoringThread = new Thread(() -> {
            //main thread is acting as a monitoring thread
            int cleanLength = 0;
            while (duration > stopWatch.secondPassed()) {
                try {
                    Thread.sleep(100);
                    cleanLength = printStatistic(duration, cleanLength, stopWatch);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (timer != null ) {
                timer.cancel();
            }

            printStatistic(duration, cleanLength, stopWatch);
            System.out.println("\nMonitoring report is ended");
            System.exit(0);
        });
        mornitoringThread.start();

    }

    private int printStatistic(int duration, int cleanLength, StopWatch stopWatch) {
        for (int i=0; i<cleanLength; i++) {
            System.out.print("\b");
        }
        StringBuilder sb = new StringBuilder();
        for (String model : carPool.getModels()) {
            sb.append(String.format("%s[%d] ", model, carPool.getCarsByModel(model).size()));
        }
        sb.append(String.format("| time spent: %dsec. | duration: %d", stopWatch.secondPassed(), duration));
        cleanLength = sb.toString().length();
        System.out.print(sb);
        return cleanLength;
    }

}
