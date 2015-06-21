/**
 * Home Page Test Output
 * Handles the test class and test methods descriptions
 * 
 * @author ASIH
 * 
 */

package appium.output;

import appium.BasicChatTest;
import com.util.log.OutputGenerator;
import com.util.log.OutputService;


public class BasicChatTestOutput implements OutputService {

	
	public String testClassDesc(){
		StringBuilder desc = new StringBuilder();
		desc.append("Performs basics chat activities").append("\n");
		return OutputGenerator.createGenericClassDesc(BasicChatTest.class, desc);
	}

	@Override
	public String testMethodDesc(String testName) {
		return OutputGenerator.createGenericMethodDesc(testName);
	}

	
}
