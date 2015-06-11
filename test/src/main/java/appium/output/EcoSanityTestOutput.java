/**
 * Home Page Test Output
 * Handles the test class and test methods descriptions
 * 
 * @author ASIH
 * 
 */

package appium.output;

import appium.AgentSkillTest;
import com.util.log.OutputGenerator;

import java.util.Arrays;
import java.util.List;


public class EcoSanityTestOutput {

	class Local{

	}



	/**
	 * Private constructor
	 *
	 */

	private EcoSanityTestOutput(){


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
		StringBuilder desc = new StringBuilder();
		return OutputGenerator.createGenericMethodDesc(AgentSkillTest.class.getName(), testName, desc, flowDesc);
	}
	
}
