package com.service.validate.demo_app;

import com.pages.demo_app.ChooseApp;
import com.pages.demo_app.ChooseChatType;
import com.pages.demo_app.ChooseCustomerType;
import com.pages.demo_app.NativeAppSearch;
import org.apache.log4j.Logger;

/**
 * Created by asih on 08/03/2015.
 */
public class DemoValidator {

    private static final DemoValidator INSTANCE = new DemoValidator();
    private static final Logger logger = Logger.getLogger(DemoValidator.class);

    private ChooseCustomerType costumerType = new ChooseCustomerType(true, true);
    private NativeAppSearch appSearch = new NativeAppSearch(true, true);
    private ChooseChatType choose = new ChooseChatType(true, true);
    private ChooseApp chooseApp = new ChooseApp(true, true);

    private DemoValidator(){
    }

    public static DemoValidator getInstance(){
        return INSTANCE;
    }

//    public void validateChooseCostumerMsg(){
//        costumerType.getValidate().validateChooseCostumerMsg();
//    }
//
//    public void validateChooseChatMsg(){
//        choose.getValidate().validateChooseChatMsg();
//    }
//
//    public void validateChooseAppMsg(){
//        appSearch.getValidate().validateChooseAppMsg();
//    }
//
//    public void validateAppSearchHeader(){
//        chooseApp.getValidate().validateAppSearchHeader();
//    }



}
