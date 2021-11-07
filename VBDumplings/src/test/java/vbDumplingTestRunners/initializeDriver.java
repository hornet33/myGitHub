package vbDumplingTestRunners;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class initializeDriver {
	WebDriver driver;
	
	public WebDriver testSetup(String browserType) {
		if (browserType.equalsIgnoreCase("chrome")) {			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			//Implicit wait of 30 seconds for Chrome and Firefox
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		else if (browserType.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			//Implicit wait of 30 seconds for Chrome and Firefox
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		else if (browserType.equalsIgnoreCase("msedge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			//Implicit wait of 60 seconds for MS Edge
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
		
		driver.manage().window().maximize();		
		
		//Return driver instance
		return(driver);
	}
}
