package com.bestbuyexam;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
/**
 * Created by johnwarford on 2017-10-27.
 */

public class CreditCardExceptionsTest {
    public static RemoteWebDriver driver;
    public static String appURL = "https://www.bestbuy.ca";

    @BeforeClass
    public static  void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        //driver.manage().window().maximize();
    }

    @Test
    public void testGooglePageTitleInIEBrowser() {
        System.out.println("*** Navigation to Application ***");
        driver.navigate().to(appURL);
        String strPageTitle = driver.getTitle();
        System.out.println("*** Verifying page title ***");
        //Assert.assertTrue(strPageTitle.equalsIgnoreCase("Google"), "Page title doesn't match");
    }

    @AfterClass
    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
