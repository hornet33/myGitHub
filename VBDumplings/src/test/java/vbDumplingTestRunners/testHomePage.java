package vbDumplingTestRunners;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import vbDumplingPages.HomePage;

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
	
	@BeforeClass
	@Parameters({"browserType","vbdURL"})
	public void initDriver(String browserType, String vbdURL) {
		System.out.println("[TEST RUN] ----------------------------- Start of testHomePage -----------------------------");
		
		//Create a new driver instance for the browserType specified in testng.xml
		initializeDriver objDriver = new initializeDriver();
		driver = objDriver.testSetup(browserType) ;
		
		//Launch AUT 
		driver.get(vbdURL);	
		
		//Initialize HomePage POM class
		objHomePage = new HomePage(driver);
	}
	
	@Test(priority=0)
	public void checkTitleText() {
		Assert.assertEquals(objHomePage.getHeaderText().toUpperCase(),expectedTitleText.toUpperCase());
		System.out.println("[TEST RUN] HomePage: Title Text Pass");
	}
	
	@Test(priority=1)
	public void checkDescriptionText() {
		Assert.assertEquals(objHomePage.getDescriptionText(),expectedDescriptionText);
		System.out.println("[TEST RUN] HomePage: Title Description Text Pass");
	}
	
	@Test(priority=3)
	public void checkHomePageLink() {
		objHomePage.clickHomePageLink();
		Assert.assertEquals(objHomePage.getPageTitle(), expectedHomePageTitle);
		System.out.println("[TEST RUN] HomePage: Title 'Home' Page Link Pass");
	}
	
	@Test(priority=4)
	public void checkMenuHomeLink() {
		objHomePage.clickMenuHomeLink();
		Assert.assertEquals(objHomePage.getPageTitle(), expectedHomePageTitle);
		System.out.println("[TEST RUN] HomePage: Menu 'Home' Page Link Pass");
	}
	
	@Test(priority=5)
	public void checkMenuMenuLink() {
		objHomePage.clickMenuMenuLink();
		Assert.assertEquals(objHomePage.getPageTitle(), expectedMenuPageTitle);
		System.out.println("[TEST RUN] HomePage: Menu 'Menu' Link Pass");
	}
	
	@Test(priority=6)
	public void checkMenuFAQLink() {
		objHomePage.clickMenuFAQLink();
		Assert.assertEquals(objHomePage.getPageTitle(), expectedFAQPageTitle);
		System.out.println("[TEST RUN] HomePage: Menu 'FAQs' Link Pass");
	}
	
	@Test(priority=7)
	public void checkMenuTestimonialsLink() {
		objHomePage.clickMenuTestimonialsLink();
		Assert.assertEquals(objHomePage.getPageTitle(), expectedTestimonialsPageTitle);
		System.out.println("[TEST RUN] HomePage: Menu 'Testimonials' Link Pass");
	}
	
	@Test(priority=8)
	public void checkMenuAboutLink() {
		objHomePage.clickMenuAboutLink();
		Assert.assertEquals(objHomePage.getPageTitle(), expectedAboutPageTitle);
		System.out.println("[TEST RUN] HomePage: Menu 'About' Link Pass");
	}
	
	@Test(priority=9)
	public void checkLHSHeaderText() {
		objHomePage.clickMenuHomeLink();
		Assert.assertEquals(objHomePage.getLHSHeaderText().toUpperCase(),expectedLHSHeaderText.toUpperCase());
		System.out.println("[TEST RUN] HomePage: LHS Header Text Pass");
	}
	
	@Test(priority=10)
	public void checkMenuOrderLink() {
		//Store the ID of the original window
		String originalWindow = driver.getWindowHandle();	
		objHomePage.clickMenuOrderLink();		
		objHomePage.verifyOrderPageHeader(originalWindow, expectedOrderPageHeader);
		System.out.println("[TEST RUN] HomePage: Menu 'Order' Link Pass");
	}
	
	@Test(priority=11)
	public void checkBodyOrderNowLink() {
		//Store the ID of the original window
		String originalWindow = driver.getWindowHandle();	
		objHomePage.clickBodyOrderNowLink();		
		objHomePage.verifyOrderPageHeader(originalWindow, expectedOrderPageHeader);
		System.out.println("[TEST RUN] HomePage: Body 'order now' Link Pass");
	}
	
	@AfterClass
	public void testClose() {
		driver.quit();
		System.out.println("[TEST RUN] ----------------------------- End of testHomePage -----------------------------");
	}
}
