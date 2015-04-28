package com.pages.demo_app;

import com.ui.page.AppiumBasePage;
import com.ui.page.base.NotInPageException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by asih on 01/03/2015.
 */
public class NativeAppSearch extends AppiumBasePage {

    private final String chooseAppMsg = "Please search the Google Play Store for an app you'd like to try chat on:";
    private static final By CHOOSEMSG = By.id("io.look.lpmobiledemo:id/textView1");

    private final By APP_NAME = By.id("io.look.lpmobiledemo:id/app_name");
    private final By FIND_APP = By.id("io.look.lpmobiledemo:id/sign_in_button");

    private WebElement appName;
    private WebElement findApp;

    private NativeAppSearch.Activate activate = this.new Activate();
    private NativeAppSearch.Validate validate = this.new Validate();

    public NativeAppSearch(boolean shouldValidateOnPage, boolean shouldFailTestOnLocation){
        super(shouldValidateOnPage, shouldFailTestOnLocation);
    }

    @Override
    public void prepareElements() {
        appName = service.findElement(APP_NAME, className + "=appName");
        findApp = service.findElement(FIND_APP, className + "=findApp");
    }

    public class Activate {
        public void feedAppName(String expression){
            appName.sendKeys(expression);
        }

        public void findApp(){
            findApp.click();
        }
    }

    public class Validate {

        public void validateChooseAppMsg(){
            service.isMsgInLocator(CHOOSEMSG, chooseAppMsg, className);
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

    @Override
    public String getPageUniqueIdentifier() throws NotInPageException {
        return chooseAppMsg;
    }

}
