/**
 * Home Page Test Output
 * Handles the test class and test methods descriptions
 * 
 * @author ASIH
 * 
 */

package appium.output;

import appium.AgentSkillTest;
import appium.ChatHangoutTest;
import com.util.log.OutputGenerator;
import com.util.log.OutputService;

import java.util.Arrays;
import java.util.List;


public class ChatHangoutTestOutput implements OutputService {

	
	public String testClassDesc(){
		StringBuilder desc = new StringBuilder();
		desc.append("Performs agent - skill variations tests").append("\n");
		return OutputGenerator.createGenericClassDesc(ChatHangoutTest.class, desc);
	}

	public static String endSessionByVisitorDesc(String testName){
		List<String> flowDesc = Arrays.asList(
				"Open app",
				"Click on engagement",
				"Verify ‘agent is ready for chat’ appears in the top of the page",
				"Send msg to agent",
				"Verify msg arrived to agent",
				"Click end session",
				"Verify chat has ended in agent",
				"Verify test app left chat page");
		return OutputGenerator.createGenericMethodDesc(testName, flowDesc);
	}

	public static String endChatByAgentDesc(String testName){
		List<String> flowDesc = Arrays.asList(
				"Open app",
				"Click on engagement",
				"Verify ‘agent is ready for chat’ appears in the top of the page",
				"Send msg to agent",
				"Verify msg arrived to agent",
				"Click terminate chat from agent",
				"Verify verification alert",
				"Verify test app left chat");
		return OutputGenerator.createGenericMethodDesc(testName, flowDesc);
	}
	
}
