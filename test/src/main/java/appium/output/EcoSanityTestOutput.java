/**
 * Home Page Test Output
 * Handles the test class and test methods descriptions
 * 
 * @author ASIH
 * 
 */

package appium.output;

import appium.AgentSkillTest;
import appium.EcoSanityTest;
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
		return OutputGenerator.createGenericClassDesc(EcoSanityTest.class, desc);
	}

	public static String sanityTestDesc(String testName){
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
		StringBuilder desc = new StringBuilder();
		return OutputGenerator.createGenericMethodDesc(EcoSanityTest.class.getName(), testName, desc, flowDesc);
	}
	
}
