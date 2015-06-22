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
import org.apache.log4j.Logger;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asih on 18/03/2015.
 *
 */
public class AgentAvaiabilityTest extends BaseTest {

    private static final String TEST_DIR = "./src/main/resources/agent_availability_tests/";
    private static final String SITE_ID = "89961346";
    private static final StringBuilder CLASS_DESC = new StringBuilder("Performs agent - availability variations tests");

    private static SettingsActivator settingsActivator = SettingsActivator.getInstance();
    private static InfoActivator infoActivator = InfoActivator.getInstance();

    private static ChatService chatService = ChatService.getInstance();
    private static List<Rep> agents;
    private static List<Rep> repsState = new ArrayList<Rep>();
    private static List<AgentState> agentStates = new ArrayList<AgentState>();
    private static AgentService service = AgentService.getInstance();
    private static final Logger logger = Logger.getLogger(AgentSkillTest.class);


    @BeforeClass
    public static void before() throws Exception {
        StaticRouter.before(
                AppiumDrivers.ANDROID,
                ConfigItemsRouter.ConfigType.LECreate,
                TEST_DIR,
                AgentAvaiabilityTest.class,
                CLASS_DESC
        );
        settingsActivator.connectToAccount(SITE_ID);
        infoActivator.setSkill("", Constants.SKILL);
        initAgentService();
    }

    @Before
    public void setUp() throws Exception {
        super.getRouter().setUp();
    }

    private static void initAgentService(){
        agents = service.setup(TEST_DIR);
        ChatActivity.initAgentLoginState(2, agents, repsState, agentStates);
    }

    @Test
    @TestSteps(steps={
                "Open app",
				"Set skill in app",
				"Click on engagement",
				"Verify ‘agent is ready for chat’ appears in the top of the page (the online agent)",
				"Send msg to agent",
				"Verify msg arrived to the online agent",
				"Send msg from the online agent and verify in chat the correct agent appears in chat.",
				"Set availability to offline",
				"See chat button disabled"
    })
    public void AgentAvailabilityOnlineOfflineTest() throws Exception {
        chatWithAgentBySkillTest(AgentSkillData.AGENT_B);
        getChatActivity().changeAgentState(
                agentStates,
                agents,
                AgentSkillData.AGENT_B.agentLocation,
                AgentState.Offline
        );
        chatWithAgentBySkillTest(AgentSkillData.AGENT_B);
    }

    public void chatWithAgentBySkillTest(AgentSkillData agentSkillData) {
        try {
            chatService.startAndValidateChat(service, repsState, agentStates, agents.get(agentSkillData.agentLocation));
            chatService.handleMsgFlow(service, "aaa", agentSkillData.msg, true, agentSkillData.nickName, 5000, false);
            if(agentSkillData == AgentSkillData.AGENT_B) {
                chatService.closeChat(service, agents.get(agentSkillData.agentLocation));
            }else{
                Assert.assertFalse("", chatService.isSystemMsgAppearInTop("Agents are standing by...", 5000));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private enum AgentSkillData{

        AGENT_A (Constants.AGENT_A_NICK_NAME, Constants.AGENT_A_MSG, 0),
        AGENT_B (Constants.AGENT_B_NICK_NAME, Constants.AGENT_B_MSG, 1);

        private String nickName;
        private String msg;
        private int agentLocation;

        AgentSkillData(String nickName, String msg, int agentLocation){
            this.nickName = nickName;
            this.msg = msg;
            this.agentLocation = agentLocation;
        }
    }

    private static class Constants{

        private static final String SKILL = "mobile";
        private static final String AGENT_A_NICK_NAME = "Asi Hiz";
        private static final String AGENT_B_NICK_NAME = "Asi the king of agents";
        private static final String AGENT_A_MSG = "I am agent A, my skill is mobile";
        private static final String AGENT_B_MSG = "I am agent B, my skill is mobile";
    }

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void after() throws Exception {
        StaticRouter.after(DriverType.APPIUM);
        AgentService.tearDown(agents);
        agentStates.clear();
        agents.clear();
        repsState.clear();

    }


}