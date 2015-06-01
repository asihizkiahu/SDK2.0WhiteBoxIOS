package appium;

import com.agent.AgentService;
import com.config.base.ConfigItemsRouter;
import com.liveperson.AgentState;
import com.liveperson.Rep;
import com.service.activate.demo_app.DemoActivator;
import com.service.activate.echo_test.InfoActivator;
import com.service.activate.echo_test.SettingsActivator;
import com.service.validate.echo_test.ChatService;
import com.ui.service.AppiumService;
import com.ui.service.drivers.AppiumDrivers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.liveperson.AgentState.Online;

/**
 * Created by asih on 18/03/2015.
 *
 */
public class BasicChatTest extends BaseTest {

    private final String TEST_DIR = "./src/main/resources/eco_sanity_test/";
    private final String SITE_ID = "89961346";
    private SettingsActivator settingsActivator = SettingsActivator.getInstance();
    private InfoActivator infoActivator = InfoActivator.getInstance();

    private ChatService chatService = ChatService.getInstance();
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
        agents = service.setup(TEST_DIR);
        initAgentLoginState();
    }

    private void initAgentLoginState(){
        repsState.add(agents.get(0));
        agentStates.add(Online);
    }

    @Test
    public void sendBidirectionalMsgTest() throws Exception {
        settingsActivator.connectToAccount(SITE_ID);
        infoActivator.setSkill("aaaa", "mobile");

        chatService.startAndValidateChat(service, repsState, agentStates);
        chatService.activateAndValidateTwoWayMsg(service, visitorMsg, agentMsg);
        chatService.activateAndValidateTwoWayMsg(service, "aaa", "bbb");
        chatService.closeChat(service, agents.get(1));

        infoActivator.setSkill("Asi Hiz", "tech support");
        chatService.verifySkillRemainLegual(SITE_ID + "\\mobile");
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown(DriverType.APPIUM);
        service.tearDown(agents);
    }


}
