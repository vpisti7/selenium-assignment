import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public final class LoadConfig {
    private static final String CONFIG_FILE = "config.properties";
    private static final String DEFAULT_BROWSERS = "chrome";
    private static final Properties CONFIG = loadProperties();

    private LoadConfig() {
    }

    public static List<String> getBrowsers() {
        String[] browserNames = get("browsers", DEFAULT_BROWSERS).split(",");
        List<String> browsers = new ArrayList<String>();

        for (String browserName : browserNames) {
            String trimmedBrowserName = browserName.trim();
            if (!trimmedBrowserName.isEmpty()) {
                browsers.add(trimmedBrowserName);
            }
        }

        return browsers;
    }

    public static String get(String key) {
        return get(key, "");
    }

    public static String get(String key, String defaultValue) {
        String value = CONFIG.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }

        return stripQuotes(value.trim());
    }

    public static String getEmail() {
        return get("email");
    }

    public static String getPassword() {
        return get("password");
    }

    private static Properties loadProperties() {
        Properties loadedProperties = new Properties();

        try {
            FileInputStream inputStream = new FileInputStream(findConfigFile());
            try {
                loadedProperties.load(inputStream);
            } finally {
                inputStream.close();
            }
        } catch (IOException exception) {
            throw new RuntimeException("Could not read " + CONFIG_FILE, exception);
        }

        return loadedProperties;
    }

    private static File findConfigFile() {
        File[] candidates = new File[]{
                new File(CONFIG_FILE),
                new File("tests/selenium-assignment/" + CONFIG_FILE),
                new File("/home/selenium/tests/selenium-assignment/" + CONFIG_FILE)
        };

        for (File candidate : candidates) {
            if (candidate.exists()) {
                return candidate;
            }
        }

        return new File(CONFIG_FILE);
    }

    private static String stripQuotes(String value) {
        if (value.length() >= 2 && value.startsWith("\"") && value.endsWith("\"")) {
            return value.substring(1, value.length() - 1);
        }

        if (value.length() >= 2 && value.startsWith("'") && value.endsWith("'")) {
            return value.substring(1, value.length() - 1);
        }

        return value;
    }
}
