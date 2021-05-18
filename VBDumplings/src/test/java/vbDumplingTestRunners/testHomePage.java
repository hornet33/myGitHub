package vbDumplingTestRunners;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import vbDumplingPages.HomePage;
import vbDumplingCommonMethods.CommonMethods;

public class testHomePage{

	final String expectedTitleText = "VB Dumplings";
	final String expectedDescriptionText = "Authentic vegetarian Darjeeling momos!";
	final String expectedHomePageTitle = "VB Dumplings – Authentic vegetarian Darjeeling momos!";
	final String expectedMenuPageTitle = "Menu – VB Dumplings";
	final String expectedFAQPageTitle = "FAQs – VB Dumplings";
	final String expectedTestimonialsPageTitle = "Testimonials – VB Dumplings";
	final String expectedAboutPageTitle = "About – VB Dumplings";
	final String expectedLHSHeaderText = "Welcome to VB Dumplings!";
	final String expectedOrderPageHeader = "VB Dumplings";
	WebDriver driver;
	HomePage objHomePage;
	CommonMethods objCommonMethods;
	String consoleTestName;
	String consolePageName = "H O M E   P A G E";
	
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
		
		//Initialize HomePage POM class
		objHomePage = new HomePage(driver);
	}
	
	@Test(priority=0)
	public void checkTitleText() {
		consoleTestName = "Title Text";
		try {
			Assert.assertEquals(objHomePage.getHeaderText().toUpperCase(),expectedTitleText.toUpperCase());
			objCommonMethods.consoleLogger(consoleTestName, "Pass");
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger(consoleTestName, "Fail", ae.getMessage());
			Assert.fail(ae.getMessage());
		}
	}
	
	@Test(priority=1)
	public void checkDescriptionText() {
		consoleTestName = "Title Description";
		try {
			Assert.assertEquals(objHomePage.getDescriptionText(),expectedDescriptionText);
			objCommonMethods.consoleLogger(consoleTestName, "Pass");
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger(consoleTestName, "Fail", ae.getMessage());
			Assert.fail(ae.getMessage());
		}
	}
	
	@Test(priority=2)
	public void checkHomePageLink() {
		consoleTestName = "Title 'Home' Page link";
		try {
			objHomePage.clickHomePageLink();
			Assert.assertEquals(objHomePage.getPageTitle(), expectedHomePageTitle);
			objCommonMethods.consoleLogger(consoleTestName, "Pass");
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger(consoleTestName, "Fail", ae.getMessage());
			Assert.fail(ae.getMessage());
		}
	}
	
	@Test(priority=3)
	public void checkLHSHeaderText() {
		consoleTestName = "LHS Header Text";
		try {
			objHomePage.clickMenuHomeLink();
			Assert.assertEquals(objHomePage.getLHSHeaderText().toUpperCase(),expectedLHSHeaderText.toUpperCase());
			objCommonMethods.consoleLogger(consoleTestName, "Pass");
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger(consoleTestName, "Fail", ae.getMessage());
			Assert.fail(ae.getMessage());
		}
	}
	
	@Test(priority=4)
	public void checkMenuHomeLink() {
		consoleTestName = "Menu 'Home' Page link";
		try {
			objHomePage.clickMenuHomeLink();
			Assert.assertEquals(objHomePage.getPageTitle(), expectedHomePageTitle);
			objCommonMethods.consoleLogger(consoleTestName, "Pass");
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger(consoleTestName, "Fail", ae.getMessage());
			Assert.fail(ae.getMessage());
		}
	}
	
	@Test(priority=5)
	public void checkMenuMenuLink() {
		consoleTestName = "Menu 'Menu' Page link";	
		try {
			objHomePage.clickMenuMenuLink();
			Assert.assertEquals(objHomePage.getPageTitle(), expectedMenuPageTitle);
			objCommonMethods.consoleLogger(consoleTestName, "Pass");
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger(consoleTestName, "Fail", ae.getMessage());
			Assert.fail(ae.getMessage());
		}
	}
	
	@Test(priority=6)
	public void checkMenuFAQLink() {
		consoleTestName = "Menu 'FAQs' Link";
		try {
			objHomePage.clickMenuFAQLink();
			Assert.assertEquals(objHomePage.getPageTitle(), expectedFAQPageTitle);
			objCommonMethods.consoleLogger(consoleTestName, "Pass");
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger(consoleTestName, "Fail", ae.getMessage());
			Assert.fail(ae.getMessage());
		}
	}
	
	@Test(priority=7)
	public void checkMenuTestimonialsLink() {
		consoleTestName = "Menu 'Testimonials' Link";
		try {
			objHomePage.clickMenuTestimonialsLink();
			Assert.assertEquals(objHomePage.getPageTitle(), expectedTestimonialsPageTitle);
			objCommonMethods.consoleLogger(consoleTestName, "Pass");
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger(consoleTestName, "Fail", ae.getMessage());
			Assert.fail(ae.getMessage());
		}
	}
	
	@Test(priority=8)
	public void checkMenuOrderLink() {
		consoleTestName = "Menu 'Order' Link";
		//Store the ID of the original window
		String originalWindow = driver.getWindowHandle();	
		objHomePage.clickMenuOrderLink();		
		if(objHomePage.verifyOrderPageHeader(originalWindow, expectedOrderPageHeader)) {
			Assert.assertTrue(true);
			objCommonMethods.consoleLogger(consoleTestName, "Pass");
		}
		else {
			objCommonMethods.consoleLogger(consoleTestName, "Fail", "Order page header did not match '"+expectedOrderPageHeader+"'");
			Assert.fail("Order page header did not match '" + expectedOrderPageHeader + "'");
		}
	}
	
	@Test(priority=9)
	public void checkMenuAboutLink() {
		consoleTestName = "Menu 'About' Link";
		try {
			objHomePage.clickMenuAboutLink();
			Assert.assertEquals(objHomePage.getPageTitle(), expectedAboutPageTitle);
			objCommonMethods.consoleLogger(consoleTestName, "Pass");
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger(consoleTestName, "Fail", ae.getMessage());
			Assert.fail(ae.getMessage());
		}
	}
	
	@Test(priority=10)
	public void checkBodyOrderNowLink() {
		consoleTestName = "Body 'order now' Link";
		
		//Navigate to home page
		objHomePage.clickMenuHomeLink();
		
		//Store the ID of the original window
		String originalWindow = driver.getWindowHandle();	
		
		objHomePage.clickBodyOrderNowLink();		
		
		if(objHomePage.verifyOrderPageHeader(originalWindow, expectedOrderPageHeader)) {
			Assert.assertTrue(true);
			objCommonMethods.consoleLogger(consoleTestName, "Pass");
		}
		else {
			objCommonMethods.consoleLogger(consoleTestName, "Fail", "Order page header did not match '"+expectedOrderPageHeader+"'");
			Assert.fail("Order page header did not match '" + expectedOrderPageHeader + "'");			
		}
	}
	
	@AfterClass
	public void testClose() {
		driver.quit();
		objCommonMethods.consoleLogger(consolePageName, false, true);
	}
}
