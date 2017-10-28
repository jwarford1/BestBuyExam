package com.bestbuyexam.libs;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by johnwarford on 2017-10-27.
 */
public class PageUtils {

    public static void closePopup(WebDriver driver)
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement closeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src,'/images/common/checkout/icon_alert_sm.png')]")));
            System.out.println( "found image");
        }
        catch (TimeoutException t)
        {
            System.out.println( "No pop up detected");
        }
    }
}
