
package appium;


import com.config.base.ConfigItemsRouter;
import com.ui.service.AppiumService;
import com.ui.service.drivers.AppiumDrivers;
import com.ui.service.drivers.SeleniumDrivers;
import com.util.properties.PropertiesHandlerImpl;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.Properties;


public class BaseTest  {

    private static final String ENV_PROP_FILE_PATH = "/environment/env.properties";
    private static final String LOG4J_PROP_FILE_PATH_KEY_VALUE = "Log4jPropFilePath";
    private static Properties props;

    static {
        setProps(PropertiesHandlerImpl.getInstance().parseFromJar(ENV_PROP_FILE_PATH));
    }

    protected AppiumService service = AppiumService.getInstance();


    public void setUp(AppiumDrivers driver, ConfigItemsRouter.ConfigType confType, String testPath) throws Exception {
        configureLog4J();
        service.setDriver(driver, testPath);
        if(confType != null) {
//            ConfigItemsRouter.getInstance().routeAction(confType, testPath);
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
        java.net.URL url = getClass().getResource(getProps().getProperty(LOG4J_PROP_FILE_PATH_KEY_VALUE));
        PropertyConfigurator.configure(url);
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

}
