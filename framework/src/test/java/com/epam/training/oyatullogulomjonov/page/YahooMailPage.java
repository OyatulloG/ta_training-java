package com.epam.training.oyatullogulomjonov.page;

import com.epam.training.oyatullogulomjonov.model.Mail;
import com.epam.training.oyatullogulomjonov.util.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class YahooMailPage extends AbstractPage {   
    private final Logger logger = LogManager.getRootLogger();
    private final String YAHOOMAIL = "sampleonesample@yahoo.com";
    
    @FindBy(xpath = "//*[@data-test-id='compose-button']")
    private WebElement composeButton;
    
    @FindBy(id = "ybarAccountProfile")
    private WebElement accountProfileButton;
    
    private By profileSignOutButtonBy = By.id("profile-signout-link");
    private By mailToTextBoxBy = By.id("message-to-field");

    @FindBy(xpath = "//*[@data-test-id='compose-subject']")
    private WebElement mailSubjectTextBox;

    @FindBy(xpath = "//*[@data-test-id='rte']/div")
    private WebElement mailContentTextBox;
    
    @FindBy(xpath = "//*[@data-test-id='compose-send-button']")
    private WebElement sendMailButton;

    private By mailSentMessageTextBoxBy = By.xpath("//*[@data-test-id='navigate-button']/parent::span");
    private By unreadMailBy = By.xpath("//a[@data-test-read='false']");
    private By unreadMailFromTextBoxBy = By.xpath("//*[@data-test-id='message-from']");
    
    @FindBy(xpath = "//*[@data-test-id='message-view-body-content']")
    private WebElement unreadMailContentTextBox;
            
    public YahooMailPage(WebDriver driver) {
      super(driver);
      PageFactory.initElements(this.driver, this);
    }
    
    public boolean isPageOpen() {      
      return driverWaitForVisibilityOf(composeButton, WAIT_TIMEOUT_SECONDS).isDisplayed();
    }

    public void logOut() {
      accountProfileButton.click();
      driverWaitForElementToBeClickable(profileSignOutButtonBy, WAIT_TIMEOUT_SECONDS).click();
    }   

    public YahooMailPage sendNewMail(Mail mail) {
      composeButton.click();
      driverWaitForElementToBeClickable(mailToTextBoxBy, WAIT_TIMEOUT_SECONDS).sendKeys(mail.getMailTo());
      mailSubjectTextBox.sendKeys(mail.getSubject());
      mailContentTextBox.sendKeys(mail.getContent());
      sendMailButton.click();
      logger.info("New Mail is sent");
      return this;
    }
    
    public boolean isMailSent() {
      return driverWaitForPresenceOfElementLocated(mailSentMessageTextBoxBy, WAIT_TIMEOUT_SECONDS).isDisplayed();
    }
    
    public Mail getMailWithSubject(String subjectOfMail) {
      if (!isUnreadMailWithSubjectClicked(subjectOfMail)) {
	return null;
      }      
      
      WebElement unreadMailFromTextBox = driverWaitForPresenceOfElementLocated(unreadMailFromTextBoxBy, WAIT_TIMEOUT_SECONDS);
      String mailEmail = StringUtils.extractEmailForYahoo(unreadMailFromTextBox.getText());
      String mailContent = StringUtils.extractMailContentForYahoo(unreadMailContentTextBox.getText());
      return new Mail(mailEmail, YAHOOMAIL, subjectOfMail, mailContent);
    }
    
    private boolean isUnreadMailWithSubjectClicked(String subjectOfMail) {
      List<WebElement> unreadMails = getUnreadMails();
      for (WebElement unreadMail : unreadMails) {
        if (unreadMail.getAttribute("aria-label").contains(subjectOfMail)) {
          unreadMail.click();
          return true;
        }
      }
      return false;
    }
    
    private List<WebElement> getUnreadMails() {
      return driver.findElements(unreadMailBy);
    }
}
