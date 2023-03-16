package com.epam.training.oyatullogulomjonov.page;

import com.epam.training.oyatullogulomjonov.model.Mail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class MailRuMailPage extends AbstractPage {    
    private By composeButtonBy = By.xpath("//*[@href='/compose/']");
    private By mailToTextBoxBy = By.xpath("//*[@data-type='to']//input");
    private By mailSubjectTextBoxBy = By.name("Subject");
    private By mailContentTextBoxBy = By.xpath("//*[@role='textbox']//div");
    private By sendMailButtonBy = By.xpath("//*[@data-test-id='send']");
    private By mailSentMessageTextBoxBy = By.xpath("//*[@class='layer-sent-page']");  
    private By unreadMailBy = By.xpath("//*[contains(@class, 'subject_unread')]");
    private By unreadMailFromTextBoxBy = By.xpath("//*[@class='letter__author']//*[contains(@class,'letter-contact')]");
    private By unreadMailContentTextBoxBy = By.xpath("//*[@class='letter-body']");
            
    public MailRuMailPage(WebDriver driver) {
      super(driver);
    }
    
    public boolean isPageOpen() {
      WebElement composeButton = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.presenceOfElementLocated(composeButtonBy));
      return (composeButton != null) ? true : false;
    }

    public MailRuMailPage sendNewMail(Mail mail) {
      WebElement composeButton = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.elementToBeClickable(composeButtonBy));
      composeButton.click();            
      WebElement mailToTextBox = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.elementToBeClickable(mailToTextBoxBy));
      mailToTextBox.sendKeys(mail.getEmail());	
      driver.findElement(mailSubjectTextBoxBy).sendKeys(mail.getSubject());
      driver.findElement(mailContentTextBoxBy).sendKeys(mail.getContent());
      WebElement sendMailButton = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.elementToBeClickable(sendMailButtonBy));
      sendMailButton.click();
      return this;
    }
    
    public boolean isMailSent() {
      WebElement mailSentMessageTextBox = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.presenceOfElementLocated(mailSentMessageTextBoxBy));
      return (mailSentMessageTextBox != null) ? true : false;
    }
    
    public Mail checkMail(String subjectOfMail) {
      WebElement mail = isMailReceived(subjectOfMail);
      if (mail == null) {
	return null;
      }      
      mail.click();      

      WebElement unreadMailFromTextBox = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.presenceOfElementLocated(unreadMailFromTextBoxBy));
      String mailFrom = unreadMailFromTextBox.getAttribute("title");
      String mailContent = driver.findElement(unreadMailContentTextBoxBy).getText();      
      return new Mail(mailFrom, subjectOfMail, mailContent);      
    }
        
    private WebElement isMailReceived(String subjectOfMail) {
      List<WebElement> unreadMails = getUnreadMails();
      for (WebElement unreadMail : unreadMails) {
        if (unreadMail.getText().equals(subjectOfMail)) {
          return unreadMail;
        }
      }
      return null;
    }
    
    private List<WebElement> getUnreadMails() {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
      wait.until(ExpectedConditions.presenceOfElementLocated(unreadMailBy));
      return driver.findElements(unreadMailBy);
    }
 }
