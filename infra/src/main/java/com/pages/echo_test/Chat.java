package com.pages.echo_test;

import com.ui.page.AppiumBasePage;
import com.ui.page.base.NotInPageException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by asih on 08/03/2015.
 */
public class Chat extends AppiumBasePage {

    private final By MSG = By.className("android.widget.EditText");
    private final By BUTTON = By.className("android.widget.Button");
    protected final String sendText = "Send";
    protected final String endSessionText = "End Session";
    private final By ACCEPT_END = By.id("android:id/button1");

    private Chat.Activate activate = this.new Activate();
    private Chat.Validate validate = this.new Validate();

    private WebElement msg;
    private WebElement send;
    private WebElement endSession;
    private WebElement acceptEnd;

    public Chat(boolean shouldValidateOnPage, boolean shouldFailTestOnLocation) {
        super(shouldValidateOnPage, shouldFailTestOnLocation);
    }

    @Override
    public void prepareElements() {
        msg = service.findElement(MSG, className + "=msg");
        send = service.getElementByText(BUTTON, sendText);
    }

    public class Activate {

        public void sendMsg(String _msg) {
            msg.sendKeys(_msg);
            send.click();
        }

        public void ensSession() {
            service.scroll("End Session");
            service.getElementByText(BUTTON, endSessionText).click();
            service.findElement(ACCEPT_END, className + "=acceptEnd").click();
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

    String unique = "Send a message";
    @Override
    public String getPageUniqueIdentifier() throws NotInPageException {
        return unique;
    }
}
