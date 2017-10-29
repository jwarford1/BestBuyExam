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

    @FindBy(id = "ctl00_CP_checkoutSections_ctl03_ucPaymentEdit_UCEditCreditCard_NewAddressUC_AddressLine1Container_TxtAddressLine1")
    private WebElement sameAsAddress;

    @FindBy(id = "ctl00_CP_checkoutSections_ctl03_ucPaymentEdit_UCEditCreditCard_NewAddressUC_CityContainer_TxtCity")
    private WebElement sameAsCity;

    @FindBy(id = "ctl00_CP_checkoutSections_ctl03_ucPaymentEdit_UCEditCreditCard_NewAddressUC_StateContainer_DdlState")
    private WebElement sameAsStateOrProvince;

    @FindBy(id = "ctl00_CP_checkoutSections_ctl03_ucPaymentEdit_UCEditCreditCard_NewAddressUC_PostalCodeContainer_TxtZipCode")
    private WebElement sameAsPostalCode;

    @FindBy(id = "ctl00_CP_checkoutSections_ctl03_ucPaymentEdit_UCEditCreditCard_NewAddressUC_CountryContainer_DdlCountry")
    private WebElement sameAsCountry;

    @FindBy(id = "ctl00_CP_checkoutSections_ctl03_ucPaymentEdit_UCEditCreditCard_NewAddressUC_CountryContainer_DdlCountry")
    private WebElement sameAsAreaCode;

    @FindBy(id = "ctl00_CP_checkoutSections_ctl03_ucPaymentEdit_UCEditCreditCard_NewAddressUC_Phone2Container_TxtPhone2")
    private WebElement sameAsPhoneSuffix;

    @FindBy(id = "ctl00_CP_checkoutSections_ctl03_ucPaymentEdit_UCEditCreditCard_NewAddressUC_Phone1Container_TxtPhone1")
    private WebElement sameAsPhonePrefix;

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

    public String getSameAsShippingAddress()
    {
        return this.sameAsAddress.getAttribute("value");
    }

    public String getSameAsShippingCity()
    {
        return this.sameAsCity.getAttribute("value");
    }

    public String getSameAsShippingStateOrProvince()
    {
        return this.sameAsStateOrProvince.getAttribute("value");
    }

    public String getSameAsShippingPostalCode()
    {
        return this.sameAsPostalCode.getAttribute("value");
    }

    public String getSameAsShippingCountry()
    {
        return this.sameAsCountry.getAttribute("value");
    }

    public String getSameAsShippingAreaCode()
    {
        return this.sameAsAreaCode.getAttribute("value");
    }

    public String getSameAsShippingPhonePrefix()
    {
        return this.sameAsPhonePrefix.getAttribute("value");
    }

    public String getSameAsShippingPhoneSuffix()
    {
        return this.sameAsPhoneSuffix.getAttribute("value");
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
