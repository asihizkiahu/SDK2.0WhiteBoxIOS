package com.pages.echo_test;

import com.ui.page.AppiumBasePage;
import com.ui.page.base.NotInPageException;
import com.util.genutil.GeneralUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by asih on 08/03/2015.
 */
public class Chat extends AppiumBasePage {

    private final By MSG = By.className("android.widget.EditText");
    private final By BUTTON = By.className("android.widget.Button");
    protected final String sendText = "Send";
    protected final String endSessionText = "End Session";
    private final By ACCEPT_END = By.id("android:id/button1");
    //    private final By Send = By.id(InfraConstants.CHAT_PAGE_PREFIX + "6");
    private final By CHAT_MESSAGES = By.className("android.widget.TextView");
    private static final Logger logger = Logger.getLogger(Chat.class);


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
            service.implicitWait(1500);
        }

        public void ensSession() {
            service.scroll("End Session");
            service.getElementByText(BUTTON, endSessionText).click();
            service.findElement(ACCEPT_END, className + "=acceptEnd").click();
        }
    }

    public class Validate {

        public boolean isMsgAppearInChat(String msg){
            logger.info("Going to validate msg " + msg);
            List<WebElement> messages;
            try {
                messages = service.getDriver().findElements(CHAT_MESSAGES);
            }catch (Exception e){
                return false;
            }
            for(WebElement message : messages){
                logger.info("MSG text is = " + message.getText());
                if(message.getText().contains(msg)){
                    return true;
                }
            }
            return false;
        }

        public boolean isSystemMsgAppearInTop(String msg, long timeOutInMilisec){
            long interval = 200;
            while (!isMsgAppearInChat(msg)){
                try {
                    Thread.sleep(interval);
                    timeOutInMilisec -= interval;
                    if(timeOutInMilisec <= 0){
                        GeneralUtils.handleError("Time out finished without finding results",
                                new Exception("Time out finished without finding results"));
                        return false;
                    }
                }catch (InterruptedException e) {
                    GeneralUtils.handleError("Error in wait for time out", e);
                    return false;
                }
            }
            return true;
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

String unique = "Send a message";
    @Override
    public String getPageUniqueIdentifier() throws NotInPageException {
        return unique;
    }
}
