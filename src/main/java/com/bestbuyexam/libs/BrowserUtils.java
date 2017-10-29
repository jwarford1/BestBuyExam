package com.bestbuyexam.libs;

import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by johnwarford on 2017-10-29.
 */
public  class BrowserUtils {

    public static DesiredCapabilities getBrowserCapabilities()
    {
        DesiredCapabilities capabilities = null;

        String browser = System.getProperty("browser");

        if(browser.equals("chrome"))
        {
            capabilities = DesiredCapabilities.chrome();
        }

        if(browser.equals("firefox"))
        {
            capabilities = DesiredCapabilities.firefox();
        }

        return capabilities;
    }
}
