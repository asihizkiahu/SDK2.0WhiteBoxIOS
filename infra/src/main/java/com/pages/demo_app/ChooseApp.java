package com.pages.demo_app;

import com.ui.page.AppiumBasePage;
import com.ui.page.base.NotInPageException;
import org.openqa.selenium.By;

/**
 * Created by asih on 01/03/2015.
 */
public class ChooseApp extends AppiumBasePage {

    private final String searchResultMsg = "Search Results";
    private static final By HEADER_MSG = By.id("android:id/action_bar_title");

    private final By APP_CLASS = By.id("io.look.lpmobiledemo:id/appName");

    private ChooseApp.Activate activate = this.new Activate();
    private ChooseApp.Validate validate = this.new Validate();

    public ChooseApp(boolean shouldValidateOnPage, boolean shouldFailTestOnLocation){
        super(shouldValidateOnPage, shouldFailTestOnLocation);
    }

    @Override
    public void prepareElements() {

    }

    public class Activate {
        public void chooseApp(String appName){
            service.getElementByText(APP_CLASS, appName).click();
        }
    }

    public class Validate {

        public void validateAppSearchHeader(){
            service.isMsgInLocator(HEADER_MSG, searchResultMsg, className);
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
        return searchResultMsg;
    }
}
