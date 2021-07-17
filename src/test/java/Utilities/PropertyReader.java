package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    public Properties prop = new Properties();
    InputStream inputStream;

    private static String User_directory = System.getProperty("user.dir") + File.separator;
    private static String testDatPath = User_directory + "environment.properties";
    private static String browser = User_directory + "config.properties";


    public PropertyReader(String path) {
        loadProperties(path);
    }

    static PropertyReader testDataInstance = new PropertyReader(testDatPath);
    static PropertyReader browserInstance = new PropertyReader(browser);

    public static PropertyReader getTestDataInstance() {
        return testDataInstance;
    }

    public static PropertyReader getBrowserInstance() {
        return browserInstance;
    }

    public void loadProperties(String propFilePath) {
        try {
            if (propFilePath != null) {
                inputStream = new FileInputStream(propFilePath);
                prop.load(inputStream);
            } else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readProperty(String key) {
        return prop.getProperty(key);
    }

}
