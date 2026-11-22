public class HomeInitializer {

    public static SmartHome createDefaultHome(int capacity) {
        SmartHome home = new SmartHome(21.0, 45.0, 70, capacity);
        home.addDevice(new SmartThermostat("Nappali Termosztát", 1, 230, 2, "Nappali", home, 22.5));
        home.addDevice(new SmartAirpurifier("Háló Légtisztító", 2, 230, 1, "Hálószoba", home));
        home.addDevice(new SmartAirhumidifier("Párásító", 3, 230, 1, "Gyerekszoba", home));
        home.addDevice(new SmartLamp("Mennyezeti Lámpa", 4, 230, 1, "Nappali", home));
        return home;
    }
}