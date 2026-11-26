package action;

import model.Devices;
import model.EcoModeable;
import model.SmartHome;
import ui.ConsoleInputReader;

public class DeviceEcoToggleAction {

    public static void execute(SmartHome home, ConsoleInputReader input) {
        int id = input.readInt("Add meg az eszköz ID-jét az Eco mód váltáshoz: ");
        Devices device = home.findDeviceById(id);
        if (device == null) {
            System.out.println("Hiba: Nincs ilyen ID-jű eszköz!");
            return;
        }
        if (!(device instanceof EcoModeable)) {
            System.out.println("Hiba: Ez az eszköz nem támogatja az Eco módot!");
            return;
        }
        EcoModeable ecoDevice = (EcoModeable) device;
        if (ecoDevice.isEcoModeActive()) {
            ecoDevice.disableEcoMode();
        } else {
            ecoDevice.enableEcoMode();
        }
    }
}