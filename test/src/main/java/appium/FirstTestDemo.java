package appium;

import com.config.base.ConfigItemsRouter;
import com.pages.demo_app.ChooseChatType;
import com.pages.demo_app.ChooseCustomerType;
import com.service.activate.demo_app.ChatActivator;
import com.service.activate.demo_app.DemoActivator;
import com.ui.service.drivers.AppiumDrivers;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;

/**
 * Created by asih on 18/03/2015.
 */
public class FirstTestDemo extends BaseTest {

    private static final String TEST_DIR = "./src/main/resources/first_test_demo/";
    private static final String SITE_ID = "71807640";
    private DemoActivator demo = DemoActivator.getInstance();
    private ChatActivator chat = ChatActivator.getInstance();
    private static final Logger logger = Logger.getLogger(FirstTestDemo.class);


    @Before
    public void setUp() throws Exception {
        super.setUp(
                AppiumDrivers.ANDROID,
                ConfigItemsRouter.ConfigType.LECreate, TEST_DIR);
    }

    @Test
    public void apiDemo() throws Exception {
        demo.chooseCostumerType(ChooseCustomerType.CustomerType.EXISTING_COSTUMER);
        demo.feedAccountDetails(SITE_ID, "asih@liveperson.com", "Carish74");
        super.service.rotate(ScreenOrientation.LANDSCAPE);
        demo.chooseChatType(ChooseChatType.ChatType.NATIVE);
        demo.findApp("Google");
        demo.startDemo("Google");
        demo.enterChat();
        chat.feedPersonalInfo("Asid", "asdih@liveperson.com", "0544636220", ChooseCustomerType.CustomerType.EXISTING_COSTUMER);
        chat.sendChatMsg("Asi is the king");
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
