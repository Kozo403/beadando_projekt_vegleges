package action;

import model.Devices;
import model.SmartHome;
import ui.ConsoleInputReader;

public class DeviceWorkAction {

    public static void execute(SmartHome home, ConsoleInputReader input) {
        int id = input.readInt("Add meg a működtetendő eszköz ID-jét: ");
        Devices device = home.findDeviceById(id);
        if (device == null) {
            System.out.println("Hiba: Nincs ilyen ID-jű eszköz!");
            return;
        }
        device.work();
    }
}