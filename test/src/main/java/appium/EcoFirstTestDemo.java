package appium;

import com.config.base.ConfigItemsRouter;
import com.pages.demo_app.ChooseChatType;
import com.pages.demo_app.ChooseCustomerType;
import com.service.activate.demo_app.DemoActivator;
import com.service.activate.echo_test.ChatActivator;
import com.service.activate.echo_test.InfoActivator;
import com.service.activate.echo_test.SettingsActivator;
import com.service.validate.echo_test.ChatValidator;
import com.ui.service.drivers.AppiumDrivers;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;

/**
 * Created by asih on 18/03/2015.
 */
public class EcoFirstTestDemo extends BaseTest {

    private static final String TEST_DIR = "./src/main/resources/first_test_demo_eco/";
    private static final String SITE_ID = "89961346";
    private SettingsActivator settingsActivator = SettingsActivator.getInstance();
    private InfoActivator infoActivator = InfoActivator.getInstance();
    private ChatActivator chatActivator = ChatActivator.getInstance();
    private DemoActivator demoActivator = DemoActivator.getInstance();
    private ChatValidator chatValidator = ChatValidator.getInstance();


    @Before
    public void setUp() throws Exception {
        super.setUp(
                AppiumDrivers.ANDROID,
                ConfigItemsRouter.ConfigType.LECreate, TEST_DIR);
    }

    @Test
    public void apiDemo() throws Exception {

        settingsActivator.connectToAccount(SITE_ID);
        infoActivator.setSkill("aaaa", "mobile");
//        verify call in agent service
        demoActivator.enterChat();
        chatActivator.sendChatMsg("This is a test message");
        chatActivator.ensSession();

        infoActivator.setSkill("bbb", "sales");
        demoActivator.enterChat();
        chatValidator.validateNotInChat();
        // verify no call in agent service

    }

    @After
    public void tearDown() throws Exception {
        super.tearDown(DriverType.APPIUM);
    }

    @AfterClass
    public static void after() throws Exception {
//        BaseTest.after(DriverType.APPIUM);
    }
}
