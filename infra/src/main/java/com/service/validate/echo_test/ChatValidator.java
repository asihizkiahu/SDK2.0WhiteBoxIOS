package com.service.validate.echo_test;

import com.pages.echo_test.Chat;
import com.pages.echo_test.Info;
import com.pages.echo_test.Menu;
import org.apache.log4j.Logger;
import org.junit.Assert;

/**
 * Created by asih on 09/03/2015.
 */


public class ChatValidator {

    private static final ChatValidator INSTANCE = new ChatValidator();
    private static final Logger logger = Logger.getLogger(ChatValidator.class);
    private Info info = new Info(true, true);
    private Menu menu = new Menu(true, true);

    private ChatValidator(){
    }

    /**
     *
     * @return ChatValidator
     */

    public static ChatValidator getInstance(){
        return INSTANCE;
    }

    public void verifySkillRemainLegual(String skill) throws Exception {
        menu.validateInPage();
        menu.prepareElements();
        menu.getActivate().enterMenuInfo();
        info.validateInPage();
        info.prepareElements();
        info.getValidate().verifySkillRemainLegual(skill);
    }
}
