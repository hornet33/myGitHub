package vbDumplingTestRunners;

import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import vbDumplingPages.MenuPage;
import vbDumplingCommonMethods.CommonMethods;

public class testMenuPage{

	final String expectedLHSHeaderText = "Menu";
	final String expectedMenuCategoryOneText = "Steamed Vegetable Momos";
	final String expectedMenuCategoryTwoText = "Pan-Fried Vegetable Momos";
	final String[] expectedMenuItemNames = 
		{"Wheat/Maida Cabbage","Wheat/Maida Soya-chunk","Wheat/Maida Paneer","Wheat/Maida Cabbage + Cheese","Wheat/Maida Soya-chunk + Cheese",
		 "Wheat/Maida Paneer + Cheese","Pan-fried Maida Cabbage","Pan-fried Maida Cabbage + Cheese","Pan-fried Maida Soya-chunk","Pan-fried Maida Soya-chunk + Cheese"
		 };
	final String[] expectedMenuItemPrices = 
		{"55 per plate","55 per plate","60 per plate","60 per plate","60 per plate",
		 "65 per plate","70 per plate","75 per plate","70 per plate","75 per plate"				
		};
	WebDriver driver;
	MenuPage objMenuPage;
	CommonMethods objCommonMethods;
	String consoleTestName;
	String consolePageName = "M E N U   P A G E";

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
		
		//Initialize Menu Page POM class
		objMenuPage = new MenuPage(driver);
				
		//Navigate to Menu Page
		objMenuPage.clickMenuLink();
		
	}
	
	@Test(priority=20)
	public void checkLHSHeaderText() {
		consoleTestName = "LHS Header Text";
		try {
			Assert.assertEquals(objMenuPage.getLHSHeaderText().toUpperCase(), expectedLHSHeaderText.toUpperCase());
			objCommonMethods.consoleLogger(consoleTestName, "Pass");
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger(consoleTestName, "Fail", ae.getMessage());
			Assert.fail(ae.getMessage());
		}
	}
	
	@Test(priority=21)
	public void checkMenuCategoryOneText() {
		consoleTestName = "Menu Category One Text";
		try {
			Assert.assertEquals(objMenuPage.getMenuCategoryOneText(), expectedMenuCategoryOneText);
			objCommonMethods.consoleLogger(consoleTestName, "Pass");
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger(consoleTestName, "Fail", ae.getMessage());
			Assert.fail(ae.getMessage());
		}
	}
	
	@Test(priority=21)
	public void checkMenuCategoryTwoText() {
		consoleTestName = "Menu Category Two Text";
		try {
			Assert.assertEquals(objMenuPage.getMenuCategoryTwoText(), expectedMenuCategoryTwoText);
			objCommonMethods.consoleLogger(consoleTestName, "Pass");
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger(consoleTestName, "Fail", ae.getMessage());
			Assert.fail(ae.getMessage());
		}
	}
	
	@Test(priority=22)
	public void checkMenuItemNames() {
		consoleTestName = "Menu Item Names";
		ArrayList<String> actualMenuItemNames = objMenuPage.getMenuItemNames();
		try {
			for(int i=0;i<expectedMenuItemNames.length;i++) {
				objCommonMethods.consoleLogger("Menu Item Name (Expected/Actual): '" + expectedMenuItemNames[i].trim() + "'/'" + actualMenuItemNames.get(i).trim() + "'");
				Assert.assertEquals(expectedMenuItemNames[i].trim(),actualMenuItemNames.get(i).trim());
			}
			objCommonMethods.consoleLogger(consoleTestName, "Pass");
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger(consoleTestName, "Fail", ae.getMessage());
			Assert.fail(ae.getMessage());
		}
	}
	
	@Test(priority=23)
	public void checkMenuItemPrices() {
		consoleTestName = "Menu Item Prices";
		ArrayList<String> actualMenuItemFullText = objMenuPage.getMenuItemFullText();
		int i = 0;
		int priceIndexInFullText = 0;
		try {
			for(String s: actualMenuItemFullText) {
				priceIndexInFullText = s.indexOf("\n")+2;
				objCommonMethods.consoleLogger("Menu Prices (Expected/Actual): '" + expectedMenuItemPrices[i].trim() + "'/'" + 
						s.substring(priceIndexInFullText).trim() + "'");
				Assert.assertEquals(expectedMenuItemPrices[i].trim(), s.substring(priceIndexInFullText).trim());
				i++;
			}
			objCommonMethods.consoleLogger(consoleTestName, "Pass");
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger(consoleTestName, "Fail", ae.getMessage());
			Assert.fail(ae.getMessage());
		}
	}
	
	@AfterClass
	public void testClose() {
		driver.quit();
		objCommonMethods.consoleLogger(consolePageName, false, true);
	}
}
