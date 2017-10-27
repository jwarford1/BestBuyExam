package com.bestbuyexam.libs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by johnwarford on 2017-10-27.
 */
public class BestBuyCheckout extends PageObject {

    @FindBy(id="ctl00_CP_UcCheckoutSignInUC_radioButtonNew")
    private WebElement guestCheckout;

    @FindBy(xpath = "//img[contains(@src,'https://images.bbycastatic.ca/sf/images/common/checkout/icon_lock.png')]")
    private WebElement continueCheckout;

    public BestBuyCheckout(WebDriver driver) {
        super(driver);
    }

    public void selectGuestCheckout()
    {
        this.guestCheckout.click();
    }

    public void continueCheckout()
    {
        this.continueCheckout.click();
    }
}
