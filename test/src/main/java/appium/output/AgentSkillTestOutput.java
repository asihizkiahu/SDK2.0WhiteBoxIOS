/**
 * Home Page Test Output
 * Handles the test class and test methods descriptions
 * 
 * @author ASIH
 * 
 */

package appium.output;

import java.util.Arrays;
import java.util.List;

import appium.AgentSkillTest;
import com.util.log.OutputGenerator;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


public class AgentSkillTestOutput {

	class Local{

	}



	/**
	 * Private constructor
	 * 
	 */

	private AgentSkillTestOutput(){


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
		desc.append("Performs agent - skill variations tests").append("\n");
		return OutputGenerator.createGenericClassDesc(AgentSkillTest.class, desc);
	}

	public static String chatWithAgentBySkillTestDesc(String testName){
		List<String> flowDesc = Arrays.asList(
				"Open app",
				"Set skill A in app",
				"Click on engagement",
				"Verify ‘agent is ready for chat’ appears in the top of the page (the skill A agent)",
				"Send msg to agent",
				"Verify msg arrived to the skill A agent",
				"Send msg from agent and verify in chat the correct agent A appears",
				"Set skill B in app",
				"Click on engagement",
				"Verify ‘agent is ready for chat’ appears in the top of the page (the skill B agent)",
				"Send msg to agent",
				"Verify msg arrived to the skill B agent",
				"Send msg from agent and verify in chat the correct agent B appears");
		return OutputGenerator.createGenericMethodDesc(testName, flowDesc);
	}
	
}
