/**
 * Home Page Test Output
 * Handles the test class and test methods descriptions
 * 
 * @author ASIH
 * 
 */

package appium.output;

import appium.AgentAvaiabilityTest;
import com.util.log.OutputGenerator;
import com.util.log.OutputService;


public class AgentAvaiabilityTestOutput implements OutputService {

	@Override
	public String testClassDesc(){
		StringBuilder desc = new StringBuilder();
		desc.append("Performs agent - availability variations tests").append("\n");
		return OutputGenerator.createGenericClassDesc(AgentAvaiabilityTest.class, desc);
	}

	@Override
	public String testMethodDesc(String testName) {
		return OutputGenerator.createGenericMethodDesc(testName);
	}

	
}
