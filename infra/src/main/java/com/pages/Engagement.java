package com.pages;

import com.ui.page.AppiumBasePage;
import com.ui.page.base.NotInPageException;
import org.openqa.selenium.Dimension;

/**
 * Created by asih on 11/03/2015.
 */
public class Engagement extends AppiumBasePage {

    private Engagement.Activate activate = this.new Activate();
    private Engagement.Validate validate = this.new Validate();

    public Engagement(boolean shouldValidateOnPage, boolean shouldFailTestOnLocation){
        super(shouldValidateOnPage, shouldFailTestOnLocation);
    }

    @Override
    public void prepareElements() {
    }

    public class Activate {
        public void enterChat() throws Exception{
            service.implicitWait(1500);
            Dimension dimensions = service.getDriver().manage().window().getSize();
            service.tap(dimensions.getWidth() - 20, dimensions.getHeight() / 2);
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

    @Override
    public String getPageUniqueIdentifier() throws NotInPageException {
        return "";
    }
}
