package persistence;

public class JsonSerializerUtil {

    public static String escapeJson(String text) {
        if (text == null) {
            return "";
        }
        return text
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }

    public static String unescapeJson(String text) {
        if (text == null) {
            return "";
        }
        return text
                .replace("\\\"", "\"")
                .replace("\\\\", "\\")
                .replace("\\n", "\n")
                .replace("\\r", "\r");
    }

    public static String extractStringValue(String json, String key) {
        String search = "\"" + key + "\"";
        int keyIndex = json.indexOf(search);
        if (keyIndex == -1) {
            return "";
        }
        int colonIndex = json.indexOf(":", keyIndex + search.length());
        int firstQuote = json.indexOf("\"", colonIndex + 1);
        int secondQuote = findClosingQuote(json, firstQuote + 1);
        if (firstQuote == -1 || secondQuote == -1) {
            return "";
        }
        return unescapeJson(json.substring(firstQuote + 1, secondQuote));
    }

    public static double extractDoubleValue(String json, String key, double fallback) {
        String raw = extractRawScalar(json, key);
        if (raw.isEmpty()) {
            return fallback;
        }
        return Double.parseDouble(raw);
    }

    public static int extractIntValue(String json, String key, int fallback) {
        String raw = extractRawScalar(json, key);
        if (raw.isEmpty()) {
            return fallback;
        }
        return Integer.parseInt(raw);
    }

    public static boolean extractBooleanValue(String json, String key, boolean fallback) {
        String raw = extractRawScalar(json, key);
        if (raw.isEmpty()) {
            return fallback;
        }
        return Boolean.parseBoolean(raw);
    }

    private static String extractRawScalar(String json, String key) {
        String search = "\"" + key + "\"";
        int keyIndex = json.indexOf(search);
        if (keyIndex == -1) {
            return "";
        }
        int colonIndex = json.indexOf(":", keyIndex + search.length());
        if (colonIndex == -1) {
            return "";
        }
        int start = colonIndex + 1;
        while (start < json.length() && Character.isWhitespace(json.charAt(start))) {
            start++;
        }
        int end = start;
        while (end < json.length()) {
            char c = json.charAt(end);
            if (c == ',' || c == '}' || c == '\n' || c == '\r') {
                break;
            }
            end++;
        }
        return json.substring(start, end).trim();
    }

    private static int findClosingQuote(String text, int fromIndex) {
        boolean escaped = false;
        for (int i = fromIndex; i < text.length(); i++) {
            char c = text.charAt(i);
            if (escaped) {
                escaped = false;
            } else if (c == '\\') {
                escaped = true;
            } else if (c == '"') {
                return i;
            }
        }
        return -1;
    }
}