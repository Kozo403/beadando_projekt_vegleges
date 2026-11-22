public class MenuPrinter {

    public static void printMenu(SmartHome home) {
        System.out.println("\n==========================================================");
        System.out.println("              🏠 OKOSOTTHON VEZÉRLŐ RENDSZER              ");
        System.out.println("==========================================================");
        System.out.printf(" Hőm: %.1f °C | Pára: %.1f%% | Lev.min: %d | Fogyasztás: %.1f W%n",
                home.getTemperature(), home.getHumidity(), home.getAirQuality(), home.getTotalPowerConsumption());
        System.out.println("----------------------------------------------------------");
        System.out.println("1. Eszközök listázása");
        System.out.println("2. Eszköz bekapcsolása (ID alapján)");
        System.out.println("3. Eszköz kikapcsolása (ID alapján)");
        System.out.println("4. Eco mód váltása (ID alapján)");
        System.out.println("5. Eszköz működtetése [work()] (ID alapján)");
        System.out.println("6. Új eszköz hozzáadása");
        System.out.println("7. Minden bekapcsolt eszköz működtetése egyszerre");
        System.out.println("8. Állapot mentése JSON fájlba");
        System.out.println("9. Állapot betöltése JSON fájlból");
        System.out.println("0. Kilépés");
    }
}