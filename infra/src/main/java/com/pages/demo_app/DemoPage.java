package com.pages.demo_app;

import com.ui.page.AppiumBasePage;
import com.ui.page.base.NotInPageException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by asih on 01/03/2015.
 */
public class DemoPage extends AppiumBasePage {

    private final By START_DEMO = By.id("android:id/title");

    private WebElement startDemo;

    private DemoPage.Activate activate = this.new Activate();
    private DemoPage.Validate validate = this.new Validate();

    public DemoPage(boolean shouldValidateOnPage, boolean shouldFailTestOnLocation){
        super(shouldValidateOnPage, shouldFailTestOnLocation);
    }

    @Override
    public void prepareElements() {
        startDemo = service.findElement(START_DEMO, className + "=startDemo");
    }

    public class Activate {
        public void startDemo(){
            startDemo.click();
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

    String unique = "Start the Demo";

    @Override
    public String getPageUniqueIdentifier() throws NotInPageException {
        return unique;
    }
}
