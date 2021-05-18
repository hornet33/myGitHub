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
	
	@BeforeClass
	@Parameters({"browserType","vbdURL"})
	public void initDriver(String browserType, String vbdURL) {
		//Create a new driver instance for the browserType specified in testng.xml
		initializeDriver objDriver = new initializeDriver();
		driver = objDriver.testSetup(browserType) ;
		
		//Launch AUT 
		driver.get(vbdURL);	
		
		//Initialize HomePage POM class
		objHomePage = new HomePage(driver);
		
		//Initialize CommonMethods class
		objCommonMethods = new CommonMethods(driver);
		objCommonMethods.consoleLogger("Home Page", true, false);
		objCommonMethods.consoleLogger("WebDriver Initialized ('" + browserType + "')");
	}
	
	@Test(priority=0)
	public void checkTitleText() {
		try {
			Assert.assertEquals(objHomePage.getHeaderText().toUpperCase(),expectedTitleText.toUpperCase());
			objCommonMethods.consoleLogger("Title Text", "Pass");
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger("Title Text", "Fail", ae.getMessage());
			//Fail the test
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority=1)
	public void checkDescriptionText() {
		try {
			Assert.assertEquals(objHomePage.getDescriptionText(),expectedDescriptionText);
			objCommonMethods.consoleLogger("Title Description", "Pass");
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger("Title Description", "Fail", ae.getMessage());
			//Fail the test
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority=3)
	public void checkHomePageLink() {
		try {
			objHomePage.clickHomePageLink();
			Assert.assertEquals(objHomePage.getPageTitle(), expectedHomePageTitle);
			objCommonMethods.consoleLogger("Title 'Home' Page link", "Pass");
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger("Title 'Home' Page link", "Fail", ae.getMessage());
			//Fail the test
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority=4)
	public void checkMenuHomeLink() {
		try {
			objHomePage.clickMenuHomeLink();
			Assert.assertEquals(objHomePage.getPageTitle(), expectedHomePageTitle);
			objCommonMethods.consoleLogger("Menu 'Home' Page link", "Pass");
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger("Menu 'Home' Page link", "Fail", ae.getMessage());
			//Fail the test
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority=5)
	public void checkMenuMenuLink() {
		try {
			objHomePage.clickMenuMenuLink();
			Assert.assertEquals(objHomePage.getPageTitle(), expectedMenuPageTitle);
			objCommonMethods.consoleLogger("Menu 'Menu' Page link", "Pass");
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger("Menu 'Menu' Page link", "Fail", ae.getMessage());
			//Fail the test
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority=6)
	public void checkMenuFAQLink() {
		try {
			objHomePage.clickMenuFAQLink();
			Assert.assertEquals(objHomePage.getPageTitle(), expectedFAQPageTitle);
			objCommonMethods.consoleLogger("Menu 'FAQs' Link","Pass");
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger("Menu 'FAQs' Link","Fail",ae.getMessage());
			//Fail the test
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority=7)
	public void checkMenuTestimonialsLink() {
		try {
			objHomePage.clickMenuTestimonialsLink();
			Assert.assertEquals(objHomePage.getPageTitle(), expectedTestimonialsPageTitle);
			objCommonMethods.consoleLogger("Menu 'Testimonials' Link","Pass");
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger("Menu 'Testimonials' Link","Fail",ae.getMessage());
			//Fail the test
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority=8)
	public void checkMenuAboutLink() {
		try {
			objHomePage.clickMenuAboutLink();
			Assert.assertEquals(objHomePage.getPageTitle(), expectedAboutPageTitle);
			objCommonMethods.consoleLogger("Menu 'About' Link","Pass");
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger("Menu 'About' Link","Fail",ae.getMessage());
			//Fail the test
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority=9)
	public void checkLHSHeaderText() {
		try {
			objHomePage.clickMenuHomeLink();
			Assert.assertEquals(objHomePage.getLHSHeaderText().toUpperCase(),expectedLHSHeaderText.toUpperCase());
			objCommonMethods.consoleLogger("LHS Header Text","Pass");
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger("LHS Header Text","Fail",ae.getMessage());
			//Fail the test
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority=10)
	public void checkMenuOrderLink() {
			//Store the ID of the original window
			String originalWindow = driver.getWindowHandle();	
			objHomePage.clickMenuOrderLink();		
			objHomePage.verifyOrderPageHeader(originalWindow, expectedOrderPageHeader);
			objCommonMethods.consoleLogger("Menu 'Order' Link","Pass");
	}
	
	@Test(priority=11)
	public void checkBodyOrderNowLink() {
		//Store the ID of the original window
		String originalWindow = driver.getWindowHandle();	
		objHomePage.clickBodyOrderNowLink();		
		objHomePage.verifyOrderPageHeader(originalWindow, expectedOrderPageHeader);
		objCommonMethods.consoleLogger("Body 'order now' Link","Pass");
	}
	
	@AfterClass
	public void testClose() {
		driver.quit();
		objCommonMethods.consoleLogger("Home Page", false, true);
	}
}
