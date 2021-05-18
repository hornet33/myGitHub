package vbDumplingCommonMethods;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods {
	
	WebDriver driver;
	
	public CommonMethods(WebDriver driver) {
		this.driver = driver;
	}
	
	//Method to check if a WebElement is visible within the specified 'timeOut' seconds
	//Returns true if visible, else returns false
	public boolean isElementVisible(WebElement webElement, int timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions.visibilityOf(webElement));
			return true;
		} 
		catch (NoSuchElementException e) {
			return false;
		}
	}
	
	//Method to click on a WebElement if it is visible 
	//Returns N/A	
	public void clickWebElement(WebElement elementToClick) {
		if(isElementVisible(elementToClick,2)) {
			elementToClick.click();
		}
	}
	
	//Method to set text on a WebElement if it is visible 
	//Returns N/A	
	public void setWebElementText(WebElement textInputElement, String textToSet) {
		if(isElementVisible(textInputElement,2)) {
			textInputElement.sendKeys(textToSet);
		}
	}
	
	//Method to get the text from a WebElement if it is visible 
	//Returns the text of the WebElement if found, else ""	
	public String getWebElementText(WebElement element){
		if(isElementVisible(element, 2)) {
			return(element.getText());
		}
		else return("");
	}
	
	//Method to scroll the view-port until the specified WebElement is visible 
	//Returns N/A	
	public void scrollUntilElementInView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}
	
	//Method to enable a check-box WebElement if it is visible and not enabled already
	//Returns N/A	
	public void enableCheckbox(WebElement checkboxToEnable) {
		if(isElementVisible(checkboxToEnable,2) && !checkboxToEnable.isEnabled()) {
			checkboxToEnable.click();
		}
	}
	
	//Method to disable a check-box WebElement if it is visible and not disabled already
	//Returns N/A		
	public void disableCheckbox(WebElement checkboxToDisable) {
		if(isElementVisible(checkboxToDisable,2) && checkboxToDisable.isEnabled()) {
			checkboxToDisable.click();
		}
	}
	
	//Method to print to the console for start or end of a test
	//Returns N/A		
	public void consoleLogger(String logText, boolean isTestStart, boolean isTestEnd) {
		String outputText = "[TEST RUN] ----------------------------- ";
		if(isTestStart) {
			outputText += "S T A R T   -   " + logText + " -----------------------------";
		}
		else if(isTestEnd) {
			outputText += "E N D   -   " + logText + " -----------------------------";
		}
		System.out.println(outputText);
	}
	
	//Method to print to the console for just plain text
	//Returns N/A		
	public void consoleLogger(String logText) {
		System.out.println("[TEST RUN] " + logText);
	}
	
	//Method to print to the console for just plain text with pass/fail result, without additional text
	//Returns N/A		
	public void consoleLogger(String logText, String actionResultText) {
		System.out.println("[TEST RUN] " + logText + " - " + actionResultText);	
	}
	
	//Method to print to the console for validation tests with pass/fail result with additional text
	//Returns N/A		
	public void consoleLogger(String logText,String actionResultText,String additionalInfoText) {
		System.out.println("[TEST RUN] " + logText + " - " + actionResultText + " (" + additionalInfoText + ")");
	}
}
