package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjects.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Steps extends BaseClass {



    @Before
    public void setup() throws IOException {
        logger = logger.getLogger("Sample Project");
        PropertyConfigurator.configure("log4j.properties");
        configProp = new Properties();
        FileInputStream configPropfile = new FileInputStream("config.properties");
        configProp.load(configPropfile);
        logger.info("*********Browser Initialization************");
        String browser = configProp.getProperty("browser");
        if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));
            driver = new FirefoxDriver();
        } else
            if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
            driver = new ChromeDriver();
        }
    }

    String OrderNumber = "";

    @After
    public void testTearDown() {
        driver.quit();
    }


    @Given("User navigates to a page with a list of disputes")
    public void userNavigatesToAPageWithAListOfDisputes() throws IOException {
        logger.info("********* Launching browser************");
        driver.manage().window().maximize();
        driver.get(testData.readProperty("baseURL"));
        disputePage = new DisputePage(driver);
        disputePage.addToArray();
        disputePage.extractData();

    }

}