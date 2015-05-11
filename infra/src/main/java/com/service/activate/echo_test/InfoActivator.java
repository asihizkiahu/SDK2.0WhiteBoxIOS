package com.service.activate.echo_test;

import com.pages.echo_test.Info;
import com.pages.echo_test.InfraConstants;
import com.pages.echo_test.Menu;
import com.pages.echo_test.Settings;
import com.service.ReflectivePageFlow;
import org.apache.log4j.Logger;

import java.util.Arrays;

/**
 * Created by asih on 09/03/2015.
 */
public class InfoActivator {

    private static final InfoActivator INSTANCE = new InfoActivator();
    private static final Logger logger = Logger.getLogger(InfoActivator.class);

    private Menu menu = new Menu(true, true);
    private Info info = new Info(true, true);

    private InfoActivator(){

    }

    public static InfoActivator getInstance(){
        return INSTANCE;
    }

    public void setSkill(String accountName, String skill) throws Exception {
        ReflectivePageFlow.invoke(Menu.class, menu, Arrays.asList(
                InfraConstants.VALIDATE_IN_PAGE_M_NAME,
                InfraConstants.PREPARE_ELEMENTS_M_NAME)
        );
        menu.getActivate().enterMenuInfo();
        feedAccountInfo(accountName, skill);
    }

    public void feedAccountInfo(String accountName, String skill) throws Exception {
        ReflectivePageFlow.invoke(Info.class, info, Arrays.asList(
                InfraConstants.VALIDATE_IN_PAGE_M_NAME,
                InfraConstants.PREPARE_ELEMENTS_M_NAME)
        );
        info.getActivate().feedAccountDetails(accountName, skill);
    }


}
