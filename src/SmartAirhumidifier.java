public class SmartAirhumidifier extends Devices {

    public SmartAirhumidifier(String name, int id, int u, int i, String category, SmartHome smartHome) {
        super(name, id, u, i, category, smartHome);
    }

    @Override
    public void work() {
        if (isOn()) {
            smartHome.changeHumidity(5.0);
            System.out.println(getName() + " növeli a páratartalmat.");
        }
    }
}