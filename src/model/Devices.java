package model;

public abstract class Devices {
    private final String name;
    private final int id;
    private final int u;
    private final int i;
    private final String category;
    private boolean isOn;
    protected final SmartHome smartHome;

    public Devices(String name, int id, int u, int i, String category, SmartHome smartHome) {
        this.name = name;
        this.id = id;
        this.u = u;
        this.i = i;
        this.category = category;
        this.isOn = false;
        this.smartHome = smartHome;
    }

    public void turnOn() {
        this.isOn = true;
        System.out.println(name + " (#" + id + ") bekapcsolva.");
    }

    public void turnOff() {
        this.isOn = false;
        System.out.println(name + " (#" + id + ") kikapcsolva.");
    }

    public void restoreOnState() {
        this.isOn = true;
    }

    public abstract void work();

    public boolean isOn() { return isOn; }
    public String getName() { return name; }
    public int getId() { return id; }
    public int getVoltage() { return u; }
    public int getCurrent() { return i; }
    public String getCategory() { return category; }

    public double getPowerConsumption() {
        return isOn ? (u * i) : 0.0;
    }
}