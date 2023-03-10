package utils;

import org.apache.commons.configuration.PropertiesConfiguration;

public class ConfigUtils {
    private static final String PROPERTIES_FILE = "./src/test/resources/config.properties";
    public static void setProperty(String key, String value){
        try {
            PropertiesConfiguration config = new PropertiesConfiguration(PROPERTIES_FILE);
            config.setProperty(key, value);
            config.save();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static String getProperty(String key){
        Object val= null;
        try {
            PropertiesConfiguration config = new PropertiesConfiguration(PROPERTIES_FILE);
            val = config.getProperty(key);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return String.valueOf(val);
    }
}
