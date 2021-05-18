package vbDumplingTestRunners;

import vbDumplingPages.FAQPage;
import vbDumplingPages.HomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import vbDumplingCommonMethods.CommonMethods;

public class testFaqPage {

	WebDriver driver;
	FAQPage objFaqPage;
	HomePage objHomePage;
	CommonMethods objCommonMethods;
	String consoleTestName;
	String consolePageName = "F A Q S   P A G E";
	
	final String expectedLHSHeaderText = "FAQS";
	final String expectedContentHeaderText = "Frequently Asked Questions (FAQ)";
	final String[] expectedQuestionList = 
		{	"What are momos?",
			"How many momos are there in a plate?",
			"Are your momos pure vegetarian?",
			"How do you maintain segregation in your momos?",
			"How is quality maintained in your momos?",
			"What modes of payment do you accept?",
			"How do I place an order?",
			"How early do I need to place my order?",
			"How many calories are there in a plate of momos?",
			"Why are there timing restrictions and why is Sunday off?"
		};
	final String expectedOrderPageHeader = "VB Dumplings";

	@BeforeClass
	@Parameters({"browserType","vbdURL"})
	public void initDriver(String browserType, String vbdURL) {
		//Create a new driver instance for the browserType specified in testng.xml
		initializeDriver objDriver = new initializeDriver();
		driver = objDriver.testSetup(browserType) ;

		//Initialize CommonMethods class
		objCommonMethods = new CommonMethods(driver);
		objCommonMethods.consoleLogger(consolePageName, true, false);
		objCommonMethods.consoleLogger("WebDriver Initialized ('" + browserType + "')");

		//Launch AUT 
		driver.get(vbdURL);	

		//Initialize FAQ Page POM class
		objFaqPage = new FAQPage(driver);
		
		//Navigate to FAQ Page
		objFaqPage.clickMenuFaqLink();
		
		//Initialize Home Page POM class (to verify Order page link click)
		objHomePage = new HomePage(driver);
	}
	
	@Test(priority=30)
	public void checkFaqPageLHSHeaderText() {
		consoleTestName = "LHS Header Text";
		try { 
			Assert.assertEquals(objFaqPage.getLHSHeaderText().toUpperCase(), expectedLHSHeaderText.toUpperCase());
			objCommonMethods.consoleLogger(consoleTestName, "Pass");
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger(consoleTestName, "Fail", ae.getMessage());
			Assert.fail(ae.getMessage());
		}
	}
	
	@Test(priority=31)
	public void checkFaqPageContentHeaderText() {
		consoleTestName = "Content Header Text";
		try {
			Assert.assertEquals(objFaqPage.getContentHeaderText().toUpperCase(), expectedContentHeaderText.toUpperCase());
			objCommonMethods.consoleLogger(consoleTestName, "Pass");
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger(consoleTestName, "Fail", ae.getMessage());
			Assert.fail(ae.getMessage());
		}
	}
	
	@Test(priority=32)
	public void checkFaqPageQuestions() {
		consoleTestName = "Question List";
		ArrayList<String> actualFaqPageQuestionList = objFaqPage.getQuestions();
		int i = 0;
		try {
			for(String actualFaqPageQuestion: actualFaqPageQuestionList) {
				objCommonMethods.consoleLogger("FAQs (Expected/Actual): '" + expectedQuestionList[i].trim() + "'/'" + actualFaqPageQuestion.trim() + "'");
				Assert.assertEquals(expectedQuestionList[i].trim(), actualFaqPageQuestion.trim());
				i++;
			}
			objCommonMethods.consoleLogger(consoleTestName, "Pass");
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger(consoleTestName, "Fail", ae.getMessage());
			Assert.fail(ae.getMessage());
		}
	}
	
	@Test(priority=33)
	public void clickAllFaqPageQuestionsExpand() {
		consoleTestName = "Question Click Expand Tests";
		ArrayList<String> actualFaqPageQuestionList = objFaqPage.getQuestions();
		List<WebElement> faqPageExpandedAnswersWebelements = objFaqPage.getExpandedAnswersAsWebelements();
		int questionIndex = 0;
		try {
			for(String actualFaqPageQuestion: actualFaqPageQuestionList) {
				objCommonMethods.consoleLogger("Clicking on question '" + actualFaqPageQuestion + "', index '" + questionIndex + "'");
				//Click on the FAQ page question to expand the answer body
				objFaqPage.clickQuestion(questionIndex);
				//Verify that the expanded answer body is displayed
				if(objCommonMethods.isElementVisible(faqPageExpandedAnswersWebelements.get(questionIndex), 2)) {
					//Pass the test
					Assert.assertTrue(true);					
				}
				else {
					//Fail the test
					Assert.fail("Question '" + actualFaqPageQuestion + "' should be visible but is not");
				}
				questionIndex++;
			}
			objCommonMethods.consoleLogger(consoleTestName, "Pass");
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger(consoleTestName, "Fail", ae.getMessage());
		}
	}
	
	@Test(priority=34)
	public void checkOrderFormLink() {
		consoleTestName = "Body 'Order Form' Link";
		//Check if any of the answers has the "Order Form" link displayed
		//Store the ID of the original window
		String originalWindow = driver.getWindowHandle();	
		objFaqPage.clickOrderFormLink();		
		if(objHomePage.verifyOrderPageHeader(originalWindow, expectedOrderPageHeader)) {
			Assert.assertTrue(true);
			objCommonMethods.consoleLogger(consoleTestName, "Pass");
		}
		else {
			objCommonMethods.consoleLogger(consoleTestName, "Fail", "Order page header did not match '"+expectedOrderPageHeader+"'");
			Assert.fail("Order page header did not match '"+expectedOrderPageHeader+"'");
		}
	}
	
	@Test(priority=35)
	public void clickAllFaqPageQuestionsCollapse() {
		consoleTestName = "Question Click Collapse Tests";
		ArrayList<String> actualFaqPageQuestionList = objFaqPage.getQuestions();		
		WebElement faqPageContentHeader = objFaqPage.getContentHeaderElement();
		
		//Since all questions are expanded, the first question is not in view
		//Scrolling up until the first FAQ comes into view
		objCommonMethods.scrollUntilElementInView(faqPageContentHeader);
		
		int questionIndex = 0;
		try {
			for(String actualFaqPageQuestion: actualFaqPageQuestionList) {
				System.out.println("[TEST RUN] FaqPage: Clicking on question '" + actualFaqPageQuestion + "', index '" + questionIndex + "'");
				//Click on the FAQ page question to expand the answer body
				objFaqPage.clickQuestion(questionIndex);
				//Verify that the expanded answer body is NOT displayed since clicking on an expanded question will collapse it
				if(!objFaqPage.clickExpandedAnswer(questionIndex)) {
					//Pass the test
					Assert.assertTrue(true);
				}
				else {
					//Fail the test
					Assert.fail("Question '" + actualFaqPageQuestion + "' should NOT be visible");
				}
				questionIndex++;
			}
			objCommonMethods.consoleLogger(consoleTestName, "Pass");
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger(consoleTestName, "Fail", ae.getMessage());
		}
	}
	
	@AfterClass
	public void testClose() {
		driver.quit();
		objCommonMethods.consoleLogger(consolePageName, false, true);
	}
}
