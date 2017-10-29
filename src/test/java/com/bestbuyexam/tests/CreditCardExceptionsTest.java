package com.bestbuyexam.tests;

import com.bestbuyexam.libs.*;
import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by johnwarford on 2017-10-27.
 */

public class CreditCardExceptionsTest {

    /// simple test watcher for basic reporting
    @Rule
    public TestWatcher testWatcher = new TestWatcher() {

        @Override
        protected void starting(Description description) {
            Logger.getAnonymousLogger().info(
                    "start: " + description.getMethodName());
        }

        @Override
        protected void succeeded(Description description) {
            Logger.getAnonymousLogger().info("succeeded: " + description.getMethodName());
            System.out.println("Test "+description.getMethodName()+" PASSED!");
            System.out.println("Captured exceptio:" + actual_exception_string);
        }

        @Override
        protected void failed(Throwable e, Description description) {
            Logger.getAnonymousLogger().log(Level.WARNING, "failed: " + description.getMethodName(), e);
            System.out.println("Test "+description.getMethodName()+" FAILED!");
        }

        @Override
        protected void finished(Description description) {
            Logger.getAnonymousLogger().info(
                    "finished: " + description.getMethodName());
        }

    };

    public static RemoteWebDriver driver;

    static Properties prop = null;
    static InputStream input = null;
    private String actual_exception_string = "";

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
        mcp.selectLedMonitorType();

        // Validate that the product we searched ends up
        // in 'scope' ie: loaded in with the cart page
        String prodToCart = mcp.selectFirstItemInCatgeory();
        BestBuyProductToCart ptc = new BestBuyProductToCart(driver);
        Assert.assertEquals(true, ptc.validateProducToCart(prodToCart));

        // **** validate the product is in stock
        Assert.assertTrue((ptc.getSelectedProductAvailability().contains(prop.getProperty("product_availability"))));

        // add item to cart
        ptc.addItemToCart();
        ptc.dismissWarranty();
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
        delivery.setPhone(prop.getProperty("phone_area_code"), prop.getProperty("phone_prefix"),prop.getProperty("phone_suffix"));
        PageUtils.scrollerHelper(515, 1045, driver);
        delivery.continueFromAddress();

        // fill in visa card info with invalid cc number
        // caputure exception
        BestBuyPayment payment = new BestBuyPayment(driver);
        PageUtils.scrollerHelper(630, -415, driver);
        payment.selectCreditCard();
        payment.setCardType(prop.getProperty("cc_type"));
        payment.setCardExpirationMonth(prop.getProperty("cc_expMonth"));
        payment.setCardExpirationYear(prop.getProperty("cc_expYear"));
        payment.setCardNumber("xx");

        // Possible bug:  the cvv box appears in the browser, but its syntax has no height
        // payment.setCardCVV("047");

        payment.selectSameShipping();

        // validate that aut-complete correcly populates the same as shipping fields

        PageUtils.scrollerHelper(630, 1045, driver);
        payment.processCard();
        actual_exception_string = payment.getErrorSummaries();

        Assert.assertEquals(prop.getProperty("expected_exception_string"), actual_exception_string);
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
