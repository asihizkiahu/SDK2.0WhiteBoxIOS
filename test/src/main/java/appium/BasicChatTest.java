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
public class BasicChatTest extends BaseTest {

    private static final String TEST_DIR = "./src/main/resources/basic_chat_tests/";
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
    private final String visitorLongMsg = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    private final String agentLongMsg = "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";


    @BeforeClass
    public static void before() throws Exception {
        BaseTest.before(AppiumDrivers.ANDROID, ConfigItemsRouter.ConfigType.LECreate, TEST_DIR);
        settingsActivator.connectToAccount(SITE_ID);
        infoActivator.setSkill("aaaa", "mobile");
        initAgentService();
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    private static void initAgentService(){
        agents = service.setup(TEST_DIR);
        initAgentLoginState();
    }

    private static void initAgentLoginState(){
        repsState.add(agents.get(0));
        agentStates.add(Online);
    }

    @Test
    public void sendBidirectionalMsgTest() throws Exception {
        super.startChat(service, chatService, repsState, agentStates, agents.get(0));
        chatService.handleMessagesFlow(service, visitorMsg, agentMsg, false, "");
        chatService.handleMessagesFlow(service, "aaa", "bbb", false, "");
        super.closeChat(service, chatService, agents.get(0));
    }

    @Test
    public void sendLongMessagesTest() throws Exception {
        super.startChat(service, chatService, repsState, agentStates, agents.get(0));
        chatService.handleMessagesFlow(service, visitorLongMsg, agentLongMsg, false, "");
        super.closeChat(service, chatService, agents.get(0));
    }

    @Test
    public void sendMessagesWithSpecialCharactersTest() throws Exception {
        super.startChat(service, chatService, repsState, agentStates, agents.get(0));
        StringBuilder asciiChars = new StringBuilder();
        for(int index = 32; index <= 125; index++){
            asciiChars.append(String.valueOf(Character.toChars(index)));
        }
        chatService.handleMessagesFlow(service, asciiChars.toString(), "1234567890", false, "");
        super.closeChat(service, chatService, agents.get(0));
    }

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void after() throws Exception {
        BaseTest.after(DriverType.APPIUM);
        AgentService.tearDown(agents);
    }


}
