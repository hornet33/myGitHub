package vbDumplingTestRunners;

import org.testng.annotations.Test;
import vbDumplingCommonMethods.CommonMethods;
import vbDumplingPages.TestimonialsPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class testTestimonialsPage {

    WebDriver driver;
    TestimonialsPage objTestimonialsPage;
    CommonMethods objCommonMethods;
    String consoleTestName;
    String consolePageName = "T E S T I M O N I A L S   P A G E";
    Integer initialCommentsCount;
    String sampleCommentText;

    final String expectedLHSHeaderText = "Testimonials";
    final String sampleCommentEmail = "rahulautomationemail2021@gmail.com";
    final String sampleCommentAuthor = "Rahul Selenium";
    final String sampleCommentURL = "https://github.com";
    final String expectedNoEmailErrorText = "Please enter your email address here";
    final String expectedNoAuthorErrorText = "Please enter your name here";
    final String expectedPendingModerationText = "Your comment is awaiting moderation. This is a preview; your comment will be visible after it has been approved.";
    final String expectedDuplicateCommentErrorText = "Duplicate comment detected; it looks as though you’ve already said that!";
    final String expectedCommentSubmissionFailTitle = "Comment Submission Failure";
    final String expectedWordpressConnectionText = "Connecting to WordPress.com";
    final String expectedTwitterConnectionText = "Connecting to Twitter";
    final String expectedFacebookConnectionText = "Connecting to Facebook";

    @BeforeClass
    @Parameters({"browserType", "vbdURL"})
    public void initDriver(String browserType, String vbdURL) {
        //Create a new driver instance for the browserType specified in testng.xml
        initializeDriver objDriver = new initializeDriver();
        driver = objDriver.testSetup(browserType);

        //Initialize CommonMethods class
        objCommonMethods = new CommonMethods(driver);
        objCommonMethods.consoleLogger(consolePageName, true, false);
        objCommonMethods.consoleLogger("WebDriver Initialized ('" + browserType + "')");

        //Launch AUT
        driver.get(vbdURL);

        //Initialize Testimonials Page POM class
        objTestimonialsPage = new TestimonialsPage(driver);

        //Navigate to Testimonials Page
        objTestimonialsPage.clickTestimonialsLink();

        //Store the initial comments count - will be used for cross-verification of comments count in later tests
        initialCommentsCount = objTestimonialsPage.getCommentsAsWebelements().size();
    }

    @Test(priority = 40)
    public void checkLHSHeaderText() {
        consoleTestName = "LHS Header Text";
        try {
            Assert.assertEquals(objTestimonialsPage.getLHSHeaderText().toUpperCase(), expectedLHSHeaderText.toUpperCase());
            objCommonMethods.consoleLogger(consoleTestName, "Pass");
        } catch (AssertionError ae) {
            objCommonMethods.consoleLogger(consoleTestName, "Fail", ae.getMessage());
            Assert.fail(ae.getMessage());
        }
    }

    @Test(priority = 41)
    public void checkCommentsTitle() {
        consoleTestName = "Comments Count In Comments Header";
        String actualCommentsTitleText = objTestimonialsPage.getCommentsHeaderText();
        Integer actualCommentsCount = Integer.valueOf(actualCommentsTitleText.substring(0, actualCommentsTitleText.indexOf("Replies")).trim());
        Integer totalCommentsCount = objTestimonialsPage.getCommentsAsWebelements().size();
        if (actualCommentsCount.equals(totalCommentsCount)) {
            Assert.assertTrue(true);
            objCommonMethods.consoleLogger(consoleTestName, "Pass", actualCommentsCount + "/" + totalCommentsCount);
        } else {
            String errorMsg = "Comments count mismatch - header = '" + actualCommentsCount + "', total comments = '" + totalCommentsCount + "'";
            objCommonMethods.consoleLogger(consoleTestName, "Fail", errorMsg);
            Assert.fail(errorMsg);
        }
    }

    @Test(priority = 42)
    public void checkNewCommentErrorAllEmpty() {
        consoleTestName = "New Comment Error - All Mandatory Fields Empty";
        sampleCommentText = objCommonMethods.appendRandomIntToString("This is a test comment using Selenium Webdriver!");
        objTestimonialsPage.setNewCommentText(sampleCommentText);
        objTestimonialsPage.clearNewCommentEmail();
        objTestimonialsPage.clearNewCommentAuthor();
        objTestimonialsPage.clearNewCommentURL();
        objTestimonialsPage.clickNewCommentSubmitBtn();
        Integer totalCommentsCount = objTestimonialsPage.getCommentsAsWebelements().size();
        if (
                objTestimonialsPage.getNoMailErrorText().equals(expectedNoEmailErrorText) &&
                        objTestimonialsPage.getNoAuthorErrorText().equals(expectedNoAuthorErrorText) &&
                        initialCommentsCount.equals(totalCommentsCount)
        ) {
            Assert.assertTrue(true);
            objCommonMethods.consoleLogger(consoleTestName, "Pass");
        } else {
            String errorMsg = "Error message not displayed as expected";
            objCommonMethods.consoleLogger(consoleTestName, "Fail", errorMsg);
            Assert.fail(errorMsg);
        }
    }

    @Test(priority = 43)
    public void checkNewCommentErrorEmailEmpty() {
        consoleTestName = "New Comment Error - Email Field Empty";
        sampleCommentText = objCommonMethods.appendRandomIntToString("This is a test comment using Selenium Webdriver!");
        objTestimonialsPage.setNewCommentText(sampleCommentText);
        objTestimonialsPage.clearNewCommentEmail();
        objTestimonialsPage.setNewCommentAuthor(sampleCommentAuthor);
        objTestimonialsPage.clearNewCommentURL();
        objTestimonialsPage.clickNewCommentSubmitBtn();
        Integer totalCommentsCount = objTestimonialsPage.getCommentsAsWebelements().size();
        if (objTestimonialsPage.getNoMailErrorText().equals(expectedNoEmailErrorText) && initialCommentsCount.equals(totalCommentsCount)) {
            Assert.assertTrue(true);
            objCommonMethods.consoleLogger(consoleTestName, "Pass");
        } else {
            String errorMsg = "Error message not displayed as expected";
            objCommonMethods.consoleLogger(consoleTestName, "Fail", errorMsg);
            Assert.fail(errorMsg);
        }
    }

    @Test(priority = 44)
    public void checkNewCommentErrorAuthorEmpty() {
        consoleTestName = "New Comment Error - Author Field Empty";
        sampleCommentText = objCommonMethods.appendRandomIntToString("This is a test comment using Selenium Webdriver!");
        objTestimonialsPage.setNewCommentText(sampleCommentText);
        objTestimonialsPage.setNewCommentEmail(sampleCommentEmail);
        objTestimonialsPage.clearNewCommentAuthor();
        objTestimonialsPage.clearNewCommentURL();
        objTestimonialsPage.clickNewCommentSubmitBtn();
        Integer totalCommentsCount = objTestimonialsPage.getCommentsAsWebelements().size();
        if (objTestimonialsPage.getNoAuthorErrorText().equals(expectedNoAuthorErrorText) && initialCommentsCount.equals(totalCommentsCount)) {
            Assert.assertTrue(true);
            objCommonMethods.consoleLogger(consoleTestName, "Pass");
        } else {
            String errorMsg = "Error message not displayed as expected";
            objCommonMethods.consoleLogger(consoleTestName, "Fail", errorMsg);
            Assert.fail(errorMsg);
        }
    }

    @Test(priority = 45)
    public void checkNewCommentSuccessNoNotificationsSelected() {
        consoleTestName = "New Comment Success - No notifications Selected";
        sampleCommentText = objCommonMethods.appendRandomIntToString("This is a test comment using Selenium Webdriver!");
        objCommonMethods.consoleLogger(consoleTestName, sampleCommentText);

        objTestimonialsPage.setNewCommentText(sampleCommentText);
        objTestimonialsPage.setNewCommentEmail(sampleCommentEmail);
        objTestimonialsPage.setNewCommentAuthor(sampleCommentAuthor);
        objTestimonialsPage.setNewCommentURL(sampleCommentURL);
        objTestimonialsPage.disableSubscribeCommentsChkbox();
        objTestimonialsPage.disableSubscribePostsChkbox();
        objTestimonialsPage.clickNewCommentSubmitBtn();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        int totalCommentsCount = objTestimonialsPage.getCommentsAsWebelements().size();
        String actualPendingModerationText = objTestimonialsPage.getCommentPendingModerationText();
        //New comment for pending moderation should be added to the bottom of the initial comments list
        if (actualPendingModerationText.trim().equals(expectedPendingModerationText.trim())
                && Integer.valueOf(initialCommentsCount + 1).equals(totalCommentsCount)
                && objTestimonialsPage.getCommentsAsWebelements().get(totalCommentsCount - 1).getText().equals(sampleCommentText)
        ) {
            Assert.assertTrue(true);
            objCommonMethods.consoleLogger(consoleTestName, "Pass");
        } else {
            String errorMsg = "Pending moderation text message not displayed as expected";
            objCommonMethods.consoleLogger(consoleTestName, "Fail", errorMsg);
            Assert.fail(errorMsg);
        }
    }

    @Test(priority = 46)
    public void checkNewCommentErrorDuplicateComment() {
        consoleTestName = "New Comment Error - Duplicate Comment";
        objTestimonialsPage.setNewCommentText(sampleCommentText); //Using the same comment text
        objCommonMethods.consoleLogger(consoleTestName, sampleCommentText);

        objTestimonialsPage.setNewCommentEmail(sampleCommentEmail);
        objTestimonialsPage.setNewCommentAuthor(sampleCommentAuthor);
        objTestimonialsPage.setNewCommentURL(sampleCommentURL);
        objTestimonialsPage.disableSubscribeCommentsChkbox();
        objTestimonialsPage.disableSubscribePostsChkbox();
        objTestimonialsPage.clickNewCommentSubmitBtn();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        if (objTestimonialsPage.getDuplicateCommentErrorText().trim().equals(expectedDuplicateCommentErrorText)
                && objTestimonialsPage.getPageTitle().trim().equals(expectedCommentSubmissionFailTitle)
        ) {
            Assert.assertTrue(true);
            objCommonMethods.consoleLogger(consoleTestName, "Pass");
            //Navigate to main testimonials page from duplicate comment page
            objTestimonialsPage.clickDuplicateCommentBackLink();
        } else {
            String errorMsg = "Duplicate comment error message not displayed as expected";
            objCommonMethods.consoleLogger(consoleTestName, "Fail", errorMsg);
            Assert.fail(errorMsg);
        }
    }

    @Test(priority = 46)
    public void checkNewCommentSuccessSubscribeCommentsSelected() {
        consoleTestName = "New Comment Success - Subscribe Comments Selected";
        sampleCommentText = objCommonMethods.appendRandomIntToString("This is a test comment using Selenium Webdriver!");
        objCommonMethods.consoleLogger(consoleTestName, sampleCommentText);

        objTestimonialsPage.setNewCommentText(sampleCommentText);
        objTestimonialsPage.setNewCommentEmail(sampleCommentEmail);
        objTestimonialsPage.setNewCommentAuthor(sampleCommentAuthor);
        objTestimonialsPage.setNewCommentURL(sampleCommentURL);
        objTestimonialsPage.enableSubscribeCommentsChkbox();
        objTestimonialsPage.disableSubscribePostsChkbox();
        objTestimonialsPage.clickNewCommentSubmitBtn();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        int totalCommentsCount = objTestimonialsPage.getCommentsAsWebelements().size();
        String actualPendingModerationText = objTestimonialsPage.getCommentPendingModerationText();
        //New comment for pending moderation should be added to the bottom of the initial comments list
        if (actualPendingModerationText.trim().equals(expectedPendingModerationText.trim())
                && Integer.valueOf(initialCommentsCount + 1).equals(totalCommentsCount)
                && objTestimonialsPage.getCommentsAsWebelements().get(totalCommentsCount - 1).getText().equals(sampleCommentText)
        ) {
            Assert.assertTrue(true);
            objCommonMethods.consoleLogger(consoleTestName, "Pass");
        } else {
            String errorMsg = "Pending moderation text message not displayed as expected";
            objCommonMethods.consoleLogger(consoleTestName, "Fail", errorMsg);
            Assert.fail(errorMsg);
        }
    }

    @Test(priority = 46)
    public void checkNewCommentSuccessSubscribePostsSelected() {
        consoleTestName = "New Comment Success - Subscribe Posts Selected";
        sampleCommentText = objCommonMethods.appendRandomIntToString("This is a test comment using Selenium Webdriver!");
        objCommonMethods.consoleLogger(consoleTestName, sampleCommentText);

        objTestimonialsPage.setNewCommentText(sampleCommentText);
        objTestimonialsPage.setNewCommentEmail(sampleCommentEmail);
        objTestimonialsPage.setNewCommentAuthor(sampleCommentAuthor);
        objTestimonialsPage.setNewCommentURL(sampleCommentURL);
        objTestimonialsPage.disableSubscribeCommentsChkbox();
        objTestimonialsPage.enableSubscribePostsChkbox();
        objTestimonialsPage.clickNewCommentSubmitBtn();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        int totalCommentsCount = objTestimonialsPage.getCommentsAsWebelements().size();
        String actualPendingModerationText = objTestimonialsPage.getCommentPendingModerationText();
        //New comment for pending moderation should be added to the bottom of the initial comments list
        if (actualPendingModerationText.trim().equals(expectedPendingModerationText.trim())
                && Integer.valueOf(initialCommentsCount + 1).equals(totalCommentsCount)
                && objTestimonialsPage.getCommentsAsWebelements().get(totalCommentsCount - 1).getText().equals(sampleCommentText)
        ) {
            Assert.assertTrue(true);
            objCommonMethods.consoleLogger(consoleTestName, "Pass");
        } else {
            String errorMsg = "Pending moderation text message not displayed as expected";
            objCommonMethods.consoleLogger(consoleTestName, "Fail", errorMsg);
            Assert.fail(errorMsg);
        }
    }

    @Test(priority = 47)
    public void checkWordpressLink() {
        consoleTestName = "External Connection - Wordpress";

        //Store the ID of the original window
        String originalWindow = driver.getWindowHandle();

        //Need to set some text in text area first
        sampleCommentText = objCommonMethods.appendRandomIntToString("This is a test comment using Selenium Webdriver!");
        objCommonMethods.consoleLogger(consoleTestName, sampleCommentText);
        objTestimonialsPage.setNewCommentText(sampleCommentText);

        //Click on external connection link
        objTestimonialsPage.clickWordpressLink();

        //Switch to old window to verify fields are disabled and external connection message is displayed
        driver.switchTo().window(originalWindow);

        if (objTestimonialsPage.getExternalConnectionInfoText().equals(expectedWordpressConnectionText)) {
            //Verify elements in new popup
            if (objTestimonialsPage.checkExternalConnectionUsernamePresent(originalWindow, "Wordpress")) {
                Assert.assertTrue(true);
                objCommonMethods.consoleLogger(consoleTestName, "Pass");
            } else {
                String errorMsg = "Element was expected, but was not found";
                objCommonMethods.consoleLogger(consoleTestName, "Fail", errorMsg);
                Assert.fail(errorMsg);
            }
        } else {
            String errorMsg = "Expected text '" + expectedWordpressConnectionText + "' not found";
            objCommonMethods.consoleLogger(consoleTestName, "Fail", errorMsg);
            Assert.fail(errorMsg);
        }
    }

    @Test(priority = 47)
    public void checkTwitterLink() {
        consoleTestName = "External Connection - Twitter";

        //Store the ID of the original window
        String originalWindow = driver.getWindowHandle();

        //Need to set some text in text area first
        sampleCommentText = objCommonMethods.appendRandomIntToString("This is a test comment using Selenium Webdriver!");
        objCommonMethods.consoleLogger(consoleTestName, sampleCommentText);
        objTestimonialsPage.setNewCommentText(sampleCommentText);

        //Click on external connection link
        objTestimonialsPage.clickTwitterLink();

        //Switch to old window to verify fields are disabled and external connection message is displayed
        driver.switchTo().window(originalWindow);

        if (objTestimonialsPage.getExternalConnectionInfoText().equals(expectedTwitterConnectionText)) {
            //Verify elements in new popup
            if (objTestimonialsPage.checkExternalConnectionUsernamePresent(originalWindow, "Twitter")) {
                Assert.assertTrue(true);
                objCommonMethods.consoleLogger(consoleTestName, "Pass");
            } else {
                String errorMsg = "Element was expected, but was not found";
                objCommonMethods.consoleLogger(consoleTestName, "Fail", errorMsg);
                Assert.fail(errorMsg);
            }
        } else {
            String errorMsg = "Expected text '" + expectedTwitterConnectionText + "' not found";
            objCommonMethods.consoleLogger(consoleTestName, "Fail", errorMsg);
            Assert.fail(errorMsg);
        }
    }

    @Test(priority = 47)
    public void checkFacebookLink() {
        consoleTestName = "External Connection - Facebook";

        //Store the ID of the original window
        String originalWindow = driver.getWindowHandle();

        //Need to set some text in text area first
        sampleCommentText = objCommonMethods.appendRandomIntToString("This is a test comment using Selenium Webdriver!");
        objCommonMethods.consoleLogger(consoleTestName, sampleCommentText);
        objTestimonialsPage.setNewCommentText(sampleCommentText);

        //Click on external connection link
        objTestimonialsPage.clickFacebookLink();

        //Switch to old window to verify fields are disabled and external connection message is displayed
        driver.switchTo().window(originalWindow);

        if (objTestimonialsPage.getExternalConnectionInfoText().equals(expectedFacebookConnectionText)) {
            //Verify elements in new popup
            if (objTestimonialsPage.checkExternalConnectionUsernamePresent(originalWindow, "Facebook")) {
                Assert.assertTrue(true);
                objCommonMethods.consoleLogger(consoleTestName, "Pass");
            } else {
                String errorMsg = "Element was expected, but was not found";
                objCommonMethods.consoleLogger(consoleTestName, "Fail", errorMsg);
                Assert.fail(errorMsg);
            }
        } else {
            String errorMsg = "Expected text '" + expectedFacebookConnectionText + "' not found";
            objCommonMethods.consoleLogger(consoleTestName, "Fail", errorMsg);
            Assert.fail(errorMsg);
        }
    }

    @Test(priority = 48)
    public void checkExternalConnectionCancelLink() {
        consoleTestName = "External Connection - Cancel Link";

        //Store the ID of the original window
        String originalWindow = driver.getWindowHandle();
        objCommonMethods.consoleLogger(consoleTestName, "Original Window Handle", originalWindow);

        //Need to set some text in text area first
        sampleCommentText = objCommonMethods.appendRandomIntToString("This is a test comment using Selenium Webdriver!");
        objCommonMethods.consoleLogger(consoleTestName, sampleCommentText);
        objTestimonialsPage.setNewCommentText(sampleCommentText);

        //Click on external connection link
        objTestimonialsPage.clickFacebookLink();
        objCommonMethods.switchToNewTab(originalWindow);
        String newWindowHandle = driver.getWindowHandle();
        objCommonMethods.consoleLogger(consoleTestName, "New Window Handle", newWindowHandle);

        //Switch to old window to verify fields are disabled and external connection message is displayed
        driver.switchTo().window(originalWindow);

        if (objTestimonialsPage.getExternalConnectionInfoText().equals(expectedFacebookConnectionText)) {
            //Click on the cancel link
            objTestimonialsPage.clickExternalConnectionCancelLink();

            for (String windowHandle : driver.getWindowHandles()) {
                if (newWindowHandle.contentEquals(windowHandle)) {
                    String errorMsg = "Expected windowHandle '" + windowHandle + "' not closed";
                    objCommonMethods.consoleLogger(consoleTestName, "Fail", errorMsg);
                    Assert.fail(errorMsg);
                }
            }
            Assert.assertTrue(true);
            objCommonMethods.consoleLogger(consoleTestName, "Pass");
        }
    }

    @AfterClass
    public void testClose() {
        driver.quit();
        objCommonMethods.consoleLogger(consolePageName, false, true);
    }
}
