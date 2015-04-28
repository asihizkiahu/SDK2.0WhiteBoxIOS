package com.service.activate.echo_test;

import com.pages.echo_test.Info;
import com.pages.echo_test.Menu;
import com.pages.echo_test.Settings;
import org.apache.log4j.Logger;

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
        menu.validateInPage();
        menu.prepareElements();
        menu.getActivate().enterMenuInfo();
        feedAccountInfo(accountName, skill);
    }

    public void feedAccountInfo(String accountName, String skill) throws Exception {
        info.validateInPage();
        info.prepareElements();
        info.getActivate().feedAccountDetails(accountName, skill);
    }


}
