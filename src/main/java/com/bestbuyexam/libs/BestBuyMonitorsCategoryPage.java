package com.bestbuyexam.libs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by johnwarford on 2017-10-27.
 */
public class BestBuyMonitorsCategoryPage extends PageObject {

    public BestBuyMonitorsCategoryPage(WebDriver driver) {
        super(driver);
    }

    // clicks the very first element of the css classe
    // representing items available in the given catgery
    public void selectFirstItemInCatgeory()
    {

                this.driver.findElement(By.className("prod-image")).click();
                int x = 0;

    }
}
