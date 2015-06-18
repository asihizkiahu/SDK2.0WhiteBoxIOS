/**
 * Home Page Test Output
 * Handles the test class and test methods descriptions
 * 
 * @author ASIH
 * 
 */

package appium.output;

import appium.AgentAvaiabilityTest;
import appium.AgentSkillTest;
import com.util.log.OutputGenerator;

import java.util.Arrays;
import java.util.List;


public class AgentAvaiabilityTestOutput {

	class Local{

	}



	/**
	 * Private constructor
	 *
	 */

	private AgentAvaiabilityTestOutput(){


	}


	/**
	 * Test Class Desc
	 * 
	 * Contains the output for the test class description

	 * @version 0.0.1-SNAPSHOT
	 * @return Output value
	 */

	
	public static String testClassDesc(){
		StringBuilder desc = new StringBuilder();
		desc.append("Performs agent - availability variations tests").append("\n");
		return OutputGenerator.createGenericClassDesc(AgentAvaiabilityTest.class, desc);
	}

	public static String AgentAvailabilityOnlineOfflineTestDesc(String testName){
		List<String> flowDesc = Arrays.asList(
				"Open app",
				"Set skill in app",
				"Click on engagement",
				"Verify ‘agent is ready for chat’ appears in the top of the page (the online agent)",
				"Send msg to agent",
				"Verify msg arrived to the online agent",
				"Send msg from the online agent and verify in chat the correct agent appears in chat.",
				"Set availability to offline",
				"See chat button disabled");
		return OutputGenerator.createGenericMethodDesc(testName, flowDesc);
	}
	
}
