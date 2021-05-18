package vbDumplingTestRunners;

import org.testng.annotations.Test;
import vbDumplingCommonMethods.CommonMethods;
import vbDumplingPages.TestimonialsPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class testTestimonialsPage {

	WebDriver driver;
	TestimonialsPage objTestimonialsPage;
	CommonMethods objCommonMethods;
	String consoleTestName;
	String consolePageName = "T E S T I M O N I A L S   P A G E";
	
	final String expectedLHSHeaderText = "Testimonials";
	final String sampleCommentText = "This is a test comment using Selenium Webdriver!";
	final String sampleCommentEmail = "rahulautomationemail2021@gmail.com";
	final String sampleCommentAuthor = "Rahul Selenium";
	final String sampleCommentURL = "https://github.com";
	

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

		//Initialize Testimonials Page POM class
		objTestimonialsPage = new TestimonialsPage(driver);
		
		//Navigate to FAQ Page
		objTestimonialsPage.clickTestimonialsLink();
	}
	
	@Test(priority=40)
	public void checkLHSHeaderText() {
		consoleTestName = "LHS Header Text";
		try { 
			Assert.assertEquals(objTestimonialsPage.getLHSHeaderText().toUpperCase(), expectedLHSHeaderText.toUpperCase());
			objCommonMethods.consoleLogger(consoleTestName, "Pass");
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger(consoleTestName, "Fail", ae.getMessage());
			Assert.fail(ae.getMessage());
		}
	}
	
	@Test(priority=41)
	public void checkCommentsTitle() {
		consoleTestName = "Comments Count In Comments Header";
		String actualCommentsTitleText = objTestimonialsPage.getCommentsHeaderText();
		String actualCommentsCount = actualCommentsTitleText.substring(0,actualCommentsTitleText.indexOf("Replies")).trim();
		String totalCommentsCount = String.valueOf(objTestimonialsPage.getCommentsAsWebelements().size());
		if(actualCommentsCount.equals(totalCommentsCount)) {
			Assert.assertTrue(true);
			objCommonMethods.consoleLogger(consoleTestName, "Pass", actualCommentsCount + "/" + totalCommentsCount);
		}
		else {
			String errorMsg = "Comments count mismatch - header = '" + actualCommentsCount + "', total comments = '" + totalCommentsCount + "'";
			objCommonMethods.consoleLogger(consoleTestName, "Fail", errorMsg);
			Assert.fail(errorMsg);
		}
	}
	
	@Test(priority=42)
	public void checkNewCommentErrorAllEmpty() {
		
	}
	
	@Test(priority=43)
	public void checkNewCommentErrorOnlyEmailEmpty() {
		
	}
	
	@Test(priority=44)
	public void checkNewCommentErrorOnlyAuthorEmpty() {
		
	}
	
	@Test(priority=45)
	public void checkNewCommentSuccessNoNotificationsSelected() {
		
	}
	
	@Test(priority=46)
	public void checkNewCommentWordpressLink() {
		
	}
	
	@Test(priority=46)
	public void checkNewCommentTwitterLink() {
		
	}
	
	@Test(priority=46)
	public void checkNewCommentFacebookLink() {
		
	}

	@Test(priority=47)
	public void checkNewCommentSuccessSubscribeCommentsSelected() {
		
	}
	
	@Test(priority=47)
	public void checkNewCommentSuccessSubscribePostsSelected() {
		
	}
	
	@AfterClass
	public void testClose() {
		driver.quit();
		objCommonMethods.consoleLogger(consolePageName, false, true);
	}
}
