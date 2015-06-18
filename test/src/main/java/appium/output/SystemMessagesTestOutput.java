/**
 * Home Page Test Output
 * Handles the test class and test methods descriptions
 * 
 * @author ASIH
 * 
 */

package appium.output;

import appium.EcoSanityTest;
import appium.SystemMessagesTest;
import com.util.log.OutputGenerator;
import com.util.log.OutputService;

import java.util.Arrays;
import java.util.List;


public class SystemMessagesTestOutput implements OutputService {


	public  String testClassDesc(){
		StringBuilder desc = new StringBuilder();
		desc.append("Performs system messages verifications in chat page").append("\n");
		return OutputGenerator.createGenericClassDesc(SystemMessagesTest.class, desc);
	}

	public static String systemMessagesTestDesc(String testName){
		List<String> flowDesc = Arrays.asList(
				"Open app",
				"Set skill in app",
				"Click on engagement",
				"Verify ‘agent is ready for chat’ appears in the top of the page",
				"Send msg to agent",
				"Verify msg arrived to the agent",
				"Send msg to visitor",
				"Verify msg arrived to the visitor",
				"End session",
				"Set illegal skill",
				"Verify skill remains the legal skill");
		return OutputGenerator.createGenericMethodDesc(testName, flowDesc);
	}
	
}
