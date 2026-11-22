public class SmartLamp extends Devices {

    public SmartLamp(String name, int id, int u, int i, String category, SmartHome smartHome) {
        super(name, id, u, i, category, smartHome);
    }

    @Override
    public void work() {
        if (isOn()) {
            System.out.println(getName() + " világít.");
        }
    }

    @Override
    public double getPowerConsumption() {
        return isOn() ? 10.0 : 0.0;
    }
}