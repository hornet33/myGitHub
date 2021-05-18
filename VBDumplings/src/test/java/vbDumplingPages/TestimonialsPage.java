package vbDumplingPages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import vbDumplingCommonMethods.CommonMethods;

public class TestimonialsPage {
	
	WebDriver driver;
	CommonMethods objCommonMethods;
	
	@FindBy(xpath = "//a[text()='Testimonials']") WebElement menuTestimonialsLink;
	@FindBy(xpath = "//h1[@class='entry-title']") WebElement testimonialsPageLHSHeader;
	@FindBy(xpath = "//h2[@class='comments-title']") WebElement testimonialsPageCommentsHeader;
	@FindBy(xpath = "//div[@class='comment-content']/p") List<WebElement> testimonialsPageCommentsContent;
	@FindBy(xpath = "//textarea[@id='comment']") WebElement testimonialsPageNewCommentTextarea;
	@FindBy(xpath = "//a[@id='postas-wordpress']") WebElement testimonialsPagePostAsWordpressLink;
	@FindBy(xpath = "//a[@id='postas-twitter']") WebElement testimonialsPagePostAsTwitterLink;
	@FindBy(xpath = "//a[@id='postas-facebook']") WebElement testimonialsPagePostAsFacebookLink;
	@FindBy(xpath = "//input[@id='email']") WebElement testimonialsPageNewCommentEmail;
	@FindBy(xpath = "//input[@id='author']") WebElement testimonialsPageNewCommentAuthor;
	@FindBy(xpath = "//div[@class='comment-form-field comment-form-email']/label[@class='error']") WebElement testimonialsPageNoEmailError;
	@FindBy(xpath = "//div[@class='comment-form-field comment-form-author']/label[@class='error']") WebElement testimonialsPageNoAuthorError;
	@FindBy(xpath = "//input[@id='url']") WebElement testimonialsPageNewCommentURL;
	@FindBy(xpath = "//input[@id='comment-submit']") WebElement testimonialsPageNewCommentSubmitButton;
	@FindBy(xpath = "//input[@id='subscribe']") WebElement testimonialsPageSubscribeCommentsChkbox;
	@FindBy(xpath = "//input[@id='subscribe_blog']") WebElement testimonialsPageSubscribePostsChkbox;
	@FindBy(xpath = "//em[@class='comment-awaiting-moderation']") WebElement testimonialsPageCommentPendingModeration;
	
	public TestimonialsPage(WebDriver driver) {
		this.driver = driver;
		objCommonMethods =  new CommonMethods(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void clickTestimonialsLink(){
		objCommonMethods.clickWebElement(menuTestimonialsLink);
	}
	
	public String getLHSHeaderText() {
		return(objCommonMethods.getWebElementText(testimonialsPageLHSHeader));
	}
	
	public String getCommentsHeaderText() {
		return(objCommonMethods.getWebElementText(testimonialsPageCommentsHeader));
	}
	
	public List<WebElement> getCommentsAsWebelements(){
		return(testimonialsPageCommentsContent);
	}
	
	public void setNewCommentText(String commentText) {
		objCommonMethods.setWebElementText(testimonialsPageNewCommentTextarea,commentText);
	}
	
	public void setNewCommentEmail(String commentEmail) {
		objCommonMethods.setWebElementText(testimonialsPageNewCommentEmail, commentEmail);
	}
	
	public void setNewCommentAuthor(String commentAuthor) {
		objCommonMethods.setWebElementText(testimonialsPageNewCommentAuthor, commentAuthor);
	}
	
	public void setNewCommentURL(String commentURL) {
		objCommonMethods.setWebElementText(testimonialsPageNewCommentURL, commentURL);
	}
	
	public void clickWordpressLink() {
		objCommonMethods.clickWebElement(testimonialsPagePostAsWordpressLink);
	}
	
	public void clickTwitterLink() {
		objCommonMethods.clickWebElement(testimonialsPagePostAsTwitterLink);
	}
	
	public void clickFacebookLink() {
		objCommonMethods.clickWebElement(testimonialsPagePostAsFacebookLink);
	}	
	
	public void clickNewCommentSubmitBtn() {
		objCommonMethods.clickWebElement(testimonialsPageNewCommentSubmitButton);
	}
	
	public void enableSubscribeCommentsChkbox() {
		objCommonMethods.enableCheckbox(testimonialsPageSubscribeCommentsChkbox);
	}
	
	public void enableSuscribePostsChkbox() {
		objCommonMethods.enableCheckbox(testimonialsPageSubscribePostsChkbox);
	}
	
	public String getNoMailErrorText() {
		return(objCommonMethods.getWebElementText(testimonialsPageNoEmailError));
	}
	
	public String getNoAuthorErrorText() {
		return(objCommonMethods.getWebElementText(testimonialsPageNoAuthorError));
	}
	
	public String getCommentPendingModerationText() {
		return(objCommonMethods.getWebElementText(testimonialsPageCommentPendingModeration));
	}
}
