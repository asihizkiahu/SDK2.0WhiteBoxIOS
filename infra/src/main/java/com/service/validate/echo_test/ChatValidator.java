package com.service.validate.echo_test;

import com.pages.echo_test.Chat;
import com.pages.echo_test.Info;
import com.pages.echo_test.InfraConstants;
import com.pages.echo_test.Menu;
import com.service.ReflectivePageFlow;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.Arrays;

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
        ReflectivePageFlow.invoke(Menu.class, menu, Arrays.asList(InfraConstants.VALIDATE_IN_PAGE_M_NAME, InfraConstants.PREPARE_ELEMENTS_M_NAME));
        menu.getActivate().enterMenuInfo();
        ReflectivePageFlow.invoke(Info.class, info, Arrays.asList(InfraConstants.VALIDATE_IN_PAGE_M_NAME, InfraConstants.PREPARE_ELEMENTS_M_NAME));
        info.getValidate().verifySkillRemainLegual(skill);
    }
}
