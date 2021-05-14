package vbDumplingTestRunners;

import vbDumplingPages.FAQPage;
import vbDumplingPages.HomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class testFaqPage {

	WebDriver driver;
	FAQPage objFaqPage;
	HomePage objHomePage;
	
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
		System.out.println("[TEST RUN] ----------------------------- Start of testFaqPage -----------------------------");

		//Create a new driver instance for the browserType specified in testng.xml
		initializeDriver objDriver = new initializeDriver();
		driver = objDriver.testSetup(browserType) ;

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
		try { 
			Assert.assertEquals(objFaqPage.getFaqPageLHSHeaderText().toUpperCase(), expectedLHSHeaderText.toUpperCase());
			System.out.println("[TEST RUN] FaqPage: LHS Header Text Pass");
		}
		catch(AssertionError ae) {
			System.out.println("[TEST RUN] FaqPage: LHS Header Text Fail (" + ae.getMessage() + ")");
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority=31)
	public void checkFaqPageContentHeaderText() {
		try {
			Assert.assertEquals(objFaqPage.getFaqPageContentHeaderText().toUpperCase(), expectedContentHeaderText.toUpperCase());
			System.out.println("[TEST RUN] FaqPage: Content Header Text Pass");
		}
		catch(AssertionError ae) {
			System.out.println("[TEST RUN] FaqPage: Content Header Text Fail (" + ae.getMessage() + ")");
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority=32)
	public void checkFaqPageQuestions() {
		ArrayList<String> actualFaqPageQuestionList = objFaqPage.getFaqPageQuestions();
		int i = 0;
		try {
			for(String actualFaqPageQuestion: actualFaqPageQuestionList) {
				System.out.println("[TEST RUN] FAQs (Expected/Actual): '" + expectedQuestionList[i].trim() + "'/'" + actualFaqPageQuestion.trim() + "'");
				Assert.assertEquals(expectedQuestionList[i].trim(), actualFaqPageQuestion.trim());
				i++;
			}
			System.out.println("[TEST RUN] FaqPage: Question List Pass");
		}
		catch(AssertionError ae) {
			System.out.println("[TEST RUN] FaqPage: Question List Fail (" + ae.getMessage() + ")");
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority=33)
	public void clickAllFaqPageQuestionsExpand() {
		ArrayList<String> actualFaqPageQuestionList = objFaqPage.getFaqPageQuestions();
		List<WebElement> faqPageExpandedAnswersWebelements = objFaqPage.getFaqPageExpandedAnswersAsWebelements();
		int questionIndex = 0;
		try {
			for(String actualFaqPageQuestion: actualFaqPageQuestionList) {
				System.out.println("[TEST RUN] FaqPage: Clicking on question '" + actualFaqPageQuestion + "', index '" + questionIndex + "'");
				//Click on the FAQ page question to expand the answer body
				objFaqPage.clickFaqPageQuestion(questionIndex);
				//Verify that the expanded answer body is displayed
				if(objFaqPage.isElementVisible(faqPageExpandedAnswersWebelements.get(questionIndex), 2)) {
					//Pass the test
					Assert.assertTrue(true);					
				}
				else {
					//Fail the test
					Assert.assertTrue(false);
				}
				questionIndex++;
			}
			System.out.println("[TEST RUN] FaqPage: Question Click Expand Tests Pass");
		}
		catch(AssertionError ae) {
			System.out.println("[TEST RUN] FaqPage: Question Click Expand Tests Fail (" + ae.getMessage() + ")");
		}
	}
	
	@Test(priority=34)
	public void checkOrderFormLink() {
		//Check if any of the answers has the "Order Form" link displayed
		//Store the ID of the original window
		String originalWindow = driver.getWindowHandle();	
		objFaqPage.clickFaqPageOrderFormLink();
		objHomePage.verifyOrderPageHeader(originalWindow, expectedOrderPageHeader);
		System.out.println("[TEST RUN] FaqPage: Body 'Order Form' Link Pass");		
	}
	
	@Test(priority=35)
	public void clickAllFaqPageQuestionsCollapse() {
		ArrayList<String> actualFaqPageQuestionList = objFaqPage.getFaqPageQuestions();
		List<WebElement> faqPageExpandedAnswersWebelements = objFaqPage.getFaqPageExpandedAnswersAsWebelements();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement faqPageContentHeader = objFaqPage.getFaqPageContentHeaderElement();
		
		//Since all questions are expanded, the first question is not in view
		//Scrolling up until the first FAQ comes into view
		js.executeScript("arguments[0].scrollIntoView();", faqPageContentHeader);
		
		int questionIndex = 0;
		try {
			for(String actualFaqPageQuestion: actualFaqPageQuestionList) {
				System.out.println("[TEST RUN] FaqPage: Clicking on question '" + actualFaqPageQuestion + "', index '" + questionIndex + "'");
				//Click on the FAQ page question to expand the answer body
				objFaqPage.clickFaqPageQuestion(questionIndex);
				//Verify that the expanded answer body is NOT displayed since clicking on an expanded question will collapse it
				if(!objFaqPage.clickFaqPageExpandedAnswer(questionIndex)) {
					//Pass the test
					Assert.assertTrue(true);					
				}
				else {
					//Fail the test
					Assert.assertTrue(false);
				}
				questionIndex++;
			}
			System.out.println("[TEST RUN] FaqPage: Question Click Collapse Tests Pass");
		}
		catch(AssertionError ae) {
			System.out.println("[TEST RUN] FaqPage: Question Click Collapse Tests Fail (" + ae.getMessage() + ")");
		}
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
		System.out.println("[TEST RUN] ----------------------------- End of testFaqPage -----------------------------");
	}
}
