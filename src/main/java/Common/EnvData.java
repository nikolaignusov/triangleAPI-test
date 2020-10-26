package Common;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class EnvData {

    public static final String PATH = getPath();
    public static final String SCHEMA_PATH = "src/main/resources/jsonSchemas";
    public static final String ENV = getEnvironment();
    public static Properties properties;

    public EnvData() {
        properties = null;
    }

    protected static String getPath() {
        String mpath = System.getProperty("mpath");
        return isNotNullNotEmpty(mpath, true) ? mpath : "/src/main/resources/";
    }

    protected static String getEnvironment() {
        String env = "production";
        String mavenEnv = System.getProperty("menv");
        if (isNotNullNotEmpty(mavenEnv, true)) {
            env = mavenEnv;
        }

        System.out.println("Running test in environment: " + env + "\n");
        return env;
    }


    protected static String getProperty(String key) {
        return properties.getProperty(key);
    }

    protected static Properties getPropertiesForEnv(String env) {
        Properties properties = null;

        try {
            properties = new Properties();
            FileReader reader = new FileReader(System.getProperty("user.dir") + String.format(PATH + "%s.properties", env));
            properties.load(reader);
            File custom = new File(System.getProperty("user.dir") + PATH + "custom.properties");
            String val;
            Enumeration enums = properties.propertyNames();
        } catch (IOException ioe) {
            ;
        }

        return properties;
    }

    private static boolean isNotNullNotEmpty(String text, boolean trim) {
        if (trim) {
            if (text != null && text.trim().length() > 0) {
                return true;
            }
        } else if (text != null && text.length() > 0) {
            return true;
        }

        return false;
    }

    static {
        properties = getPropertiesForEnv(ENV);
    }
}
