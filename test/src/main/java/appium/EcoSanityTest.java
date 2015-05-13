package appium;

import com.agent.AgentService;
import com.config.base.ConfigItemsRouter;
import com.liveperson.AgentState;
import com.liveperson.Rep;
import com.service.activate.demo_app.DemoActivator;
import com.service.activate.echo_test.ChatActivator;
import com.service.activate.echo_test.InfoActivator;
import com.service.activate.echo_test.SettingsActivator;
import com.service.validate.echo_test.ChatValidator;
import com.ui.service.AppiumService;
import com.ui.service.drivers.AppiumDrivers;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static com.liveperson.AgentState.Online;

/**
 * Created by asih on 18/03/2015.
 *
 */
public class EcoSanityTest extends BaseTest {

    private AppiumService appiumService = AppiumService.getInstance();
    private final String TEST_DIR = "./src/main/resources/eco_sanity_test/";
    private final String SITE_ID = "89961346";
    private SettingsActivator settingsActivator = SettingsActivator.getInstance();
    private InfoActivator infoActivator = InfoActivator.getInstance();
    private ChatActivator chatActivator = ChatActivator.getInstance();
    private DemoActivator demoActivator = DemoActivator.getInstance();
    private ChatValidator chatValidator = ChatValidator.getInstance();
    private List<Rep> agents = new ArrayList<Rep>();
    private List<Rep> repsState = new ArrayList<Rep>();
    private List<AgentState> agentStates = new ArrayList<AgentState>();
    private AgentService service = AgentService.getInstance();
    private final String visitorMsg = "I need help";
    private final String agentMsg = "Me too";


    @Before
    public void setUp() throws Exception {
        super.setUp(AppiumDrivers.ANDROID, ConfigItemsRouter.ConfigType.LECreate, TEST_DIR);
        initAgentService();
    }

    private void initAgentService(){
        service.setup(TEST_DIR, agents);
        initLoginState();
    }

    private void initLoginState(){
        repsState.add(agents.get(0));
        repsState.add(agents.get(1));
        agentStates.add(Online);
        agentStates.add(Online);
    }

    @Test
    public void apiDemo() throws Exception {

        settingsActivator.connectToAccount(SITE_ID);
        infoActivator.setSkill("aaaa", "mobile");

        prepareAgentForChat();
        demoActivator.enterChat();

        chatActivator.sendChatMsg(visitorMsg);
        activateAgentConversation();

        chatActivator.ensSession();
        service.endChat(agents.get(1));

        infoActivator.setSkill("Asi Hiz", "tech support");
        chatValidator.verifySkillRemainLegual(SITE_ID + "\\mobile");

    }

    private void prepareAgentForChat(){
        service.logInAndSetState(repsState, agentStates);
    }

    private void activateAgentConversation(){
        Rep mobileAgent = service.getFirstMobileAgent();

        Assert.assertNotNull("There is no mobile agent", mobileAgent);
        Assert.assertTrue(
                "Ringing count is not as expected",
                service.isRingingCountAsExpected(mobileAgent, 1, 5000)
        );

        Assert.assertTrue(
                "Start chat encountered a problem",
                service.startChat(mobileAgent)
        );

        appiumService.implicitWait(3000);

        Assert.assertTrue(
                "Chat last line " + visitorMsg + "is not as expected",
                service.verifyLatestChatLines(mobileAgent, visitorMsg)
        );

        service.addChatLines(agents.get(1), agentMsg);

        Assert.assertTrue(
                "Chat last line " + agentMsg + "is not as expected ",
                service.verifyLatestChatLines(mobileAgent, agentMsg)
        );
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown(DriverType.APPIUM);
        service.tearDown(agents);
    }

//    @AfterClass
//    public static void after() throws Exception {
//        BaseTest.after(DriverType.APPIUM);
//    }
}
