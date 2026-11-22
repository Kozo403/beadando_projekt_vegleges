public class SmartThermostat extends Devices implements EcoModeable {
    private final double targetTemperature;
    private boolean ecoMode;

    public SmartThermostat(String name, int id, int u, int i, String category, SmartHome smartHome, double targetTemperature) {
        super(name, id, u, i, category, smartHome);
        this.targetTemperature = targetTemperature;
        this.ecoMode = false;
    }

    public double getTargetTemperature() {
        return targetTemperature;
    }

    @Override
    public void work() {
        if (isOn()) {
            double change = ecoMode ? 0.2 : 0.5;
            smartHome.changeTemperature(change);
            System.out.println(getName() + " fűt. Cél: " + targetTemperature + " °C" + (ecoMode ? " [ECO MÓD]" : ""));
        }
    }

    @Override
    public void enableEcoMode() {
        ecoMode = true;
        System.out.println(getName() + " Eco mód BEKAPCSOLVA.");
    }

    @Override
    public void disableEcoMode() {
        ecoMode = false;
        System.out.println(getName() + " Eco mód KIKAPCSOLVA.");
    }

    @Override
    public boolean isEcoModeActive() {
        return ecoMode;
    }

    @Override
    public double getPowerConsumption() {
        if (!isOn()) {
            return 0.0;
        }
        double basePower = super.getPowerConsumption();
        if (basePower == 0) {
            basePower = 1500.0;
        }
        return ecoMode ? basePower * 0.75 : basePower;
    }
}