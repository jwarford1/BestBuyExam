package com.bestbuyexam.libs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by johnwarford on 2017-10-27.
 */
public class BestBuyDeliveryDetails extends PageObject {

    @FindBy(id = "ctl00_CP_checkoutSections_ctl01_DeliveryOptionTabs1_manageShippingAddress_pnlNewShippingAddress")
    private WebElement deliveryAddressForm;

    @FindBy(id = "ctl00_CP_checkoutSections_ctl01_DeliveryOptionTabs1_manageShippingAddress_oeaUseNew_addressUC_FirstNameContainer_TxtFirstName")
    private WebElement firstNameInput;

    @FindBy(id = "ctl00_CP_checkoutSections_ctl01_DeliveryOptionTabs1_manageShippingAddress_oeaUseNew_addressUC_LastNameContainer_txtLastName")
    private WebElement lastNameInput;

    @FindBy(id = "ctl00_CP_checkoutSections_ctl01_DeliveryOptionTabs1_manageShippingAddress_oeaUseNew_addressUC_AddressLine1Container_TxtAddressLine1")
    private WebElement addressInput;

    @FindBy(id = "ctl00_CP_checkoutSections_ctl01_DeliveryOptionTabs1_manageShippingAddress_oeaUseNew_addressUC_CityContainer_TxtCity")
    private WebElement cityInput;

    @FindBy(id = "ctl00_CP_checkoutSections_ctl01_DeliveryOptionTabs1_manageShippingAddress_oeaUseNew_addressUC_StateContainer_DdlState")
    private WebElement provinceDropdown;

    @FindBy(id = "ctl00_CP_checkoutSections_ctl01_DeliveryOptionTabs1_manageShippingAddress_oeaUseNew_addressUC_PostalCodeContainer_TxtZipCode")
    private WebElement postalCodeInput;

    @FindBy(id = "ctl00_CP_checkoutSections_ctl01_DeliveryOptionTabs1_manageShippingAddress_oeaUseNew_addressUC_CountryContainer_DdlCountry")
    private WebElement countryDropdown;

    @FindBy(id = "ctl00_CP_checkoutSections_ctl01_DeliveryOptionTabs1_manageShippingAddress_oeaUseNew_addressUC_PhoneContainer_TxtPhone")
    private WebElement phoneAreaCodeInput;

    @FindBy(id = "ctl00_CP_checkoutSections_ctl01_DeliveryOptionTabs1_manageShippingAddress_oeaUseNew_addressUC_Phone1Container_TxtPhone1")
    private WebElement phoneNxxInput;

    @FindBy(id = "ctl00_CP_checkoutSections_ctl01_DeliveryOptionTabs1_manageShippingAddress_oeaUseNew_addressUC_Phone2Container_TxtPhone2")
    private WebElement phoneNumberInput;

    @FindBy(id = "ctl00_CP_checkoutSections_ctl01_DeliveryOptionTabs1_btnContinueFromShipping")
    private WebElement continueButton;

    @FindBy(id = "ctl00_CP_ErrorSummaryUC2_ValidationSummary1")
    private WebElement validationErrorsBlock;



    public BestBuyDeliveryDetails(WebDriver driver) {
        super(driver);
    }

    public void setFirstName(String firstName){
        firstNameInput.sendKeys(firstName);
    }

    public void setLastName(String lastName){
        lastNameInput.sendKeys(lastName);
    }

}
