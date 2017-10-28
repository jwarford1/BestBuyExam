package com.bestbuyexam.tests;

import com.bestbuyexam.libs.*;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
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
        //driver.manage().window().maximize();
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
        String prodToCart = mcp.selectFirstItemInCatgeory();

        // Validate that the product we searched ends up
        // in the 'scope' loaded in with the cart page
        BestBuyProductToCart ptc = new BestBuyProductToCart(driver);
        Assert.assertEquals(true, ptc.validateProducToCart(prodToCart));

        // validate the product is in stock

        // add item to cart
        ptc.addItemToCart();
        ptc.dismissWarranty();
        System.out.println(ptc.validateCart());
        Thread.sleep(2000);
        ptc.submitForCheckout();

        // begin checkout
        BestBuyCheckout checkout = new BestBuyCheckout(driver);
        checkout.selectGuestCheckout();
        checkout.continueCheckout();

        // fill in delivery info
        BestBuyDeliveryDetails delivery = new BestBuyDeliveryDetails(driver);
        delivery.setFirstName(prop.getProperty("first_name"));
        delivery.setLastName(prop.getProperty("last_name"));
        delivery.setAddress(prop.getProperty("address"));
        delivery.setCity(prop.getProperty("city"));
        delivery.setProvince(prop.getProperty("province"));
        delivery.setPostalCode(prop.getProperty("postal_code"));
        delivery.setCountry(prop.getProperty("country"));
        delivery.setPhone(prop.getProperty("phone_area_code"), prop.getProperty("phone_prefix"),prop.getProperty("phone_suffix"));Thread.sleep(5000);


        JavascriptExecutor jsDown = (JavascriptExecutor) driver;
        jsDown.executeScript("javascript:window.scrollBy(515,1045)");

        delivery.continueFromAddress();

        // fill in visa card info with invalid cc number
        // caputure exception
        BestBuyPayment payment = new BestBuyPayment(driver);
        Thread.sleep(5000);


        payment.selectCreditCard();
        payment.setCardType("Visa");
        payment.setCardExpirationMonth("01");
        payment.setCardExpirationYear("2019");
        Thread.sleep(5000);
        payment.setCardNumber("xx");
        Thread.sleep(5000);

        // Possible bug:  the cvv box appears in the browser, but its syntax has no height
        // payment.setCardCVV("047");

        payment.selectSameShipping();
        Thread.sleep(5000);
        payment.processCard();
        Thread.sleep(5000);
        //PageUtils.closePopup(driver);
        String x = payment.getErrorSummaries();
        System.out.println(x);
    }

    @AfterClass
    public static void shutDown() {

        if (driver != null) {
            //driver.quit();
        }

        try {
            input.close();

        }
        catch(IOException ex) {

            System.out.println(ex.getCause());
        }
    }
}
