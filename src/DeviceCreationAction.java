public class DeviceCreationAction {

    public static int execute(SmartHome home, ConsoleInputReader input, int nextId) {
        String name = input.readLine("Add meg az új eszköz nevét: ");
        String category = input.readLine("Add meg a szobát / csoportot: ");
        int type = input.readInt("Válassz típust (1: Lámpa, 2: Termosztát, 3: Légtisztító, 4: Párásító): ");

        Devices created;
        if (type == 2) {
            created = new SmartThermostat(name, nextId, 230, 2, category, home, 22.0);
        } else if (type == 3) {
            created = new SmartAirpurifier(name, nextId, 230, 1, category, home);
        } else if (type == 4) {
            created = new SmartAirhumidifier(name, nextId, 230, 1, category, home);
        } else {
            created = new SmartLamp(name, nextId, 230, 1, category, home);
        }

        home.addDevice(created);
        System.out.println("✅ Új eszköz létrehozva! ID: " + nextId);
        return nextId + 1;
    }
}