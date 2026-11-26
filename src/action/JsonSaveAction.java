package action;

import model.SmartHome;
import persistence.SmartHomeFileStorage;
import ui.ConsoleInputReader;

public class JsonSaveAction {

    public static void execute(SmartHome home, ConsoleInputReader input, String defaultFile) {
        String path = input.readLine("Fájlnév (Enter = " + defaultFile + "): ").trim();
        String targetFile = path.isEmpty() ? defaultFile : path;
        SmartHomeFileStorage.saveToFile(home, targetFile);
    }
}