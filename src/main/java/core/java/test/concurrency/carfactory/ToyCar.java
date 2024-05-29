package core.java.test.concurrency.carfactory;

public class ToyCar {

    private String carId;

    private String name;

    private String model;

    public ToyCar(String carId, String name, String model) {
        this.carId = carId;
        this.name = name;
        this.model = model;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

}
