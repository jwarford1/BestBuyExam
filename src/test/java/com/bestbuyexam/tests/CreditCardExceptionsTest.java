package com.bestbuyexam.tests;

import com.bestbuyexam.libs.PageUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * Created by johnwarford on 2017-10-27.
 */

public class CreditCardExceptionsTest {
    public static RemoteWebDriver driver;
    //public static String appURL = "https://www.bestbuy.ca";
    static Properties prop = null;
    static InputStream input = null;

    @BeforeClass
    public static  void setUp() throws MalformedURLException {

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        prop = new Properties();

        try
        {
            input = new FileInputStream("/Users/johnwarford/BestBuyExam/src/main/resources/config.properties");
        }
        catch(FileNotFoundException f)
        {
            System.out.println(f.getCause());
        }

        try
        {
            prop.load(input);
        }
        catch(IOException ex) {

            System.out.println(ex.getCause());
        }
    }

    @Test
    public void testInvalidCreditCardNumber() {

        driver.navigate().to(prop.getProperty("landing_page"));
        PageUtils.closePopup(driver);

        // verify that we are on the main Best Buy landing page
        Assert.assertEquals("Computers, TVs, Video Games & Appliances - Best Buy Canada", driver.getTitle());

    }

    @AfterClass
    public static void shutDown() {
        if (driver != null) {
            driver.quit();
        }

        try {
            input.close();

        }
        catch(IOException ex) {

            System.out.println(ex.getCause());
        }
    }
}
