package com.pages.echo_test;

import com.ui.page.AppiumBasePage;
import com.ui.page.base.NotInPageException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by asih on 21/04/2015.
 */
public class Info extends AppiumBasePage {


    private final By SET_ACCOUNT_SKILL = By.id(AppConstants.APP_ID_PREFIX + "setAccountSkillButton");
    private final By ACCOUNT_NAME = By.id(AppConstants.APP_ID_PREFIX + "mixAccountEditText");
    private final By SKILL = By.id(AppConstants.APP_ID_PREFIX + "mixSkillEditText");
    private final By SET = By.id(AppConstants.APP_ID_PREFIX + "setCurrentButton");

    private Info.Activate activate = this.new Activate();
    private Info.Validate validate = this.new Validate();


    public Info(boolean shouldValidateOnPage, boolean shouldFailTestOnLocation){
        super(shouldValidateOnPage, shouldFailTestOnLocation);
    }

    @Override
    public void prepareElements() {
    }

    public class Activate {
        public void feedAccountDetails(String _accName, String _skill) throws Exception{
            service.scroll("Set Account Skill");
            service.findElement(SET_ACCOUNT_SKILL, className + "=setAccountSkill").click();
            service.findElement(ACCOUNT_NAME , className + "=accName").sendKeys(_accName);
            service.findElement(SKILL, className + "=skill").sendKeys(_skill);
            service.findElement(SET, className + "=set").click();
            service.getDriver().navigate().back();
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

    String unique = "Set";
    @Override
    public String getPageUniqueIdentifier() throws NotInPageException {
        return unique;
    }

}





