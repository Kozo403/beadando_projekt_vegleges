package ui;

import model.Devices;
import model.EcoModeable;
import model.SmartHome;

public class DeviceLister {

    public static void listDevices(SmartHome home) {
        System.out.println("\n--- Jelenlegi eszközök a házban ---");
        Devices[] devices = home.getDevices();
        for (int i = 0; i < home.getDeviceCount(); i++) {
            Devices d = devices[i];
            if (d != null) {
                String ecoInfo = "";
                if (d instanceof EcoModeable) {
                    ecoInfo = " | Eco: " + (((EcoModeable) d).isEcoModeActive() ? "BE" : "KI");
                }
                System.out.printf("- ID [%d] %s (%s) | Állapot: %s%s | Fogyasztás: %.1f W%n",
                        d.getId(), d.getName(), d.getCategory(), (d.isOn() ? "BE" : "KI"), ecoInfo, d.getPowerConsumption());
            }
        }
    }
}