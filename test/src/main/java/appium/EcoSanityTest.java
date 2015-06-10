package appium;

import com.agent.AgentService;
import com.config.base.ConfigItemsRouter;
import com.liveperson.AgentState;
import com.liveperson.Rep;
import com.service.activate.echo_test.InfoActivator;
import com.service.activate.echo_test.SettingsActivator;
import com.service.validate.echo_test.ChatService;
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

    private static final String TEST_DIR = "./src/main/resources/eco_sanity_test/";
    private static final String SITE_ID = "89961346";
    private static SettingsActivator settingsActivator = SettingsActivator.getInstance();
    private static InfoActivator infoActivator = InfoActivator.getInstance();

    private static ChatService chatService = ChatService.getInstance();
    private static List<Rep> agents = new ArrayList<Rep>();
    private static List<Rep> repsState = new ArrayList<Rep>();
    private static List<AgentState> agentStates = new ArrayList<AgentState>();
    private static AgentService service = AgentService.getInstance();
    private final String visitorMsg = "I need help";
    private final String agentMsg = "Me too";

    @BeforeClass
    public static void before() throws Exception {
        StaticRouter.before(AppiumDrivers.ANDROID, ConfigItemsRouter.ConfigType.LECreate, TEST_DIR);
        settingsActivator.connectToAccount(SITE_ID);
        infoActivator.setSkill("aaaa", "mobile");
        initAgentService();
    }

    @Before
    public void setUp() throws Exception {
        super.getRouter().setUp();
        chatService.startAndValidateChat(service, repsState, agentStates, agents.get(1));
    }

    private static void initAgentService(){
        agents = service.setup(TEST_DIR);
        initAgentLoginState();
    }

    private static void initAgentLoginState(){
        repsState.add(agents.get(0));
        repsState.add(agents.get(1));
        agentStates.add(Online);
        agentStates.add(Online);
    }

    @Test
    public void sanityTest() throws Exception {
        chatService.handleMessagesFlow(service, visitorMsg, agentMsg, false, "");

    }

    @After
    public void tearDown() throws Exception {
        chatService.closeChat(service, agents.get(1));
    }

    @AfterClass
    public static void after() throws Exception {
        Class c = Class.forName("l");

        c.getAnnotation(Class.forName("Test"));
        try {
            infoActivator.setSkill("Asi Hiz", "tech support");
            chatService.verifySkillRemainLegual(SITE_ID + "\\mobile");
        }
        finally {
            StaticRouter.after(DriverType.APPIUM);
            AgentService.tearDown(agents);
            agentStates.clear();
            agents.clear();
            repsState.clear();
        }
    }


}
