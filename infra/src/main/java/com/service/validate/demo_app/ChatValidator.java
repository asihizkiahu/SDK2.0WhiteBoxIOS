package com.service.validate.demo_app;

import com.pages.demo_app.Chat;
import com.pages.demo_app.PersonalInfo;
import org.apache.log4j.Logger;

/**
 * Created by asih on 09/03/2015.
 */


public class ChatValidator {

    private static final ChatValidator INSTANCE = new ChatValidator();
    private static final Logger logger = Logger.getLogger(ChatValidator.class);


    private PersonalInfo info = new PersonalInfo(true, true);
    private Chat chatPage = new Chat(true, true);

    private ChatValidator(){
    }

    /**
     *
     * @return ChatValidator
     */

    public static ChatValidator getInstance(){
        return INSTANCE;
    }

}
