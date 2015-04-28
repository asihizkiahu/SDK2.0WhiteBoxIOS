package com.pages.demo_app;

import com.ui.page.AppiumBasePage;
import com.ui.page.base.NotInPageException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by asih on 08/03/2015.
 */
public class Chat extends AppiumBasePage {

    private final String SEND = "Send";
    private final String END_SESSION = "End Session";

    private final By MSG = By.className("android.widget.EditText");
    private final By BUTTON = By.className("android.widget.Button");
    private final By APPRROVE_END_SESSION = By.id("android:id/button1");

    private Chat.Activate activate = this.new Activate();
    private Chat.Validate validate = this.new Validate();

    private WebElement approveEndSession;


    public Chat(boolean shouldValidateOnPage, boolean shouldFailTestOnLocation) {
        super(shouldValidateOnPage, shouldFailTestOnLocation);
    }

    @Override
    public void prepareElements() {
    }

    public class Activate {

        public void sendMsg(String msg) {
            service.findElement(MSG, this.getClass().getName() + "=name").sendKeys(msg);
            service.getElementByText(BUTTON, SEND).click();
        }

        public void ensSession() {
            service.scroll(END_SESSION);
            service.getElementByText(BUTTON, END_SESSION).click();
            approveEndSession = service.findElement(APPRROVE_END_SESSION, this.getClass().getName() + "=approveEndSession");
            approveEndSession.click();
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

    String unique = "Hi there! Interested in LivePerson solutions? You're in the right place. How can I help you today?";
    @Override
    public String getPageUniqueIdentifier() throws NotInPageException {
        return unique;
    }
}
