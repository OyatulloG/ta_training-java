package com.epam.training.oyatullogulomjonov.page;

import com.epam.training.oyatullogulomjonov.model.Mail;
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
    private final String MAILRU = "sampletwosample@mail.ru";

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

    public void logOut() {
      driverWaitForElementToBeClickable(accountButtonBy, WAIT_TIMEOUT_SECONDS).click();
      driverWaitForElementToBeClickable(accountLogoutButtonBy, WAIT_TIMEOUT_SECONDS).click();
    }

    public MailRuMailPage sendNewMail(Mail mail) {
      driverWaitForElementToBeClickable(composeButtonBy, WAIT_TIMEOUT_SECONDS).click();
      driverWaitForElementToBeClickable(mailToTextBoxBy, WAIT_TIMEOUT_SECONDS).sendKeys(mail.getMailTo());
      mailSubjectTextBox.sendKeys(mail.getSubject());
      mailContentTextBox.sendKeys(mail.getContent());
      driverWaitForElementToBeClickable(sendMailButtonBy, WAIT_TIMEOUT_SECONDS).click();
      logger.info("New Mail is sent");
      return this;
    }
    
    public boolean isMailSent() {
      return driverWaitForPresenceOfElementLocated(mailSentMessageTextBoxBy, WAIT_TIMEOUT_SECONDS).isDisplayed();
    }
    
    public void clickMailSentMessageCloseButton() {
      driverWaitForElementToBeClickable(mailSentMessageCloseButtonBy, WAIT_TIMEOUT_SECONDS).click();
    }
    
    public Mail getMailWithSubject(String subjectOfMail) {
      if (!isUnreadMailWithSubjectClicked(subjectOfMail)) {
        return null;
      }      

      WebElement unreadMailFromTextBox = driverWaitForPresenceOfElementLocated(unreadMailFromTextBoxBy, WAIT_TIMEOUT_SECONDS);
      String mailFrom = unreadMailFromTextBox.getAttribute("title");
      String mailContent = unreadMailContentTextBox.getText();      
      return new Mail(mailFrom, MAILRU, subjectOfMail, mailContent);      
    }
        
    private boolean isUnreadMailWithSubjectClicked(String subjectOfMail) {
      List<WebElement> unreadMails = getUnreadMails();
      for (WebElement unreadMail : unreadMails) {
        if (unreadMail.getText().equals(subjectOfMail)) {
          unreadMail.click();
          return true;
        }
      }
      return false;
    }
    
    private List<WebElement> getUnreadMails() {
      driverWaitForPresenceOfElementLocated(unreadMailBy, WAIT_TIMEOUT_SECONDS);
      return driver.findElements(unreadMailBy);
    }
}
