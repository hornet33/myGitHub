package vbDumplingPages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import vbDumplingCommonMethods.CommonMethods;

public class FAQPage {
	
	WebDriver driver;
	CommonMethods objCommonMethods;
	
	@FindBy(xpath = "//a[text()='FAQs']") WebElement menuFAQLink;
	@FindBy(xpath = "//h1[@class='entry-title']") WebElement faqPageLHSHeader;
	@FindBy(xpath = "//div[@class='entry-content']/h2") WebElement faqPageContentHeader;
	@FindBy(xpath = "//div[@class='entry-content']/details/summary") List<WebElement> faqPageQuestionSummaryList;
	@FindBy(xpath = "//div[@class='entry-content']/details/p/em") List<WebElement> faqPageExpandedAnswerList;
	@FindBy(xpath = "//a[contains(text(),'Order Form')]") WebElement faqPageOrderFormLink;
	
	public FAQPage(WebDriver driver) {
		this.driver = driver;
		objCommonMethods =  new CommonMethods(driver);
		PageFactory.initElements(driver, this);
	}
	
	public String getLHSHeaderText() {
		return(objCommonMethods.getWebElementText(faqPageLHSHeader));
	}
	
	public String getContentHeaderText() {
		return(objCommonMethods.getWebElementText(faqPageContentHeader));
	}
	
	public ArrayList<String> getQuestions(){
		ArrayList<String> faqPageQuestions = new ArrayList<String>(); 
		for(WebElement faqPageQuestionSummary: faqPageQuestionSummaryList) {
			faqPageQuestions.add(objCommonMethods.getWebElementText(faqPageQuestionSummary));
		}
		return(faqPageQuestions);
	}
	
	public ArrayList<String> getExpandedAnswers(){
		ArrayList<String> faqPageExpandedAnswers = new ArrayList<String>();
		for(WebElement faqPageExpandedAnswer: faqPageExpandedAnswerList) {
			faqPageExpandedAnswers.add(objCommonMethods.getWebElementText(faqPageExpandedAnswer));
		}
		return(faqPageExpandedAnswers);
	}
	
	public List<WebElement> getQuestionsAsWebelements(){
		return(faqPageQuestionSummaryList);
	}
	
	public List<WebElement> getExpandedAnswersAsWebelements(){
		return(faqPageExpandedAnswerList);
	}
	
	public WebElement getContentHeaderElement() {
		return(faqPageContentHeader);
	}
	
	public void clickMenuFaqLink() {
		objCommonMethods.clickWebElement(menuFAQLink);
	}
	
	public void clickQuestion(int questionIndex) {
		objCommonMethods.clickWebElement(faqPageQuestionSummaryList.get(questionIndex));
	}
	
	public boolean clickExpandedAnswer(int answerIndex) {
		try { 
			//Change implicit wait to zero seconds temporarily
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			//Try to click the element
			objCommonMethods.clickWebElement(faqPageExpandedAnswerList.get(answerIndex));	
			//If able to click, return true
			return(true);
		}
		catch(Exception e) {	
			//Not able to click element, return false
			return(false);
		}
		finally {
			//Change implicit wait back to 30 seconds before exiting the method
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}
	
	public void clickOrderFormLink() {
		objCommonMethods.clickWebElement(faqPageOrderFormLink);		
	}
}
