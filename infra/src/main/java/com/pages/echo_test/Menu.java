package com.pages.echo_test;

import com.ui.page.AppiumBasePage;
import com.ui.page.base.NotInPageException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by asih on 18/03/2015.
 */
public class Menu extends AppiumBasePage {

    private final By MENU= By.className("android.widget.ImageButton");
    private final By SUB_MENU= By.id("android:id/title");
    private final String settingsText = "Settings";
    private final String infoText = "Info";

    private WebElement menu;
    private WebElement info;
    private WebElement settings;

    private Menu.Activate activate = this.new Activate();
    private Menu.Validate validate = this.new Validate();


    public Menu(boolean shouldValidateOnPage, boolean shouldFailTestOnLocation){
        super(shouldValidateOnPage, shouldFailTestOnLocation);
    }

    @Override
    public void prepareElements() {
        menu = service.findElement(MENU, className + "=menu");
    }

    public class Activate {
        public void enterMenuSettings() throws Exception{
            menu.click();
            service.getElementByText(SUB_MENU, settingsText).click();
        }

        public void enterMenuInfo() throws Exception{
            menu.click();
            service.getElementByText(SUB_MENU, infoText).click();
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

    String unique = "STIX";
    @Override
    public String getPageUniqueIdentifier() throws NotInPageException {
        return unique;
    }
}



