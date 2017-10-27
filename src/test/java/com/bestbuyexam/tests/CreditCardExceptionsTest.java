package com.bestbuyexam.tests;

import com.bestbuyexam.libs.BestBuyMonitorsCategoryPage;
import com.bestbuyexam.libs.BestBuyShopByCategory;
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
            input = new FileInputStream("src/main/resources/config.properties");
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
    public void testInvalidCreditCardNumber() throws InterruptedException {

        driver.navigate().to(prop.getProperty("landing_page"));

        // verify that we are on the main Best Buy landing page
        Assert.assertEquals(prop.getProperty("landing_page_title"), driver.getTitle());

        // instantiate a search page and search for a monitor
        // by passing driver to the constructor for a Page (in this case BestBuyShopByCategory)
        // handle any modals that popup
        BestBuyShopByCategory landingPage = new BestBuyShopByCategory(driver);
        landingPage.closeModalPopup();
        landingPage.clearSearchBox();
        landingPage.sendSearchKeyWords(prop.getProperty("product_keyword"));
        landingPage.submitSearchTerm();

        // select the first available item in the Monitor category
        BestBuyMonitorsCategoryPage  mcp = new BestBuyMonitorsCategoryPage(driver);
        Thread.sleep(2000);
        mcp.selectFirstItemInCatgeory();

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
