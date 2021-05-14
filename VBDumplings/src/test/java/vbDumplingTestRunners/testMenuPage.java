package vbDumplingTestRunners;

import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import vbDumplingPages.MenuPage;

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

	@BeforeClass
	@Parameters({"browserType","vbdURL"})
	public void initDriver(String browserType, String vbdURL) {
		System.out.println("[TEST RUN] ----------------------------- Start of testMenuPage -----------------------------");
		
		//Create a new driver instance for the browserType specified in testng.xml
		initializeDriver objDriver = new initializeDriver();
		driver = objDriver.testSetup(browserType) ;
		
		//Launch AUT 
		driver.get(vbdURL);	
		
		//Initialize Menu Page POM class
		objMenuPage = new MenuPage(driver);
				
		//Navigate to Menu Page
		objMenuPage.clickMenuLink();
	}
	
	@Test(priority=20)
	public void checkLHSHeaderText() {
		try {
			Assert.assertEquals(objMenuPage.getMenuPageLHSHeaderText().toUpperCase(), expectedLHSHeaderText.toUpperCase());
			System.out.println("[TEST RUN] MenuPage: LHS Header Text Pass");
		}
		catch(AssertionError ae) {
			System.out.println("[TEST RUN] MenuPage: LHS Header Text Fail (" + ae.getMessage() + ")");
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority=21)
	public void checkMenuCategoryOneText() {
		try {
			Assert.assertEquals(objMenuPage.getMenuPageMenuCategoryOneText(), expectedMenuCategoryOneText);
			System.out.println("[TEST RUN] MenuPage: Menu Category One Text Pass");
		}
		catch(AssertionError ae) {
			System.out.println("[TEST RUN] MenuPage: Menu Category One Text Fail (" + ae.getMessage() + ")");
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority=21)
	public void checkMenuCategoryTwoText() {
		try {
			Assert.assertEquals(objMenuPage.getMenuPageMenuCategoryTwoText(), expectedMenuCategoryTwoText);
			System.out.println("[TEST RUN] MenuPage: Menu Category Two Text Pass");
		}
		catch(AssertionError ae) {
			System.out.println("[TEST RUN] MenuPage: Menu Category Two Text Fail (" + ae.getMessage() + ")");
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority=22)
	public void checkMenuItemNames() {
		ArrayList<String> actualMenuItemNames = objMenuPage.getMenuPageMenuItemNames();
		try {
			for(int i=0;i<expectedMenuItemNames.length;i++) {
				System.out.println("[TEST RUN] Menu Item Name (Expected/Actual): '" + expectedMenuItemNames[i].trim() + "'/'" + actualMenuItemNames.get(i).trim() + "'");
				Assert.assertEquals(expectedMenuItemNames[i].trim(),actualMenuItemNames.get(i).trim());
			}
			System.out.println("[TEST RUN] MenuPage: Menu Item Names Pass");
		}
		catch(AssertionError ae) {
			System.out.println("[TEST RUN] MenuPage: Menu Item Names Fail (" + ae.getMessage() + ")");
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority=23)
	public void checkMenuItemPrices() {
		ArrayList<String> actualMenuItemFullText = objMenuPage.getMenuPageMenuItemFullText();
		int i = 0;
		int priceIndexInFullText = 0;
		try {
			for(String s: actualMenuItemFullText) {
				priceIndexInFullText = s.indexOf("\n")+2;
				System.out.println("[TEST RUN] Menu Prices (Expected/Actual): '" + expectedMenuItemPrices[i].trim() + "'/'" + 
						s.substring(priceIndexInFullText).trim() + "'");
				Assert.assertEquals(expectedMenuItemPrices[i].trim(), s.substring(priceIndexInFullText).trim());
				i++;
			}
			System.out.println("[TEST RUN] MenuPage: Menu Item Prices Pass");
		}
		catch(AssertionError ae) {
			System.out.println("[TEST RUN] MenuPage: Menu Item Prices Fail (" + ae.getMessage() + ")");
			Assert.assertTrue(false);
		}
	}
	
	@AfterClass
	public void testClose() {
		driver.quit();
		System.out.println("[TEST RUN] ----------------------------- End of testMenuPage -----------------------------");
	}
}
