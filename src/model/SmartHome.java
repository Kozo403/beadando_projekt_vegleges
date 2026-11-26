package model;

public class SmartHome {
    private double temperature;
    private double humidity;
    private int airQuality;
    private final Devices[] devices;
    private int deviceCount;

    public SmartHome(double temperature, double humidity, int airQuality, int capacity) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.airQuality = airQuality;
        this.devices = new Devices[capacity];
        this.deviceCount = 0;
    }

    public void addDevice(Devices device) {
        if (deviceCount < devices.length) {
            devices[deviceCount++] = device;
        } else {
            System.out.println("A ház eszközökre fenntartott helye betelt!");
        }
    }

    public void changeTemperature(double amount) {
        this.temperature += amount;
    }

    public void changeHumidity(double amount) {
        this.humidity += amount;
    }

    public void changeAirQuality(int amount) {
        this.airQuality = Math.min(100, this.airQuality + amount);
    }

    public void operateAll() {
        for (int idx = 0; idx < deviceCount; idx++) {
            if (devices[idx] != null) {
                devices[idx].work();
            }
        }
    }

    public Devices findDeviceById(int id) {
        for (int idx = 0; idx < deviceCount; idx++) {
            if (devices[idx] != null && devices[idx].getId() == id) {
                return devices[idx];
            }
        }
        return null;
    }

    public int getMaxDeviceId() {
        int maxId = 0;
        for (int idx = 0; idx < deviceCount; idx++) {
            if (devices[idx] != null && devices[idx].getId() > maxId) {
                maxId = devices[idx].getId();
            }
        }
        return maxId;
    }

    public double getTotalPowerConsumption() {
        double total = 0.0;
        for (int idx = 0; idx < deviceCount; idx++) {
            if (devices[idx] != null) {
                total += devices[idx].getPowerConsumption();
            }
        }
        return total;
    }

    public double getTemperature() { return temperature; }
    public double getHumidity() { return humidity; }
    public int getAirQuality() { return airQuality; }
    public Devices[] getDevices() { return devices; }
    public int getDeviceCount() { return deviceCount; }
}