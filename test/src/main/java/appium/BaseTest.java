
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
import com.util.properties.PropertiesHandlerImpl;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;


public class BaseTest  {

    private static final String ENV_PROP_FILE_PATH = "/environment/env.properties";
    private static final String LOG4J_PROP_FILE_PATH_KEY_VALUE = "Log4jPropFilePath";
    private static final String OUTPUT_CLASS_DESC_M_NAME = "testClassDesc";
    protected static final String OUTPUT_METHOD_POSTFIX = "Desc";

    private static StaticRouter staticRouter = new StaticRouter();
    private Router router = new Router();
    private ChatActivity chatActivity = new ChatActivity();
    private static Logging logging = new Logging();

    private static Properties props;

    private static final Logger logger = Logger.getLogger(BaseTest.class);


    static {
        setProps(PropertiesHandlerImpl.getInstance().parseFromJar(ENV_PROP_FILE_PATH));

    }

    protected static AppiumService service = AppiumService.getInstance();
    protected static Class<?> outputClazz;


    protected static class StaticRouter{

        protected static void before(AppiumDrivers driver, ConfigItemsRouter.ConfigType confType, String testPath) throws Exception {
            service.setDriver(driver, testPath);
            if(confType != null) {
//            ConfigItemsRouter.getInstance().routeAction(confType, testPath);
            }
        }

        protected static void before(AppiumDrivers driver, ConfigItemsRouter.ConfigType confType, String testPath, Class outputClazz) throws Exception {
            service.setDriver(driver, testPath);
            if(confType != null) {
//            ConfigItemsRouter.getInstance().routeAction(confType, testPath);
            }
            Logging.generateTestClassOutput(outputClazz);
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

        }

        protected void tearDown(DriverType driver) throws Exception {
            if(driver == DriverType.SELENIUM){
                SeleniumDrivers.close();
            }else if(driver == DriverType.APPIUM){
                AppiumDrivers.close();
            }
        }
    }

    protected class ChatActivity {

        protected void changeAgentState(List<AgentState> agentStates, int agentLocation, AgentState stateToChange) {
            agentStates.set(agentLocation, stateToChange);
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

    }

    protected static class Logging {

        private void configureLog4J() {
            java.net.URL url = getClass().getResource(
                    getProps().getProperty(
                            LOG4J_PROP_FILE_PATH_KEY_VALUE)
            );
            PropertyConfigurator.configure(url);
        }

        protected static <T> void generateTestClassOutput(Class<T> _outputClazz) {
            try {
                outputClazz = _outputClazz;
                logger.info(outputClazz.getDeclaredMethod(OUTPUT_CLASS_DESC_M_NAME).invoke(null));
            } catch (Throwable t) {
                GeneralUtils.handleError(
                        "Invoke method in class output for generate test description, in output class "
                                + outputClazz.getName() + "failed", t);
            }
        }

        protected void generateTestMethodOutput(String testName){
            try {
                Method outputMethodName = outputClazz.getMethod(testName + OUTPUT_METHOD_POSTFIX, new Class[]{String.class});
                logger.info(outputMethodName.invoke(outputClazz, testName));
            } catch (Throwable t) {
                GeneralUtils.handleError(
                        "Invoke method in class output for generate test description, in output class "
                                + outputClazz.getName() + "failed", t);
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

    protected ChatActivity getChatActivity() {
        return chatActivity;
    }

    protected static Logging getLogging() {
        return logging;
    }

}
