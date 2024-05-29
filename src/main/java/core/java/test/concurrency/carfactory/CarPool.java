package core.java.test.concurrency.carfactory;

import java.util.*;

public class CarPool {

    private Map<String, List<ToyCar>> carsMap;

    private static CarPool instance;

    private CarPool() {
        carsMap = new HashMap<>();
        Set<Character> set = new HashSet<>();
        set.clear();
    }

    public static CarPool getInstance() {
        if (instance == null) {
            instance = new CarPool();
        }
        return instance;
    }

    public void addCar(ToyCar toyCar) {
        String model = toyCar.getModel();
        if (carsMap.containsKey(model)) {
            carsMap.get(model).add(toyCar);
        } else {
            List<ToyCar> list = new ArrayList<>();
            list.add(toyCar);
            carsMap.put(model, list);
        }
    }

    public List<String> getModels() {
        if (carsMap.isEmpty()) return Collections.emptyList();
        return new ArrayList<>(carsMap.keySet());
    }

    public List<ToyCar> getCarsByModel(String model) {
        if (carsMap.isEmpty()) return Collections.emptyList();
        return Collections.unmodifiableList(carsMap.get(model));
    }

}
