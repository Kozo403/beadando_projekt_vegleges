package model;

public class SmartAirpurifier extends Devices implements EcoModeable {
    private boolean ecoMode;

    public SmartAirpurifier(String name, int id, int u, int i, String category, SmartHome smartHome) {
        super(name, id, u, i, category, smartHome);
        this.ecoMode = false;
    }

    @Override
    public void work() {
        if (isOn()) {
            smartHome.changeAirQuality(15);
            System.out.println(getName() + " tisztítja a levegőt" + (ecoMode ? " [ECO MÓD]" : "") + ".");
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
            basePower = 50.0;
        }
        return ecoMode ? basePower * 0.75 : basePower;
    }
}