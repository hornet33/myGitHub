package vbDumplingPages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FAQPage {
	
	WebDriver driver;
	
	@FindBy(xpath = "//a[text()='FAQs']") WebElement menuFAQLink;
	@FindBy(xpath = "//h1[@class='entry-title']") WebElement faqPageLHSHeader;
	@FindBy(xpath = "//div[@class='entry-content']/h2") WebElement faqPageContentHeader;
	@FindBy(xpath = "//div[@class='entry-content']/details/summary") List<WebElement> faqPageQuestionSummaryList;
	@FindBy(xpath = "//div[@class='entry-content']/details/p/em") List<WebElement> faqPageExpandedAnswerList;
	@FindBy(xpath = "//a[contains(text(),'Order Form')]") WebElement faqPageOrderFormLink;
	
	public FAQPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getFaqPageLHSHeaderText() {
		return(faqPageLHSHeader.getText());
	}
	
	public String getFaqPageContentHeaderText() {
		return(faqPageContentHeader.getText());
	}
	
	public ArrayList<String> getFaqPageQuestions(){
		ArrayList<String> faqPageQuestions = new ArrayList<String>(); 
		for(WebElement faqPageQuestionSummary: faqPageQuestionSummaryList) {
			faqPageQuestions.add(faqPageQuestionSummary.getText());
		}
		return(faqPageQuestions);
	}
	
	public ArrayList<String> getFaqPageExpandedAnswers(){
		ArrayList<String> faqPageExpandedAnswers = new ArrayList<String>();
		for(WebElement faqPageExpandedAnswer: faqPageExpandedAnswerList) {
			faqPageExpandedAnswers.add(faqPageExpandedAnswer.getText());
		}
		return(faqPageExpandedAnswers);
	}
	
	public List<WebElement> getFaqPageQuestionsAsWebelements(){
		return(faqPageQuestionSummaryList);
	}
	
	public List<WebElement> getFaqPageExpandedAnswersAsWebelements(){
		return(faqPageExpandedAnswerList);
	}
	
	public WebElement getFaqPageContentHeaderElement() {
		return(faqPageContentHeader);
	}
	
	public void clickMenuFaqLink() {
		menuFAQLink.click();
	}
	
	public void clickFaqPageQuestion(int questionIndex) {
		faqPageQuestionSummaryList.get(questionIndex).click();
	}
	
	public boolean clickFaqPageExpandedAnswer(int answerIndex) {
		try { 
			//Change implicit wait to zero seconds temporarily
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			//Try to click the element
			faqPageExpandedAnswerList.get(answerIndex).click();			
			return(true);
		}
		catch(Exception e) {			
			return(false);
		}
		finally {
			//Change implicit wait back to 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}
	
	public boolean isElementVisible(WebElement webElement, int timeOut) {
	    try {
	    	WebDriverWait wait = new WebDriverWait(driver, timeOut);
	    	wait.until(ExpectedConditions.visibilityOf(webElement));
	    	return true;
	    } 
	    catch (org.openqa.selenium.NoSuchElementException e) {
	    	return false;
	    }
	}
	
	public void clickFaqPageOrderFormLink() {
		if(isElementVisible(faqPageOrderFormLink,2)) {
			faqPageOrderFormLink.click();
		}
	}
}
