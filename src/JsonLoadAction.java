public class JsonLoadAction {

    public static SmartHome execute(SmartHome currentHome, ConsoleInputReader input, String defaultFile, int capacity) {
        String path = input.readLine("Fájlnév (Enter = " + defaultFile + "): ").trim();
        String targetFile = path.isEmpty() ? defaultFile : path;
        SmartHome loaded = SmartHomeFileStorage.loadFromFile(targetFile, capacity);
        return (loaded != null) ? loaded : currentHome;
    }
}