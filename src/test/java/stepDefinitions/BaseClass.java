package stepDefinitions;

import Utilities.PropertyReader;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pageObjects.DisputePage;

import java.util.Properties;

public class BaseClass {
    public WebDriver driver;
    public DisputePage disputePage;
    public static Logger logger;
    Properties configProp;
    public static PropertyReader testData = PropertyReader.getTestDataInstance();

}
