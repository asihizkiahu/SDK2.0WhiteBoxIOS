package appium;

import appium.output.ChatHangoutTestOutput;
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
public class ChatHangoutTest extends BaseTest {

    private static final String TEST_DIR = "./src/main/resources/basic_chat_tests/";
    private static final String SITE_ID = "89961346";
    private static final StringBuilder CLASS_DESC = new StringBuilder("Performs bidirectional chat hang outs");

    private static SettingsActivator settingsActivator = SettingsActivator.getInstance();
    private static InfoActivator infoActivator = InfoActivator.getInstance();

    private static ChatService chatService = ChatService.getInstance();
    private static List<Rep> agents = new ArrayList<Rep>();
    private static List<Rep> repsState = new ArrayList<Rep>();
    private static List<AgentState> agentStates = new ArrayList<AgentState>();
    private static AgentService service = AgentService.getInstance();

    @BeforeClass
    public static void before() throws Exception {
        StaticRouter.before(
                AppiumDrivers.ANDROID,
                ConfigItemsRouter.ConfigType.LECreate,
                TEST_DIR,
                ChatHangoutTest.class,
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
				"Click on engagement",
				"Verify ‘agent is ready for chat’ appears in the top of the page",
				"Send msg to agent",
				"Verify msg arrived to agent",
				"Click end session",
				"Verify chat has ended in agent",
				"Verify test app left chat page"
    })

    public void endSessionByVisitor() throws Exception {
        getChatActivity().startChat(service, chatService, repsState, agentStates, agents.get(0));
        chatService.handleMsgFlow(service, "a", "a", false, "", 2500);
        chatService.ensSession(service, agents.get(0));
        chatService.verifyIsInEngagementPage();
    }

    @Test
    @TestSteps(steps={
                "Open app",
				"Click on engagement",
				"Verify ‘agent is ready for chat’ appears in the top of the page",
				"Send msg to agent",
				"Verify msg arrived to agent",
				"Click terminate chat from agent",
				"Verify verification alert",
				"Verify test app left chat"
    })
    public void endChatByAgent() throws Exception {
        getChatActivity().startChat(service, chatService, repsState, agentStates, agents.get(0));
        chatService.handleMsgFlow(service, "a", "a", false, "", 2500);
        service.endChat(agents.get(0));
        chatService.setIsChatStarted(false);
        chatService.dismissSession();
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
