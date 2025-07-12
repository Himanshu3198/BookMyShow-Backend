package bms.util;
import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.Map;

public class TestDataLoader {
    private static Map<String, Object> data;

    static {
        Yaml yaml = new Yaml();
        InputStream input = TestDataLoader.class.getClassLoader().getResourceAsStream("config/config.yaml");
        data = yaml.load(input);
    }

    public static Object get(String keyPath) {
        String[] keys = keyPath.split("\\.");
        Map<String, Object> current = data;
        Object value = null;

        for (int i = 0; i < keys.length; i++) {
            value = current.get(keys[i]);
            if (i < keys.length - 1) {
                current = (Map<String, Object>) value;
            }
        }
        return value;
    }

    public static String getAsString(String keyPath) {
        return String.valueOf(get(keyPath));
    }

    public static Long getAsLong(String keyPath) {
        return Long.valueOf(getAsString(keyPath));
    }

    public static Double getAsDouble(String keyPath) {
        return Double.valueOf(getAsString(keyPath));
    }
}
