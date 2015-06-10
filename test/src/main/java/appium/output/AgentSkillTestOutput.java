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


	public static String verifyHowDoesFeexWorkLinkDesc(){ 
		List<String> flowDesc = Arrays.asList("Go to home page", 
				"Click How To Get Reports Link",
				"Verify URL output", 
				"Verify text in page output");
		StringBuilder desc = new StringBuilder();
		return OutputGenerator.createGenericMethodDesc("Home Page Test", "Verify How Does Feex Work Link", desc, flowDesc);
	}
	
	
	public static String verifyAboutFeexLinkDesc(){ 
		List<String> flowDesc = Arrays.asList("Go to home page", 
				"Click About Feex Link",
				"Verify URL output", 
				"Verify text in page output");
		StringBuilder desc = new StringBuilder();
		return OutputGenerator.createGenericMethodDesc("Home Page Test", "Verify About Feex Link", desc, flowDesc);
	}

	
	public static String verifyFAQLinkDesc(){
		List<String> flowDesc = Arrays.asList("Go to home page", 
				"Click FAQ Link",
				"Verify URL output", 
				"Verify text in page output");
		StringBuilder desc = new StringBuilder();
		return OutputGenerator.createGenericMethodDesc("Home Page Test", "Verify FAQ Link", desc, flowDesc);
	}


	public static String verifyBlogLinkDesc(){
		List<String> flowDesc = Arrays.asList("Go to home page", 
				"Click Blog Link",
				"Verify URL output", 
				"Verify text in page output");
		StringBuilder desc = new StringBuilder();
		return OutputGenerator.createGenericMethodDesc("Home Page Test", "Verify Blog Link ", desc, flowDesc);
	}
		

	public static String verifySecurityDesc(){
		List<String> flowDesc = Arrays.asList("Go to home page", 
				"Click Security Link",
				"Verify URL output", 
				"Verify text in page output");
		StringBuilder desc = new StringBuilder();
		desc.append("The test downloads Statement And Verify.").append("\n");
		return OutputGenerator.createGenericMethodDesc("Home Page Test", "Verify Security Link", desc, flowDesc);
	}


	public static String verifyContactUsLinkDesc(){
		List<String> flowDesc = Arrays.asList("Go to home page", 
				"Click Contact Us Link",
				"Verify URL output", 
				"Verify text in page output");
		StringBuilder desc = new StringBuilder();
		return OutputGenerator.createGenericMethodDesc("Home Page Test", "Verify Contact Us Link", desc, flowDesc);
	}


	public static String verifyPressLinkDesc(){
		List<String> flowDesc = Arrays.asList("Go to home page", 
				"Click Press Link",
				"Verify URL output", 
				"Verify text in page output");
		StringBuilder desc = new StringBuilder();
		return OutputGenerator.createGenericMethodDesc("Home Page Test", "Verify Press Link", desc, flowDesc);
	}
	

	public static String verifyPrivacyPolicyinksDesc(){
		List<String> flowDesc = Arrays.asList("Go to home page", 
				"Click Privacy Policy Link",
				"Verify URL output", 
				"Verify text in page output");
		StringBuilder desc = new StringBuilder();
		return OutputGenerator.createGenericMethodDesc("Home Page Test", "Verify Privacy Policy Links", desc, flowDesc);
	}


	public static String verifyPrivacyPolicyLinkDesc(){
		List<String> flowDesc = Arrays.asList("Go to home page", 
				"Click Privacy Policy Link",
				"Verify URL output", 
				"Verify text in page output");
		StringBuilder desc = new StringBuilder();
		return OutputGenerator.createGenericMethodDesc("Home Page Test", "Verify Privacy Policy Link", desc, flowDesc);
	}


	public static String verifyTermsOfUseLinkDesc(){
		List<String> flowDesc = Arrays.asList("Go to home page", 
				"Click Terms Of Use Link",
				"Verify URL output", 
				"Verify text in page output");
		StringBuilder desc = new StringBuilder();
		return OutputGenerator.createGenericMethodDesc("Home Page Test", "Verify Terms Of Use Link", desc, flowDesc);
	}
	


	
}
