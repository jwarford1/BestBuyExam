package com.bestbuyexam.libs;

/**
 * Created by johnwarford on 2017-10-26.
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by johnwarford on 2017-10-27.
 */
public class BestBuyShopByCategory extends PageObject{

    @FindBy(id="ctl00_MasterHeader_ctl00_uchead_GlobalSearchUC_TxtSearchKeyword")
    private WebElement keywordSearch;

    @FindBy(id="ctl00_MasterHeader_ctl00_uchead_GlobalSearchUC_BtnSubmitSearch")
    private WebElement submitBtnForKeywordSearch;

    // Find categories urls via FindBy linktext here
    // for the option of searching by category
    @FindBy (linkText = "Computers, Tablets & Accessories")
    private WebElement linkComputersCategory;

    public BestBuyShopByCategory(WebDriver driver) {super(driver);}

    public void clearSearchBox(){
        this.keywordSearch.clear();

    }

    public void sendSearchKeyWords(String prdSearchTerm){

        this.keywordSearch.sendKeys(prdSearchTerm);

    }

    public void submitSearchTerm(){

        this.submitBtnForKeywordSearch.click();

    }

    public void searchComputersByCategory()
    {
        this.linkComputersCategory.click();
    }
}
