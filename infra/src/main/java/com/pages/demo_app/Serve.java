package com.pages.demo_app;

import com.ui.page.AppiumBasePage;
import com.ui.page.base.NotInPageException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by asih on 10/03/2015.
 */
public class Serve extends AppiumBasePage{

    private final By BUTTON = By.className("android.widget.Button");

    private final String CANCEL = "Cancel";
    private final String APPROVE = "Next";
    private final String DONE = "Done";
    private final String BACK = "Back";

    private final By RADIO_BUTTON = By.className("android.widget.RadioButton");

    private final String ISSUE_RESOLVD = "Yes, the issue was resolved";
    private final String ISSUE_NOT_RESOLVD = "No, the issue was not resolved";
    private final String SEND_TRANSCRIPT = "yes";
    private final String DONT_SEND_TRANSCRIPT = "no";

    private final By SATISFECTION_BAR = By.className("");
    private final By EMAIL = By.className("android.widget.EditText");

    private WebElement mail;

    private Serve.Activate activate = this.new Activate();
    private Serve.Validate validate = this.new Validate();

    public Serve(boolean shouldValidateOnPage, boolean shouldFailTestOnLocation){
        super(shouldValidateOnPage, shouldFailTestOnLocation);
    }

    @Override
    public void prepareElements() {
    }

    public class Activate {

        public void feedServeInfo(Satisfection rate, boolean isResolved, boolean isSendTranscript, String email){
            service.getElementByText(BUTTON, APPROVE).click();
            setSatisfection(rate);
            setIsResolved(isResolved);
            setIsSendTranscript(isSendTranscript, email);
        }

        private void setSatisfection(Satisfection rate){

            service.getElementByText(BUTTON, APPROVE).click();
        }

        private void setIsResolved(boolean isResolved){
            if(isResolved){
                service.getElementByText(RADIO_BUTTON, ISSUE_RESOLVD).click();
            } else{
                service.getElementByText(RADIO_BUTTON, ISSUE_NOT_RESOLVD).click();
            }
            service.getElementByText(BUTTON, APPROVE).click();
        }

        private void setIsSendTranscript(boolean isSendTranscript, String email){
            if(isSendTranscript){
                service.getElementByText(RADIO_BUTTON, SEND_TRANSCRIPT).click();
            } else{
                service.getElementByText(RADIO_BUTTON, DONT_SEND_TRANSCRIPT).click();
            }
            service.getElementByText(BUTTON, APPROVE).click();
            mail = service.findElement(EMAIL, this.getClass().getName() + "=name");
            mail.clear();
            mail.sendKeys(email);
            service.getElementByText(BUTTON, DONE).click();
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

    String unique = "Thanks for chatting. Please provide us with your feedback";
    @Override
    public String getPageUniqueIdentifier() throws NotInPageException {
        return unique;
    }

    public enum Satisfection{

        VERY_DISSATISFIED, DISSATISFIED, SATISFIED, VERY_SATISFIED;

    }
}


