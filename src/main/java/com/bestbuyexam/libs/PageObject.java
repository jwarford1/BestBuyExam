package com.bestbuyexam.libs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by johnwarford on 2017-10-27.
 */
public class PageObject {

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
}
