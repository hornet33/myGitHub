package vbDumplingTestRunners;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import vbDumplingPages.MenuPage;

public class testMenuPage{

	final String expectedLHSHeaderText = "Menu";
	final String expectedMenuCategoryOneText = "Steamed Vegetable Momos";
	final String expectedMenuCategoryTwoText = "Pan-Fried Vegetable Momos";
	WebDriver driver;
	MenuPage objMenuPage;
	
	@BeforeClass
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
		
		//Initialize Menu Page POM class
		objMenuPage = new MenuPage(driver);
		
		//Navigate to Menu Page
		objMenuPage.clickMenuLink();
	}
	
	@Test(priority=20)
	public void checkLHSHeaderText() {
		Assert.assertEquals(objMenuPage.getMenuPageLHSHeaderText().toUpperCase(), expectedLHSHeaderText.toUpperCase());
	}
	
	@Test(priority=21)
	public void checkMenuCategoryOneText() {
		Assert.assertEquals(objMenuPage.getMenuPageMenuCategoryOneText(), expectedMenuCategoryOneText);
	}
	
	@Test(priority=21)
	public void checkMenuCategoryTwoText() {
		Assert.assertEquals(objMenuPage.getMenuPageMenuCategoryTwoText(), expectedMenuCategoryTwoText);
	}
	
	@AfterClass
	public void testClose() {
		driver.quit();
	}
}
