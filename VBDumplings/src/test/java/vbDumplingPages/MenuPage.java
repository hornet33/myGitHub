package vbDumplingPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.ArrayList;
import vbDumplingCommonMethods.CommonMethods;

public class MenuPage {
	
	WebDriver driver;
	CommonMethods objCommonMethods = new CommonMethods(driver);

	@FindBy(xpath = "//a[text()='Menu']") WebElement menuMenuLink;
	@FindBy(xpath = "//h1[@class='entry-title']") WebElement menuPageLHSHeader;
	@FindBy(xpath = "(//h4)[1]") WebElement menuPageMenuCategoryOne;
	@FindBy(xpath = "(//h4)[2]") WebElement menuPageMenuCategoryTwo;
	@FindBy(xpath = "//div[starts-with(@class,'wp-block-column')]/p/strong") List<WebElement> menuPageMenuItemNameList;
	@FindBy(xpath = "//div[starts-with(@class,'wp-block-column')]/p") List<WebElement> menuPageMenuItemFullTextList;
	
	public MenuPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickMenuLink() {
		objCommonMethods.clickWebElement(menuMenuLink);
	}
	
	public String getLHSHeaderText() {
		return(objCommonMethods.getWebElementText(menuPageLHSHeader));
	}
	
	public String getMenuCategoryOneText() {
		return(objCommonMethods.getWebElementText(menuPageMenuCategoryOne));
	}
	
	public String getMenuCategoryTwoText() {
		return(objCommonMethods.getWebElementText(menuPageMenuCategoryTwo));
	}
	
	public ArrayList<String> getMenuItemNames() {
		ArrayList<String> menuItemNames = new ArrayList<String>(); 
		for(WebElement menuPageItemName: menuPageMenuItemNameList) {
			menuItemNames.add(objCommonMethods.getWebElementText(menuPageItemName));
		}
		return(menuItemNames);
	}
	
	public ArrayList<String> getMenuItemFullText(){
		ArrayList<String> menuItemFullTexts = new ArrayList<String>(); 
		for(WebElement menuPageItemFullText: menuPageMenuItemFullTextList) {
			menuItemFullTexts.add(objCommonMethods.getWebElementText(menuPageItemFullText));
		}
		return(menuItemFullTexts);
	}
}
