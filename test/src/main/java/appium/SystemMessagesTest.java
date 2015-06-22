package appium;

import com.agent.AgentService;
import com.config.base.ConfigItemsRouter;
import com.liveperson.AgentState;
import com.liveperson.Rep;
import com.service.activate.echo_test.InfoActivator;
import com.service.activate.echo_test.SettingsActivator;
import com.service.validate.echo_test.ChatService;
import com.ui.service.drivers.AppiumDrivers;
import com.util.log.TestSteps;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asih on 18/03/2015.
 *
 */
public class SystemMessagesTest extends BaseTest {

    private static final String TEST_DIR = "./src/main/resources/basic_chat_tests/";
    private static final String SITE_ID = "89961346";
    private static final StringBuilder CLASS_DESC = new StringBuilder("Performs system messages verifications in chat page");

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
        StaticRouter.before(
                AppiumDrivers.ANDROID,
                ConfigItemsRouter.ConfigType.LECreate,
                TEST_DIR,
                SystemMessagesTest.class,
                CLASS_DESC
        );
        settingsActivator.connectToAccount(SITE_ID);
        infoActivator.setSkill("aaa", "mobile");
        initAgentService();
    }

    @Before
    public void setUp() throws Exception {
        super.getRouter().setUp();
    }

    private static void initAgentService(){
        agents = service.setup(TEST_DIR);
        ChatActivity.initAgentLoginState(1, agents, repsState, agentStates);
    }

    @Test
    @TestSteps(steps={
                "Open app",
				"Set skill in app",
				"Click on engagement",
				"Verify ‘agent is ready for chat’ appears in the top of the page",
				"Send msg to agent",
                "Verify 'agent will be with you shortly'",
                "Verify 'you are now chatting with ... '",
                "Verify msg arrived to the agent",
				"Send msg to visitor",
                "Verify ‘agent is typing’ appears in the top of the page",
				"Verify msg arrived to the visitor",
				"End session",
    })
    public void systemMessagesTest() throws Exception {
        // agents are standing by
        getChatActivity().startChat(service, chatService, repsState, agentStates, agents.get(0));

        chatService.handleMsgFlow(service, visitorMsg, agentMsg, false, "", 1000);
//        chatService.handleMsgFlow(service, "aaa", "bbb", false, "", 2500);

        getChatActivity().closeChat(service, chatService, agents.get(0));
    }

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void after() throws Exception {
        StaticRouter.after(DriverType.APPIUM);
        AgentService.tearDown(agents);
    }


}
