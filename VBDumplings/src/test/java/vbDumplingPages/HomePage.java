package vbDumplingPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import vbDumplingCommonMethods.CommonMethods;

public class HomePage {
	
	WebDriver driver;
	CommonMethods objCommonMethods;
	
	@FindBy(xpath = "//h1") WebElement homePageTitle;
	@FindBy(xpath = "//h1/a") WebElement homePageLink;
	@FindBy(xpath = "//p[@class='site-description']") WebElement homePageDescription;
	@FindBy(xpath = "//a[text()='Home']") WebElement menuHomeLink;
	@FindBy(xpath = "//a[text()='Menu']") WebElement menuMenuLink;
	@FindBy(xpath = "//a[text()='FAQs']") WebElement menuFAQLink;
	@FindBy(xpath = "//a[text()='Testimonials']") WebElement menuTestimonialsLink;
	@FindBy(xpath = "//a[text()='Order']") WebElement menuOrderLink;
	@FindBy(xpath = "//a[text()='About']") WebElement menuAboutLink;
	@FindBy(xpath = "//a[text()='order now']") WebElement bodyOrderNowLink;
	@FindBy(xpath = "//h2[@class='entry-title']") WebElement homeLHSHeader;
	@FindBy(xpath = "//h3[@id='ff-title-root']") WebElement orderPageHeader;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		objCommonMethods = new CommonMethods(driver);
		PageFactory.initElements(driver, this);
	}
	
	public String getHeaderText() {
		return(objCommonMethods.getWebElementText(homePageTitle));
	}
	
	public String getLHSHeaderText() {
		return(objCommonMethods.getWebElementText(homeLHSHeader));
	}
	
	public void clickHomePageLink() {
		objCommonMethods.clickWebElement(homePageLink);
	}
	
	public String getDescriptionText() {
		return(objCommonMethods.getWebElementText(homePageDescription));
	}
	
	public void clickMenuHomeLink() {
		objCommonMethods.clickWebElement(menuHomeLink);
	}
	
	public void clickMenuMenuLink() {
		objCommonMethods.clickWebElement(menuMenuLink);
	}
	
	public void clickMenuFAQLink() {
		objCommonMethods.clickWebElement(menuFAQLink);
	}
	
	public void clickMenuTestimonialsLink() {
		objCommonMethods.clickWebElement(menuTestimonialsLink);
	}
	
	public void clickMenuOrderLink() {
		objCommonMethods.clickWebElement(menuOrderLink);
	}
	
	public void clickMenuAboutLink() {
		objCommonMethods.clickWebElement(menuAboutLink);
	}
	
	public void clickBodyOrderNowLink() {
		objCommonMethods.clickWebElement(bodyOrderNowLink);
	}
	
	public String getPageTitle() {
		return(driver.getTitle());
	}
	
	public void verifyOrderPageHeader(String originalWindow,String expectedOrderPageHeader) {
		//Loop through until we find a new window handle
		for (String windowHandle : driver.getWindowHandles()) {
			if(!originalWindow.contentEquals(windowHandle)) {
		        driver.switchTo().window(windowHandle);
		        break;
		    }
		}
		try {
			Assert.assertEquals(orderPageHeader.getText(),expectedOrderPageHeader);
		}
		catch(AssertionError ae) {
			objCommonMethods.consoleLogger("OrderPage: Header Verification", "Fail", ae.getMessage());
		}
		driver.close();
		driver.switchTo().window(originalWindow);
	}
}
