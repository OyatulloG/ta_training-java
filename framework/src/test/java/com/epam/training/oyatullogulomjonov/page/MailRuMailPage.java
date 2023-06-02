package com.epam.training.oyatullogulomjonov.page;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class MailRuMailPage extends AbstractPage {    
    private final Logger logger = LogManager.getRootLogger();
    public final String MAILRU = "sampletwosample@mail.ru";

    private By composeButtonBy = By.xpath("//*[@href='/compose/']");
    private By accountButtonBy = By.xpath("//*[@data-testid='whiteline-account']");    
    private By accountLogoutButtonBy = By.xpath("//*[@data-testid='whiteline-account-exit']");    
    private By mailToTextBoxBy = By.xpath("//*[@data-type='to']//input");

    @FindBy(name = "Subject")
    private WebElement mailSubjectTextBox;
    
    @FindBy(xpath = "//*[@role='textbox']//div")
    private WebElement mailContentTextBox;
        
    private By sendMailButtonBy = By.xpath("//*[@data-test-id='send']");
    private By mailSentMessageTextBoxBy = By.className("layer-sent-page");  
    private By mailSentMessageCloseButtonBy = By.xpath("//*[@class='layer-sent-page']//span");
    private By unreadMailBy = By.xpath("//*[contains(@class, 'subject_unread')]");
    private By unreadMailFromTextBoxBy = By.xpath("//*[@class='letter__author']//*[contains(@class,'letter-contact')]");
            
    @FindBy(className = "letter-body")
    private WebElement unreadMailContentTextBox;
            
    public MailRuMailPage(WebDriver driver) {
      super(driver);
      PageFactory.initElements(this.driver, this);
    }
    
    public boolean isPageOpen() {
      return driverWaitForPresenceOfElementLocated(composeButtonBy, WAIT_TIMEOUT_SECONDS).isDisplayed();
    }    
	    
    public MailRuMailPage clickAccountButton() {
      driverWaitForElementToBeClickable(accountButtonBy, WAIT_TIMEOUT_SECONDS).click();
      return this;      
    }
    
    public MailRuLoginPage clickAccountLogoutButton() {
      driverWaitForElementToBeClickable(accountLogoutButtonBy, WAIT_TIMEOUT_SECONDS).click();
      return new MailRuLoginPage(driver);
    }
    
    public MailRuMailPage clickComposeButton() {
      driverWaitForElementToBeClickable(composeButtonBy, WAIT_TIMEOUT_SECONDS).click();
      return this;
    }
    
    public MailRuMailPage sendKeysToMailToTextBox(String mailTo) {
      driverWaitForElementToBeClickable(mailToTextBoxBy, WAIT_TIMEOUT_SECONDS).sendKeys(mailTo);
      return this;
    }
    
    public MailRuMailPage sendKeysToMailSubjectTextBox(String mailSubject) {
      mailSubjectTextBox.sendKeys(mailSubject);
      return this;
    }
    
    public MailRuMailPage sendKeysToMailContentTextBox(String mailContent) {
      mailContentTextBox.sendKeys(mailContent);
      return this;
    }
    
    public MailRuMailPage clickSendMailButton() {
      driverWaitForElementToBeClickable(sendMailButtonBy, WAIT_TIMEOUT_SECONDS).click();
      return this;
    }
    
    public boolean isMailSent() {
      return driverWaitForPresenceOfElementLocated(mailSentMessageTextBoxBy, WAIT_TIMEOUT_SECONDS).isDisplayed();
    }
    
    public MailRuMailPage clickMailSentMessageCloseButton() {
      driverWaitForElementToBeClickable(mailSentMessageCloseButtonBy, WAIT_TIMEOUT_SECONDS).click();
      return this;
    }
        
    public MailRuMailPage clickMail(WebElement mail) {
      mail.click();
      return this;
    }       
    
    public String getMailFromText() {
      WebElement unreadMailFromTextBox = driverWaitForPresenceOfElementLocated(unreadMailFromTextBoxBy, WAIT_TIMEOUT_SECONDS);
      return unreadMailFromTextBox.getAttribute("title");    
    }
    
    public String getMailContentText() {
      return unreadMailContentTextBox.getText();     
    }
    
    public WebElement getUnreadMailWithSubject(String subjectOfMail) {
      List<WebElement> unreadMails = getUnreadMails();
      for (WebElement unreadMail : unreadMails) {
        if (unreadMail.getText().equals(subjectOfMail)) {
          return unreadMail;
        }
      }
      return null;
    }
        
    private List<WebElement> getUnreadMails() {
      driverWaitForPresenceOfElementLocated(unreadMailBy, WAIT_TIMEOUT_SECONDS);
      return driver.findElements(unreadMailBy);
    }
}
