package com.bestbuyexam.libs;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by johnwarford on 2017-10-27.
 */
public class PageObject {

    @FindBy(className = "warranty-opt-out-trigger")
    WebElement optOut;

    protected WebDriver driver;
    public PageObject(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle()
    {
        return this.driver.getTitle();
    }

    public void closeModalPopup()
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement closeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("at-close-icon")));
            closeButton.click();
        }
        catch (TimeoutException t)
        {
            System.out.println( "No pop up detected");
        }
    }

    public void dismissWarranty()
    {

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement closeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart-message")));
            optOut.click();
        }
        catch (TimeoutException t)
        {
            System.out.println( "No pop up detected");
        }
    }
}
