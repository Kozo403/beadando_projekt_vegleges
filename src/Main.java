public class Main {
    private static final int CAPACITY = 20;
    private static final String DEFAULT_JSON_FILE = "smarthome_state.json";

    public static void main(String[] args) {
        ConsoleInputReader input = new ConsoleInputReader();
        SmartHome home = HomeInitializer.createDefaultHome(CAPACITY);
        int nextId = home.getMaxDeviceId() + 1;
        boolean running = true;

        while (running) {
            MenuPrinter.printMenu(home);
            int choice = input.readInt("Válassz egy opciót: ");

            switch (choice) {
                case 1 -> DeviceLister.listDevices(home);
                case 2 -> DevicePowerSwitchAction.execute(home, input, true);
                case 3 -> DevicePowerSwitchAction.execute(home, input, false);
                case 4 -> DeviceEcoToggleAction.execute(home, input);
                case 5 -> DeviceWorkAction.execute(home, input);
                case 6 -> nextId = DeviceCreationAction.execute(home, input, nextId);
                case 7 -> home.operateAll();
                case 8 -> JsonSaveAction.execute(home, input, DEFAULT_JSON_FILE);
                case 9 -> {
                    home = JsonLoadAction.execute(home, input, DEFAULT_JSON_FILE, CAPACITY);
                    nextId = home.getMaxDeviceId() + 1;
                }
                case 0 -> running = false;
                default -> System.out.println("Ismeretlen parancs!");
            }
        }

        input.close();
        System.out.println("Kilépés az okosotthon rendszerből...");
    }
}