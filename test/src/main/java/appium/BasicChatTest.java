package appium;

import appium.output.BasicChatTestOutput;
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
public class BasicChatTest extends BaseTest {

    private static final String TEST_DIR = "./src/main/resources/basic_chat_tests/";
    private static final String SITE_ID = "89961346";
    private static final StringBuilder CLASS_DESC = new StringBuilder("Performs basics chat activities");

    private static SettingsActivator settingsActivator = SettingsActivator.getInstance();
    private static InfoActivator infoActivator = InfoActivator.getInstance();

    private static ChatService chatService = ChatService.getInstance();
    private static List<Rep> agents = new ArrayList<Rep>();
    private static List<Rep> repsState = new ArrayList<Rep>();
    private static List<AgentState> agentStates = new ArrayList<AgentState>();
    private static AgentService service = AgentService.getInstance();
    private final String visitorMsg = "I need help";
    private final String agentMsg = "Me too";
    private final String visitorLongMsg = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    private final String agentLongMsg = "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";


    @BeforeClass
    public static void before() throws Exception {
        StaticRouter.before(
                AppiumDrivers.ANDROID,
                ConfigItemsRouter.ConfigType.LECreate,
                TEST_DIR,
                BasicChatTest.class,
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
				"Send msg to visitor",
				"Verify msg appears in chat"
    })

    public void sendBidirectionalMsgTest() throws Exception {
        getChatActivity().startChat(service, chatService, repsState, agentStates, agents.get(0));

        chatService.handleMsgFlow(service, visitorMsg, agentMsg, false, "", 2500);
        chatService.handleMsgFlow(service, "aaa", "bbb", false, "", 2500);

        getChatActivity().closeChat(service, chatService, agents.get(0));
    }

    @Test
    @TestSteps(steps={
                "Open app",
				"Click on engagement",
				"Verify ‘agent is ready for chat’ appears in the top of the page",
				"Send long msg to agent",
				"Verify long msg arrived to agent",
				"Send long msg to visitor",
				"Verify long msg appears in chat"
    })
    public void sendLongMessagesTest() throws Exception {
        getChatActivity().startChat(service, chatService, repsState, agentStates, agents.get(0));
        chatService.handleMsgFlow(service, visitorLongMsg, agentLongMsg, false, "", 2500);
        getChatActivity().closeChat(service, chatService, agents.get(0));
    }

    @Test
    @TestSteps(steps={
                "Open app",
				"Click on engagement",
				"Verify ‘agent is ready for chat’ appears in the top of the page",
				"Send msg with special characters msg to agent",
				"Verify with special characters arrived to agent",
				"Send with special characters to visitor",
				"Verify with special characters appears in chat"
    })
    public void sendMessagesWithSpecialCharactersTest() throws Exception {
        getChatActivity().startChat(service, chatService, repsState, agentStates, agents.get(0));
        StringBuilder asciiChars = new StringBuilder();
        for(int index = 32; index <= 125; index++){
            asciiChars.append(String.valueOf(Character.toChars(index)));
        }
        chatService.handleMsgFlow(service, asciiChars.toString(), "1234567890", false, "", 2500);
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
