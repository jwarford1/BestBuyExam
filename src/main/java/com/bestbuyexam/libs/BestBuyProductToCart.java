package com.bestbuyexam.libs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by johnwarford on 2017-10-27.
 */
public class BestBuyProductToCart extends PageObject {

    @FindBy(id="btn-cart")
    private WebElement submitToCart;

    @FindBy(className = "cart-message")
    private WebElement cartMessage;

    @FindBy(xpath = "//img[contains(@src,'/images/common/checkout/icon_lock.png')]")
    private WebElement checkout;

    // validate in stock

    // validate product made it to this page

    public BestBuyProductToCart(WebDriver driver) {
        super(driver);
    }

    public boolean validateProducToCart(String product)
    {
        String prodName =  this.driver.findElement(By.id("ctl00_CP_ctl00_PD_lblProductTitle")).getText();
        if(product.contains(prodName)) {
            return true;
        }
        return false;
    }
    public void addItemToCart()
    {
        this.submitToCart.click();
    }

    public String validateCart()
    {
        return cartMessage.getText();
    }

    public void submitForCheckout()
    {
        checkout.click();
    }

}
