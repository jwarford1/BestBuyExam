package com.bestbuyexam.libs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by johnwarford on 2017-10-27.
 */
public class BestBuyMonitorsCategoryPage extends PageObject {

    @FindBy(id = "ctl00_CP_ctl00_LeftNavigationMenu1_RepCategoryMenu_ctl01_HC")
    private WebElement ledMonitorNavLink;

    public BestBuyMonitorsCategoryPage(WebDriver driver) {
        super(driver);
    }

    // clicks the very first element of the css classe
    // representing items available in the given catgery
    public String  selectFirstItemInCatgeory()
    {
        // System.out.println( this.driver.findElement(By.className("product-title-wrapper")).getAttribute("innerHTML"));
        String prodName =  this.driver.findElement(By.className("prod-title")).getAttribute("innerHTML");
        this.driver.findElement(By.className("prod-title")).click();
        return prodName;

    }

    public void selectLedMonitorType()
    {
        this.ledMonitorNavLink.click();
    }
}
