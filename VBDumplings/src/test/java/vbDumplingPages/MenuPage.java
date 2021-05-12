package vbDumplingPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.ArrayList;

public class MenuPage {
	
	WebDriver driver;

	@FindBy(xpath = "//a[text()='Menu']") WebElement menuMenuLink;
	@FindBy(xpath = "//h1[@class='entry-title']") WebElement menuPageLHSHeader;
	@FindBy(xpath = "(//h4)[1]") WebElement menuPageMenuCategoryOne;
	@FindBy(xpath = "(//h4)[2]") WebElement menuPageMenuCategoryTwo;
	@FindBy(xpath = "//div[@class='wp-block-column']/p/strong") List<WebElement> menuPageMenuItemName;
	@FindBy(xpath = "//div[@class='wp-block-column']/p") List<WebElement> menuPageMenuItemFullText;
	@FindBy(xpath = "//div[@class='wp-block-column is-vertically-aligned-top']/p/strong") List<WebElement> menuPageMenuItemNameRemaining;
	@FindBy(xpath = "//div[@class='wp-block-column is-vertically-aligned-top']/p") List<WebElement> menuPageMenuItemFullTextRemaining;
	
	public MenuPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickMenuLink() {
		menuMenuLink.click();
	}
	
	public String getMenuPageLHSHeaderText() {
		return(menuPageLHSHeader.getText());
	}
	
	public String getMenuPageMenuCategoryOneText() {
		return(menuPageMenuCategoryOne.getText());
	}
	
	public String getMenuPageMenuCategoryTwoText() {
		return(menuPageMenuCategoryTwo.getText());
	}
	
	public ArrayList<String> getMenuPageMenuItemNames() {
		ArrayList<String> menuItemNames = new ArrayList<String>(); 
		for(WebElement e: menuPageMenuItemName) {
			menuItemNames.add(e.getText());
		}
		return(menuItemNames);
	}
	
	public ArrayList<String> getMenuPageMenuItemFullText(){
		ArrayList<String> menuItemFullTexts = new ArrayList<String>(); 
		for(WebElement e: menuPageMenuItemFullText) {
			menuItemFullTexts.add(e.getText());
		}
		return(menuItemFullTexts);
	}
}
