package vbDumplingTestRunners;

import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Parameters;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class vbdRunner {
	
	WebDriver driver;
	
	@Test
	@Parameters({"browserType"})
	public void testMethod(String browserType) {
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
		driver.get("http://www.facebook.com");
		Assert.assertEquals(driver.getTitle(),"Facebook – log in or sign up");	
	}
	
	@BeforeMethod
	@Parameters({"browserType"})
	public void beforeMethod(String browserType) {
		System.out.println("Sample Maven TestNG - beforeMethod [" + browserType + "]");
	}

	@AfterMethod
	@Parameters({"browserType"})
	public void afterMethod(String browserType) {
		System.out.println("Sample Maven TestNG - afterMethod [" + browserType + "]");
		driver.quit();
	}
}
