package com.pages.demo_app;

import com.ui.page.AppiumBasePage;
import com.ui.page.base.NotInPageException;
import com.ui.service.AppiumService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by asih on 18/03/2015.
 */
public class ChooseCustomerType extends AppiumBasePage {

    private final String chooseCustomerTypeMsg = "Enhance your native apps and mobile website with live chat";
    private static final By CHOOSEMSG = By.id("io.look.lpmobiledemo:id/textView1");

    private static final By EXISTING_BUTTON = By.id("io.look.lpmobiledemo:id/existingCustomerButton");
    private static final By POTENTIAL_BUTTON = By.id("io.look.lpmobiledemo:id/potentialCustomerButton");
    protected static AppiumService service = AppiumService.getInstance();

    private ChooseCustomerType.Activate activate = this.new Activate();
    private ChooseCustomerType.Validate validate = this.new Validate();

    public ChooseCustomerType(boolean shouldValidateOnPage, boolean shouldFailTestOnLocation){
        super(shouldValidateOnPage, shouldFailTestOnLocation);
    }

    @Override
    public void prepareElements() {
    }

    public class Activate {
        public void chooseCostumerType(CustomerType type){
            type.costumer.click();
        }
    }

    public class Validate {
//        public void validateChooseCostumerMsg(){
//            service.isMsgInLocator(CHOOSEMSG, chooseCustomerTypeMsg, className);
//        }
    }

    public enum CustomerType{
        EXISTING_COSTUMER(service.findElement(EXISTING_BUTTON, ChooseChatType.class.getName() + "=existingCostumer")),
        POTENTIAL_COSTUMER(service.findElement(POTENTIAL_BUTTON, ChooseChatType.class.getName() + "=potentialCostumer"));

        WebElement costumer;

        CustomerType(WebElement costumer) {
            this.costumer = costumer;
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
        return chooseCustomerTypeMsg;
    }
}
