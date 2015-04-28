package com.service.activate.demo_app;

import com.pages.demo_app.Chat;
import com.pages.demo_app.ChooseCustomerType;
import com.pages.demo_app.PersonalInfo;
import org.apache.log4j.Logger;

/**
 * Created by asih on 09/03/2015.
 */
public class ChatActivator {

    private static final ChatActivator INSTANCE = new ChatActivator();
    private static final Logger logger = Logger.getLogger(ChatActivator.class);

    private PersonalInfo info = new PersonalInfo(true, true);
    private Chat chatPage = new Chat(true, true);

    private ChatActivator(){

    }

    public static ChatActivator getInstance(){
        return INSTANCE;
    }

    public void feedPersonalInfo(String name, String email, String phone, ChooseCustomerType.CustomerType type) throws Exception {
        info.validateInPage();
        info.prepareElements();
        info.getActivate().feedPersonalInfo(name, email, phone, type);
    }

    public void sendChatMsg(String msg) throws Exception {
        chatPage.validateInPage();
        chatPage.prepareElements();
        chatPage.getActivate().sendMsg(msg);
    }

    public void ensSession(){
        chatPage.getActivate().ensSession();
    }

}
