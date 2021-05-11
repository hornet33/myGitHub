package vbDumplingTestRunners;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import vbDumplingPages.HomePage;

public class testHomePage{

	final String expectedTitleText = "VB Dumplings";
	final String expectedDescriptionText = "Authentic vegetarian Darjeeling momos!";
	final String expectedHomePageTitle = "VB Dumplings – Authentic vegetarian Darjeeling momos!";
	final String expectedMenuPageTitle = "Menu – VB Dumplings";
	final String expectedFAQPageTitle = "FAQs – VB Dumplings";
	WebDriver driver;
	HomePage objHomePage;
	
	@BeforeTest
	@Parameters({"browserType","vbdURL"})
	public void testSetup(String browserType, String vbdURL) {
		if (browserType.equalsIgnoreCase("chrome")) {			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if (browserType.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if (browserType.equalsIgnoreCase("msedge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		//Implicit wait of 30 seconds
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//Launch AUT 
		driver.get(vbdURL);	
		
		//Initialize HomePage POM class
		objHomePage = new HomePage(driver);
	}
	
	@Test(priority=0)
	public void checkTitleText() {
		Assert.assertEquals(objHomePage.getHeaderText().toUpperCase(),expectedTitleText.toUpperCase());
	}
	
	@Test(priority=1)
	public void checkDescriptionText() {
		Assert.assertEquals(objHomePage.getDescriptionText(),expectedDescriptionText);
	}
	
	@Test(priority=3)
	public void checkHomePageLink() {
		objHomePage.clickHomePageLink();
		Assert.assertEquals(objHomePage.getPageTitle(), expectedHomePageTitle);
	}
	
	@Test(priority=4)
	public void checkMenuHomeLink() {
		objHomePage.clickMenuHomeLink();
		Assert.assertEquals(objHomePage.getPageTitle(), expectedHomePageTitle);
	}
	
	@Test(priority=5)
	public void checkMenuMenuLink() {
		objHomePage.clickMenuMenuLink();
		Assert.assertEquals(objHomePage.getPageTitle(), expectedMenuPageTitle);
	}
	
	@Test(priority=6)
	public void checkMenuFAQLink() {
		objHomePage.clickMenuFAQLink();
		Assert.assertEquals(objHomePage.getPageTitle(), expectedFAQPageTitle);
	}
	
	@AfterTest
	public void testClose() {
		driver.quit();
	}
}
