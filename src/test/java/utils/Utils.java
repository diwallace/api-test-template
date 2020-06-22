package utils;

import java.util.HashMap;

public class Utils {
    private static HashMap<String, String> dev = new HashMap<>();
    private static HashMap<String, String> qa = new HashMap<>();

    static {
        dev.put("uri", "https://api.github.com");
        qa.put("uri", "https://api.github.com");
    }

    public static String getUri() {
        return getEnvironment().get("uri");
    }

    private static HashMap<String, String> getEnvironment() {
        String profile;

        profile = System.getProperty("profile") == null ? "dev" : System.getProperty("profile");

        switch (profile) {
            case "qa":
                return qa;
            default:
                return dev;
        }
    }
}
