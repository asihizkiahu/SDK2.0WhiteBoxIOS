/**
 * Home Page Test Output
 * Handles the test class and test methods descriptions
 * 
 * @author ASIH
 * 
 */

package appium.output;

import appium.AgentSkillTest;
import appium.BasicChatTest;
import com.util.log.OutputGenerator;

import java.util.Arrays;
import java.util.List;


public class BasicChatTestOutput {

	class Local{

	}



	/**
	 * Private constructor
	 *
	 */

	private BasicChatTestOutput(){


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
		desc.append("Performs basics chat activities").append("\n");
		return OutputGenerator.createGenericClassDesc(BasicChatTest.class, desc);
	}

	public static String sendBidirectionalMsgTestDesc(String testName){
		List<String> flowDesc = Arrays.asList(
				"Open app",
				"Click on engagement",
				"Verify ‘agent is ready for chat’ appears in the top of the page",
				"Send msg to agent",
				"Verify msg arrived to agent",
				"Send msg to visitor",
				"Verify msg appears in chat");
		StringBuilder desc = new StringBuilder();
		return OutputGenerator.createGenericMethodDesc(BasicChatTest.class.getName(), testName, desc, flowDesc);
	}

	public static String sendLongMessagesTestDesc(String testName){
		List<String> flowDesc = Arrays.asList(
				"Open app",
				"Click on engagement",
				"Verify ‘agent is ready for chat’ appears in the top of the page",
				"Send long msg to agent",
				"Verify long msg arrived to agent",
				"Send long msg to visitor",
				"Verify long msg appears in chat");
		StringBuilder desc = new StringBuilder();
		return OutputGenerator.createGenericMethodDesc(BasicChatTest.class.getName(), testName, desc, flowDesc);
	}

	public static String sendMessagesWithSpecialCharactersTestDesc(String testName){
		List<String> flowDesc = Arrays.asList(
				"Open app",
				"Click on engagement",
				"Verify ‘agent is ready for chat’ appears in the top of the page",
				"Send msg with special characters msg to agent",
				"Verify with special characters arrived to agent",
				"Send with special characters to visitor",
				"Verify with special characters appears in chat");
		StringBuilder desc = new StringBuilder();
		return OutputGenerator.createGenericMethodDesc(BasicChatTest.class.getName(), testName, desc, flowDesc);
	}
	
}