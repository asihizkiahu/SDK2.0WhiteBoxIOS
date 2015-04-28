package com.pages.demo_app;

import com.ui.page.AppiumBasePage;
import com.ui.page.base.NotInPageException;
import org.openqa.selenium.By;

/**
 * Created by asih on 01/03/2015.
 */
public class PersonalInfo extends AppiumBasePage {

    private final String CANCEL = "Cancel";
    private final String NEXT = "Next";
    private final String BACK = "Back";
    private final String DONE = "Done";

    private final By NAME = By.className("android.widget.EditText");

    private final By EMAIL = By.className("android.widget.EditText");

    private final By PHONE = By.className("android.widget.EditText");

    private final By BUTTON = By.className("android.widget.Button");

    private PersonalInfo.Activate activate = this.new Activate();
    private PersonalInfo.Validate validate = this.new Validate();

    public PersonalInfo(boolean shouldValidateOnPage, boolean shouldFailTestOnLocation){
        super(shouldValidateOnPage, shouldFailTestOnLocation);
    }

    @Override
    public void prepareElements() {
    }

    public class Activate {
        public void feedPersonalInfo(String name, String email, String phone, ChooseCustomerType.CustomerType type){
            service.getElementByText(BUTTON, NEXT).click();
            service.findElement(NAME, this.getClass().getName() + "=name").sendKeys(name);
            service.getElementByText(BUTTON, NEXT).click();
            service.findElement(EMAIL, this.getClass().getName() + "=email").sendKeys(email);
            if(type == ChooseCustomerType.CustomerType.POTENTIAL_COSTUMER) {
                service.getElementByText(BUTTON, DONE).click();
            }
            else{
                service.getElementByText(BUTTON, NEXT).click();
                service.findElement(PHONE, this.getClass().getName() + "=email").sendKeys(phone);
                service.getElementByText(BUTTON, DONE).click();
            }
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

    String unique = "To help us serve you better";
    @Override
    public String getPageUniqueIdentifier() throws NotInPageException {
        return unique;
    }
}
