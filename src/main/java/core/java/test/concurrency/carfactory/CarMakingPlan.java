package core.java.test.concurrency.carfactory;

public class CarMakingPlan {

    private int carMakerId;
    private String carName;
    private String modelName;
    private int interval;
    private int quantity;

    public CarMakingPlan(int carMakerId, String carName, String modelName, int interval, int quantity) {
        this.carMakerId = carMakerId;
        this.carName = carName;
        this.modelName = modelName;
        this.interval = interval;
        this.quantity = quantity;
    }

    public int getCarMakerId() {
        return carMakerId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
