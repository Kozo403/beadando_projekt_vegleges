package action;

import model.Devices;
import model.SmartHome;
import ui.ConsoleInputReader;

public class DevicePowerSwitchAction {

    public static void execute(SmartHome home, ConsoleInputReader input, boolean turnOn) {
        int id = input.readInt("Add meg az eszköz ID-jét: ");
        Devices device = home.findDeviceById(id);
        if (device == null) {
            System.out.println("Hiba: Nincs ilyen ID-jű eszköz!");
            return;
        }
        if (turnOn) {
            device.turnOn();
        } else {
            device.turnOff();
        }
    }
}