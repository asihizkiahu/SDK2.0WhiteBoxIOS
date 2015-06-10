
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
import java.util.List;
import java.util.Properties;


public class BaseTest  {

    private static final String ENV_PROP_FILE_PATH = "/environment/env.properties";
    private static final String LOG4J_PROP_FILE_PATH_KEY_VALUE = "Log4jPropFilePath";
    private static final String OUTPUT_CLASS_DESC_M_NAME = "testClassDesc";
    private static Properties props;

    private static final Logger logger = Logger.getLogger(BaseTest.class);


    static {
        setProps(PropertiesHandlerImpl.getInstance().parseFromJar(ENV_PROP_FILE_PATH));
    }

    protected static AppiumService service = AppiumService.getInstance();


    public static void before(AppiumDrivers driver, ConfigItemsRouter.ConfigType confType, String testPath) throws Exception {
        service.setDriver(driver, testPath);
        if(confType != null) {
//            ConfigItemsRouter.getInstance().routeAction(confType, testPath);
        }
    }

    public static void before(AppiumDrivers driver, ConfigItemsRouter.ConfigType confType, String testPath, Class outputClazz) throws Exception {
        generateTestClassOutput(outputClazz);
        service.setDriver(driver, testPath);
        if(confType != null) {
//            ConfigItemsRouter.getInstance().routeAction(confType, testPath);
        }

    }

    protected void setUp() throws Exception {
        configureLog4J();

    }

    protected void changeAgentState(List<AgentState> agentStates, int agentLocation, AgentState stateToChange){
        agentStates.set(agentLocation, stateToChange);
    }

    protected void startChat(AgentService service, ChatService chatService, List<Rep> repsState, List<AgentState> agentStates, Rep agent){
        try {
            chatService.startAndValidateChat(service, repsState, agentStates, agent);
        } catch (Exception e) {
            GeneralUtils.handleError("Error while starting chat", e);
        }
    }

    protected void closeChat(AgentService service, ChatService chatService, Rep agent){
        try {
            chatService.closeChat(service, agent);
        } catch (Exception e) {
            GeneralUtils.handleError("Error while starting chat", e);
        }
    }

    protected void tearDown(DriverType driver) throws Exception {
        if(driver == DriverType.SELENIUM){
            SeleniumDrivers.close();
        }else if(driver == DriverType.APPIUM){
            AppiumDrivers.close();
        }
    }

    public static void after(DriverType driver) throws Exception {
        if(driver == DriverType.SELENIUM){
            SeleniumDrivers.close();
        }else if(driver == DriverType.APPIUM){
            AppiumDrivers.close();
        }
    }

    private void configureLog4J(){
        java.net.URL url = getClass().getResource(
                getProps().getProperty(
                        LOG4J_PROP_FILE_PATH_KEY_VALUE)
        );
        PropertyConfigurator.configure(url);
    }

    private static <T> void generateTestClassOutput(Class<T> outputClazz){ // logger.info(AgentSkillTestOutput.testClassDesc()); // move to base
        try {
            logger.info(outputClazz.getDeclaredMethod(OUTPUT_CLASS_DESC_M_NAME).invoke(null));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
//
//    private void generateTestMethodOutput(T outputClass, M outputTestMethod){
//        // reflect
//    }

    public enum DriverType{
        SELENIUM, APPIUM;
    }

    public static Properties getProps() {
        return props;
    }

    public static void setProps(Properties props) {
        BaseTest.props = props;
    }

}
