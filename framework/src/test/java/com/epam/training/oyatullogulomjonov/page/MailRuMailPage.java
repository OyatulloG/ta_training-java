package com.epam.training.oyatullogulomjonov.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.List;

public class MailRuMailPage extends AbstractPage {    
    public final String MAILRU = "sampletwosample@mail.ru";

    private By composeButtonBy = By.xpath("//*[@href='/compose/']");
    private By accountButtonBy = By.xpath("//*[@data-testid='whiteline-account']");    
    private By accountLogoutButtonBy = By.xpath("//*[@data-testid='whiteline-account-exit']");    
    private By mailToTextBoxBy = By.xpath("//*[@data-type='to']//input");
    private By mailSubjectTextBoxBy = By.name("Subject");    
    private By mailContentTextBoxBy = By.xpath("//*[@role='textbox']/div");            
    private By sendMailButtonBy = By.xpath("//*[@data-test-id='send']");
    private By mailSentMessageTextBoxBy = By.className("layer-sent-page");  
    private By mailSentMessageCloseButtonBy = By.xpath("//*[@class='layer-sent-page']//span");
    private By unreadMailBy = By.xpath("//*[contains(@class, 'subject_unread')]");
    private By unreadMailFromTextBoxBy = By.xpath("//*[@class='letter__author']//*[contains(@class,'letter-contact')]");
    private By unreadMailContentTextBoxBy = By.className("letter-body");
            
    public MailRuMailPage(WebDriver driver) {
      super(driver);
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
      driverWaitForElementToBeClickable(mailSubjectTextBoxBy, WAIT_TIMEOUT_SECONDS).sendKeys(mailSubject);
      return this;
    }
    
    public MailRuMailPage sendKeysToMailContentTextBox(String mailContent) {
      WebElement mailContentTextBox = driverWaitForElementToBeClickable(mailContentTextBoxBy, WAIT_TIMEOUT_SECONDS);
      new Actions(driver)
      		.sendKeys(mailContentTextBox, mailContent)
      		.perform();
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
      WebElement unreadMailContentTextBox = driverWaitForPresenceOfElementLocated(unreadMailContentTextBoxBy,
      											WAIT_TIMEOUT_SECONDS);
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
