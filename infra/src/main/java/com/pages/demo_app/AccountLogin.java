package com.pages.demo_app;




import com.ui.page.AppiumBasePage;
import com.ui.page.base.NotInPageException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by asih on 18/03/2015.
 */
public class AccountLogin extends AppiumBasePage {

    private final By ACCOUNT_ID = By.id("io.look.lpmobiledemo:id/account");
    private final By USER_NAME = By.id("io.look.lpmobiledemo:id/username");
    private final By PASSWORD = By.id("io.look.lpmobiledemo:id/password");
    private final By SIGN_IN = By.id("io.look.lpmobiledemo:id/sign_in_button");

    private WebElement accountId;
    private WebElement userName;
    private WebElement password;
    private WebElement signIn;

    private AccountLogin.Activate activate = this.new Activate();
    private AccountLogin.Validate validate = this.new Validate();


    public AccountLogin(boolean shouldValidateOnPage, boolean shouldFailTestOnLocation){
        super(shouldValidateOnPage, shouldFailTestOnLocation);
    }

    @Override
    public void prepareElements() {
        accountId = service.findElement(ACCOUNT_ID, className + "=accountId");
        userName = service.findElement(USER_NAME, className + "=userName");
        password = service.findElement(PASSWORD, className + "=password");
        signIn = service.findElement(SIGN_IN, className + "=password");
    }

    public class Activate {
        public void feedAccountDetails(String _accountId, String _userName, String _password) throws Exception{
            accountId.sendKeys(_accountId);
            userName.sendKeys(_userName);
            password.sendKeys(_password);
            signIn.click();
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

    String unique = "Username";
    @Override
    public String getPageUniqueIdentifier() throws NotInPageException {
        return unique;
    }
}


