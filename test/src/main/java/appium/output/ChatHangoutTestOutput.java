/**
 * Home Page Test Output
 * Handles the test class and test methods descriptions
 * 
 * @author ASIH
 * 
 */

package appium.output;

import appium.ChatHangoutTest;
import com.util.log.OutputGenerator;
import com.util.log.OutputService;


public class ChatHangoutTestOutput implements OutputService {

	
	public String testClassDesc(){
		StringBuilder desc = new StringBuilder();
		desc.append("Performs agent - skill variations tests").append("\n");
		return OutputGenerator.createGenericClassDesc(ChatHangoutTest.class, desc);
	}

	@Override
	public String testMethodDesc(String testName) {
		return OutputGenerator.createGenericMethodDesc(testName);
	}
	
}
