package com.pages.echo_test;

import com.ui.page.AppiumBasePage;
import com.ui.page.base.NotInPageException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by asih on 21/04/2015.
 */
public class Settings extends AppiumBasePage{


    private final By ACCOUNT_ID = By.id(AppConstants.APP_ID_PREFIX + "settings_app_id");
    private final By STAGING = By.id(AppConstants.APP_ID_PREFIX + "settings_staging");
    private final By SAVE_AND_CLOSE = By.id(AppConstants.APP_ID_PREFIX + "settings_save_and_close");

    private WebElement accountId;
    private WebElement staging;
    private WebElement saveAndClose;

    private Settings.Activate activate = this.new Activate();
    private Settings.Validate validate = this.new Validate();


    public Settings(boolean shouldValidateOnPage, boolean shouldFailTestOnLocation){
        super(shouldValidateOnPage, shouldFailTestOnLocation);
    }

    @Override
    public void prepareElements() {
        accountId = service.findElement(ACCOUNT_ID, className + "=accountId");
        staging = service.findElement(STAGING , className + "=staging");
        saveAndClose = service.findElement(SAVE_AND_CLOSE, className + "=saveAndClose");
    }

    public class Activate {
        public void feedAccountDetails(String _accountId) throws Exception{
            accountId.sendKeys(_accountId);
            staging.click();
            saveAndClose.click();
        }
    }

    public class Validate {
    }

    @Override
    public Validate getValidate() {
        return validate;
    }

    @Override
    public Activate getActivate() {
        return activate;
    }

    String unique = "Staging";
    @Override
    public String getPageUniqueIdentifier() throws NotInPageException {
        return unique;
    }
}



