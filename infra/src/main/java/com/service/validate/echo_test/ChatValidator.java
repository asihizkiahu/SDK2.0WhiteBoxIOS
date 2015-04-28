package com.service.validate.echo_test;

import com.pages.echo_test.Chat;
import org.apache.log4j.Logger;
import org.junit.Assert;

/**
 * Created by asih on 09/03/2015.
 */


public class ChatValidator {

    private static final ChatValidator INSTANCE = new ChatValidator();
    private static final Logger logger = Logger.getLogger(ChatValidator.class);

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

    public void validateNotInChat() throws Exception {
        Assert.assertFalse("Chat is active when not expected", chatPage.validateInPage());;
    }

}
