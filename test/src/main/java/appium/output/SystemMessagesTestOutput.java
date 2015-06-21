/**
 * Home Page Test Output
 * Handles the test class and test methods descriptions
 * 
 * @author ASIH
 * 
 */

package appium.output;

import appium.SystemMessagesTest;
import com.util.log.OutputGenerator;
import com.util.log.OutputService;


public class SystemMessagesTestOutput implements OutputService {


	public  String testClassDesc(){
		StringBuilder desc = new StringBuilder();
		desc.append("Performs system messages verifications in chat page").append("\n");
		return OutputGenerator.createGenericClassDesc(SystemMessagesTest.class, desc);
	}

	@Override
	public String testMethodDesc(String testName) {
		return OutputGenerator.createGenericMethodDesc(testName);
	}
	
}
