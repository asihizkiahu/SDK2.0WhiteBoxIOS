package com.service.activate.echo_test;

import com.pages.demo_app.Chat;
import com.pages.demo_app.ChooseCustomerType;
import com.pages.demo_app.PersonalInfo;
import com.pages.echo_test.InfraConstants;
import com.pages.echo_test.Menu;
import com.pages.echo_test.Settings;
import com.service.ReflectivePageFlow;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * Created by asih on 09/03/2015.
 */
public class SettingsActivator {

    private static final SettingsActivator INSTANCE = new SettingsActivator();
    private static final Logger logger = Logger.getLogger(SettingsActivator.class);

    private Menu menu = new Menu(true, true);
    private Settings settings = new Settings(true, true);

    private SettingsActivator(){

    }

    public static SettingsActivator getInstance(){
        return INSTANCE;
    }

    public void connectToAccount(String accountId) throws Exception {
        ReflectivePageFlow.invoke(Menu.class, menu, Arrays.asList(
                InfraConstants.VALIDATE_IN_PAGE_M_NAME,
                InfraConstants.PREPARE_ELEMENTS_M_NAME)
        );
        menu.getActivate().enterMenuSettings();
        feedAccountInfo(accountId);
    }

    public void feedAccountInfo(String accountId) throws Exception {
        ReflectivePageFlow.invoke(Settings.class, settings, Arrays.asList(
                InfraConstants.VALIDATE_IN_PAGE_M_NAME,
                InfraConstants.PREPARE_ELEMENTS_M_NAME)
        );
        settings.getActivate().feedAccountDetails(accountId);
    }


}
