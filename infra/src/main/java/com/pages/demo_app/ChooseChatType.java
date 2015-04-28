package com.pages.demo_app;

import com.ui.page.AppiumBasePage;
import com.ui.page.base.NotInPageException;
import com.ui.service.AppiumService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by asih on 01/03/2015.
 */
public class ChooseChatType extends AppiumBasePage {

    private final String chooseChatMsg = "Would you like to try out Web Chat over a website, or Native Chat over screenshots of your app?";
    private static final By CHOOSEMSG = By.className("io.look.lpmobiledemo:id/textView");

    private static final By WEB_CHAT = By.id("io.look.lpmobiledemo:id/web_chat_btn");
    private static final By NATIVE_CHAT = By.id("io.look.lpmobiledemo:id/native_chat_btn");
    protected static AppiumService service = AppiumService.getInstance();

    private ChooseChatType.Activate activate = this.new Activate();
    private ChooseChatType.Validate validate = this.new Validate();

    public ChooseChatType(boolean shouldValidateOnPage, boolean shouldFailTestOnLocation){
        super(shouldValidateOnPage, shouldFailTestOnLocation);
    }

    @Override
    public void prepareElements() {
    }

    public class Activate {
        public void chooseChatType(ChatType type){
            type.chat.click();
        }
    }

    public class Validate {
//        public void validateChooseChatMsg(){
//            service.isMsgInLocator(CHOOSEMSG, chooseChatMsg, className);
//        }
    }

    public enum ChatType{
        WEB(service.findElement(WEB_CHAT, ChooseChatType.class.getName() + "=webChat")),
        NATIVE(service.findElement(NATIVE_CHAT, ChooseChatType.class.getName() + "=nativeChat"));

        WebElement chat;

        ChatType(WebElement chat) {
            this.chat = chat;
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
        return chooseChatMsg;
    }
}
