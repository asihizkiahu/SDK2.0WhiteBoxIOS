
package appium;

import com.agent.AgentService;
import com.config.base.ConfigItemsRouter;
import com.liveperson.AgentState;
import com.liveperson.Rep;
import com.service.validate.echo_test.ChatService;
import com.ui.service.AppiumService;
import com.ui.service.drivers.AppiumDrivers;
import com.ui.service.drivers.SeleniumDrivers;
import com.util.genutil.GeneralUtils;
import com.util.log.OutputGenerator;
import com.util.properties.PropertiesHandlerImpl;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Rule;
import org.junit.rules.TestName;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import com.liveperson.AgentState;


import static com.liveperson.AgentState.Online;


public class BaseTest {

    private static final String ENV_PROP_FILE_PATH = "/environment/env.properties";
    private static final String LOG4J_PROP_FILE_PATH_KEY_VALUE = "Log4jPropFilePath";
    protected static final String OUTPUT_METHOD_POSTFIX = "Desc";

    private static StaticRouter staticRouter = new StaticRouter();
    private Router router = new Router();
    private static ChatActivity chatActivity = new ChatActivity();
    private static Logging logging = new Logging();

    private static Properties props;

    private static final Logger logger = Logger.getLogger(BaseTest.class);

    static {
        setProps(PropertiesHandlerImpl.getInstance().parseFromJar(ENV_PROP_FILE_PATH));

    }

    protected static AppiumService service = AppiumService.getInstance();


    @Rule
    public TestName name = new TestName();


    protected static class StaticRouter{

        protected static <T extends BaseTest> void before(
                AppiumDrivers driver,
                ConfigItemsRouter.ConfigType confType,
                String testPath,
                Class<T> testClass,
                StringBuilder desc)
                throws Exception {

            service.setDriver(driver, testPath);
            if(confType != null) {
//            ConfigItemsRouter.getInstance().routeAction(confType, testPath);
            }
            Logging.generateTestClassOutput(testClass, desc);
        }

        protected static void after(DriverType driver) throws Exception {
            if(driver == DriverType.SELENIUM){
                SeleniumDrivers.close();
            }else if(driver == DriverType.APPIUM){
                AppiumDrivers.close();
            }
        }

    }

    protected class Router{

        protected void setUp() throws Exception {
            logging.configureLog4J();
            getLogging().generateTestMethodOutput(name.getMethodName());
        }

        protected void tearDown(DriverType driver) throws Exception {
            if(driver == DriverType.SELENIUM){
                SeleniumDrivers.close();
            }else if(driver == DriverType.APPIUM){
                AppiumDrivers.close();
            }
        }
    }

    protected static class ChatActivity {

        protected void changeAgentState(List<AgentState> agentStates, List<Rep> agents, int agentLocation, AgentState stateToChange) {
            agentStates.set(agentLocation, stateToChange);
            agents.get(agentLocation).setAvailability(AgentState.Offline.name());
        }

        protected void changeAgentStateWithRange(List<AgentState> agentStates, List<Rep> agents, int fromLocation, int toLocation, AgentState stateToChange) {
            for(int i = fromLocation; i <= toLocation; i++){
                agentStates.set(i, stateToChange);
                agents.get(i).setAvailability("Away");
            }
        }

        protected void startChat(AgentService service, ChatService chatService, List<Rep> repsState, List<AgentState> agentStates, Rep agent) {
            try {
                chatService.startAndValidateChat(service, repsState, agentStates, agent);
            } catch (Exception e) {
                GeneralUtils.handleError("Error while starting chat", e);
            }
        }

        protected void closeChat(AgentService service, ChatService chatService, Rep agent) {
            try {
                chatService.closeChat(service, agent);
            } catch (Exception e) {
                GeneralUtils.handleError("Error while starting chat", e);
            }
        }

        protected static void initAgentLoginState(int numberOfAgents, List<Rep> agents, List<Rep> repsState, List<AgentState> agentStates){
            for(int i = 0; i < numberOfAgents; i++){
                repsState.add(agents.get(i));
                agentStates.add(Online);
            }
        }


    }

    protected static class Logging {

        private void configureLog4J() {
            java.net.URL url = getClass().getResource(
                    getProps().getProperty(
                            LOG4J_PROP_FILE_PATH_KEY_VALUE)
            );
            PropertyConfigurator.configure(url);
        }

        protected static <T> void generateTestClassOutput(Class<T> testClass, StringBuilder desc) {
            try {
                logger.info(OutputGenerator.createGenericClassDesc(testClass, desc));
            } catch (Throwable t) {
                GeneralUtils.handleError(
                        "activate method in class output for " +
                        "generate test description, in output class "
                        + testClass.getName() + "failed", t);
            }
        }

        protected void generateTestMethodOutput(String testName){
            try {
                logger.info(OutputGenerator.createGenericMethodDesc(testName));
            } catch (Throwable t) {
                GeneralUtils.handleError(
                        "Invoke method in class output for " +
                        "generate test description, in output method "
                         + "failed" + " in test " + testName, t);
            }
        }

    }

    public enum DriverType{
        SELENIUM, APPIUM;
    }

    public static Properties getProps() {
        return props;
    }

    public static void setProps(Properties props) {
        BaseTest.props = props;
    }

    protected Router getRouter() {
        return router;
    }

    protected static StaticRouter getStaticRouter() {
        return staticRouter;
    }

    protected static ChatActivity getChatActivity() {
        return chatActivity;
    }

    protected static Logging getLogging() {
        return logging;
    }

}
