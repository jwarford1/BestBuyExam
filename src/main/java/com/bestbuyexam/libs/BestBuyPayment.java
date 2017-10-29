package com.bestbuyexam.libs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by johnwarford on 2017-10-27.
 */
public class BestBuyPayment extends PageObject{

    @FindBy(xpath = "//img[contains(@src,'/images/common/checkout/visa.png')]")
    private WebElement ccImageVisa;

    @FindBy(className = "payment-select")
    private WebElement selectCC;

    @FindBy(id = "ctl00_CP_checkoutSections_ctl03_ucPaymentEdit_UCEditCreditCard_AddCreditCardUC_CardTypeContainer_DdlCardType")
    private WebElement cardTypeInput;

    @FindBy(id = "ctl00_CP_checkoutSections_ctl03_ucPaymentEdit_UCEditCreditCard_AddCreditCardUC_MonthContainer_DdlMonth")
    private WebElement cardMonthDropdown;

    @FindBy(id = "ctl00_CP_checkoutSections_ctl03_ucPaymentEdit_UCEditCreditCard_AddCreditCardUC_YearContainer_DdlYear")
    private WebElement cardYearDropdown;

    @FindBy(id = "ctl00_CP_checkoutSections_ctl03_ucPaymentEdit_UCEditCreditCard_EditCreditCardUC_CIDNumberContainer_TxtCID")
    private WebElement cardCVV;

    //<input name="ctl00$CP$checkoutSections$ctl03$ucPaymentEdit$UCEditCreditCard$AddCreditCardUC$CIDNumberContainer$TxtCID" type="password" maxlength="4" id="ctl00_CP_checkoutSections_ctl03_ucPaymentEdit_UCEditCreditCard_AddCreditCardUC_CIDNumberContainer_TxtCID" class="txtbox small tbox-cid" autocomplete="off" onblur="if (this.onchange!=null)this.onchange();">

    @FindBy(id = "ctl00_CP_checkoutSections_ctl03_ucPaymentEdit_UCEditCreditCard_AddCreditCardUC_CreditCardNumberContainer_TxtCardNumber")
    private WebElement cardNumber;

    @FindBy(id = "ctl00_CP_checkoutSections_ctl03_ucPaymentEdit_UCEditCreditCard_ChkSameAsShipping")
    private WebElement seletShippingSameAsDetails;

    @FindBy(id = "ctl00_CP_checkoutSections_ctl03_ucPaymentEdit_BtnContinueFromPayment")
    private WebElement continuePaymentButton;

    //@FindBy(id = "ctl00_CP_ErrorSummaryUC12_ValidationSummary1")
    @FindBy(xpath = "//*[@id='ctl00_CP_ErrorSummaryUC12_ValidationSummary1']/ul/li[1]")
    private WebElement errorSummary;

    @FindBy(id = "ctl00_CP_checkoutSections_ctl03_ucPaymentEdit_UCEditCreditCard_NewAddressUC_FirstNameContainer_TxtFirstName")
    private WebElement sameAsFirstName;

    @FindBy(id = "ctl00_CP_checkoutSections_ctl03_ucPaymentEdit_UCEditCreditCard_NewAddressUC_LastNameContainer_txtLastName")
    private WebElement sameAsLastName;


    public BestBuyPayment(WebDriver driver) {
        super(driver);
    }

    public void selectCreditCard()
    {
        this.selectCC.click();
    }

    public void setCardType(String cardType){
        Select select = new Select(cardTypeInput);
        select.selectByVisibleText(cardType);
    }

    public void setCardExpirationMonth(String cardExpirationMonth){
        Select select = new Select(cardMonthDropdown);
        select.selectByVisibleText(cardExpirationMonth);
    }

    public void setCardExpirationYear(String cardExpirationYear){
        Select select = new Select(cardYearDropdown);
        select.selectByVisibleText(cardExpirationYear);
    }

    public void setCardNumber(String cardNumber){
        this.cardNumber.sendKeys(cardNumber);
    }

    public void setCardCVV(String cvv) {

        this.cardCVV.sendKeys(cvv);
    }

    public void selectSameShipping()
    {
        this.seletShippingSameAsDetails.click();
    }

    public String getSameAsShippingFirstName()
    {
        return this.sameAsFirstName.getAttribute("value");
    }

    public String getSameAsShippingLasttName()
    {
        return this.sameAsLastName.getAttribute("value");
    }

    public void processCard()
    {
        this.continuePaymentButton.click();

    }

    public String getErrorSummaries()
    {
        return this.errorSummary.getText();
    }




}
