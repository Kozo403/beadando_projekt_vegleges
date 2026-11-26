package persistence;

import model.SmartHome;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SmartHomeFileStorage {

    public static void saveToFile(SmartHome home, String filePath) {
        String jsonContent = SmartHomeJsonExporter.toJson(home);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(jsonContent);
            System.out.println(" Állapot elmentve JSON fájlba: " + filePath);
        } catch (IOException e) {
            System.out.println("Hiba a JSON fájl mentésekor: " + e.getMessage());
        }
    }

    public static SmartHome loadFromFile(String filePath, int capacity) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            System.out.println(" Állapot betöltve JSON fájlból: " + filePath);
            return SmartHomeJsonImporter.fromJson(sb.toString(), capacity);
        } catch (IOException e) {
            System.out.println("Hiba a JSON fájl betöltésekor: " + e.getMessage());
            return null;
        }
    }
}