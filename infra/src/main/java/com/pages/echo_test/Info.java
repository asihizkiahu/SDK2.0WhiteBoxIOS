package com.pages.echo_test;

import com.ui.page.AppiumBasePage;
import com.ui.page.base.NotInPageException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by asih on 21/04/2015.
 */
public class Info extends AppiumBasePage {


    private final By SET_ACCOUNT_SKILL = By.id(InfraConstants.APP_ID_PREFIX + "setAccountSkillButton");
    private final By ACCOUNT_NAME = By.id(InfraConstants.APP_ID_PREFIX + "mixAccountEditText");
    private final By SKILL = By.id(InfraConstants.APP_ID_PREFIX + "mixSkillEditText");
    private final By SET = By.id(InfraConstants.APP_ID_PREFIX + "setCurrentButton");
    private final By ACC_SKILL_STATUS = By.id(InfraConstants.APP_ID_PREFIX + "currentValTextView");

    private Info.Activate activate = this.new Activate();
    private Info.Validate validate = this.new Validate();

    private WebElement skill;

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
        public void verifySkillRemainLegual(String _skill) throws Exception {
            Assert.assertEquals("Skill changed when not expected", _skill,
                    service.findElement(ACC_SKILL_STATUS, className + "=setAccountSkill").getText());;
            service.getDriver().navigate().back();
        }
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





