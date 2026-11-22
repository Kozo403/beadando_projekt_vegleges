public class SmartHomeJsonImporter {

    public static SmartHome fromJson(String json, int capacity) {
        double temperature = JsonSerializerUtil.extractDoubleValue(json, "temperature", 21.0);
        double humidity = JsonSerializerUtil.extractDoubleValue(json, "humidity", 45.0);
        int airQuality = JsonSerializerUtil.extractIntValue(json, "airQuality", 70);

        SmartHome home = new SmartHome(temperature, humidity, airQuality, capacity);

        int arrayStart = json.indexOf("[");
        int arrayEnd = json.lastIndexOf("]");
        if (arrayStart == -1 || arrayEnd == -1 || arrayEnd <= arrayStart) {
            return home;
        }

        String devicesSection = json.substring(arrayStart + 1, arrayEnd);
        int pos = 0;
        while (pos < devicesSection.length()) {
            int objStart = devicesSection.indexOf("{", pos);
            if (objStart == -1) {
                break;
            }
            int objEnd = findMatchingBrace(devicesSection, objStart);
            if (objEnd == -1) {
                break;
            }
            String deviceJson = devicesSection.substring(objStart, objEnd + 1);
            Devices restored = parseDevice(deviceJson, home);
            if (restored != null) {
                home.addDevice(restored);
            }
            pos = objEnd + 1;
        }

        return home;
    }

    private static Devices parseDevice(String objJson, SmartHome home) {
        int id = JsonSerializerUtil.extractIntValue(objJson, "id", 1);
        String type = JsonSerializerUtil.extractStringValue(objJson, "type");
        String name = JsonSerializerUtil.extractStringValue(objJson, "name");
        String category = JsonSerializerUtil.extractStringValue(objJson, "category");
        int voltage = JsonSerializerUtil.extractIntValue(objJson, "voltage", 230);
        int current = JsonSerializerUtil.extractIntValue(objJson, "current", 1);
        boolean isOn = JsonSerializerUtil.extractBooleanValue(objJson, "isOn", false);
        boolean ecoMode = JsonSerializerUtil.extractBooleanValue(objJson, "ecoMode", false);
        double targetTemp = JsonSerializerUtil.extractDoubleValue(objJson, "targetTemperature", 22.0);

        Devices device;
        if ("thermostat".equalsIgnoreCase(type)) {
            device = new SmartThermostat(name, id, voltage, current, category, home, targetTemp);
        } else if ("purifier".equalsIgnoreCase(type)) {
            device = new SmartAirpurifier(name, id, voltage, current, category, home);
        } else if ("humidifier".equalsIgnoreCase(type)) {
            device = new SmartAirhumidifier(name, id, voltage, current, category, home);
        } else {
            device = new SmartLamp(name, id, voltage, current, category, home);
        }

        if (device instanceof EcoModeable && ecoMode) {
            ((EcoModeable) device).enableEcoMode();
        }
        if (isOn) {
            device.restoreOnState();
        }
        return device;
    }

    private static int findMatchingBrace(String text, int openIndex) {
        int depth = 0;
        boolean inQuotes = false;
        boolean escaped = false;

        for (int i = openIndex; i < text.length(); i++) {
            char c = text.charAt(i);
            if (inQuotes) {
                if (escaped) {
                    escaped = false;
                } else if (c == '\\') {
                    escaped = true;
                } else if (c == '"') {
                    inQuotes = false;
                }
            } else {
                if (c == '"') {
                    inQuotes = true;
                } else if (c == '{') {
                    depth++;
                } else if (c == '}') {
                    depth--;
                    if (depth == 0) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
}