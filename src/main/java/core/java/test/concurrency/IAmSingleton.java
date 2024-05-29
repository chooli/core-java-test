package core.java.test.concurrency;

public enum IAmSingleton {
    INSTANCE;

    private String name;

    public String getName() {
        return name;
    }

}
