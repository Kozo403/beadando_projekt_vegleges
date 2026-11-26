package persistence;

import model.Devices;
import model.EcoModeable;
import model.SmartAirhumidifier;
import model.SmartAirpurifier;
import model.SmartHome;
import model.SmartThermostat;

public class SmartHomeJsonExporter {

    public static String toJson(SmartHome home) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"temperature\": ").append(home.getTemperature()).append(",\n");
        sb.append("  \"humidity\": ").append(home.getHumidity()).append(",\n");
        sb.append("  \"airQuality\": ").append(home.getAirQuality()).append(",\n");
        sb.append("  \"devices\": [\n");

        Devices[] devices = home.getDevices();
        int count = home.getDeviceCount();
        int written = 0;

        for (int i = 0; i < count; i++) {
            Devices d = devices[i];
            if (d == null) {
                continue;
            }
            if (written > 0) {
                sb.append(",\n");
            }
            sb.append(deviceToJson(d));
            written++;
        }

        sb.append("\n  ]\n");
        sb.append("}\n");
        return sb.toString();
    }

    private static String deviceToJson(Devices d) {
        String type = resolveType(d);
        boolean ecoActive = (d instanceof EcoModeable) && ((EcoModeable) d).isEcoModeActive();
        double targetTemp = (d instanceof SmartThermostat) ? ((SmartThermostat) d).getTargetTemperature() : 22.0;

        StringBuilder sb = new StringBuilder();
        sb.append("    {\n");
        sb.append("      \"id\": ").append(d.getId()).append(",\n");
        sb.append("      \"type\": \"").append(JsonSerializerUtil.escapeJson(type)).append("\",\n");
        sb.append("      \"name\": \"").append(JsonSerializerUtil.escapeJson(d.getName())).append("\",\n");
        sb.append("      \"category\": \"").append(JsonSerializerUtil.escapeJson(d.getCategory())).append("\",\n");
        sb.append("      \"voltage\": ").append(d.getVoltage()).append(",\n");
        sb.append("      \"current\": ").append(d.getCurrent()).append(",\n");
        sb.append("      \"isOn\": ").append(d.isOn()).append(",\n");
        sb.append("      \"ecoMode\": ").append(ecoActive).append(",\n");
        sb.append("      \"targetTemperature\": ").append(targetTemp).append("\n");
        sb.append("    }");
        return sb.toString();
    }

    private static String resolveType(Devices d) {
        if (d instanceof SmartThermostat) {
            return "thermostat";
        }
        if (d instanceof SmartAirpurifier) {
            return "purifier";
        }
        if (d instanceof SmartAirhumidifier) {
            return "humidifier";
        }
        return "lamp";
    }
}