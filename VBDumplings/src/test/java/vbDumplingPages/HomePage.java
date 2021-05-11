package vbDumplingPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
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
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getHeaderText() {
		return(homePageTitle.getText());
	}
	
	public void clickHomePageLink() {
		homePageLink.click();
	}
	
	public String getDescriptionText() {
		return(homePageDescription.getText());
	}
	
	public void clickMenuHomeLink() {
		menuHomeLink.click();
	}
	
	public void clickMenuMenuLink() {
		menuMenuLink.click();
	}
	
	public void clickMenuFAQLink() {
		menuFAQLink.click();
	}
	
	public void clickMenuTestimonialsLink() {
		menuTestimonialsLink.click();
	}
	
	public void clickMenuOrderLink() {
		menuOrderLink.click();
	}
	
	public void clickMenuAboutLink() {
		menuAboutLink.click();
	}
	
	public void clickBodyOrderNowLink() {
		bodyOrderNowLink.click();
	}
	
	public String getPageTitle() {
		return(driver.getTitle());
	}
}
