package com.epam.training.oyatullogulomjonov.page;

import com.epam.training.oyatullogulomjonov.model.Mail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.time.Duration;

public class YahooMailPage extends AbstractPage {   
    private By composeButtonBy = By.xpath("//*[@data-test-id='compose-button']");
    private By mailToTextBoxBy = By.id("message-to-field");
    private By mailSubjectTextBoxBy = By.xpath("//*[@data-test-id='compose-subject']");
    private By mailContentTextBoxBy = By.xpath("//*[@data-test-id='rte']/div");
    private By sendMailButtonBy = By.xpath("//*[@data-test-id='compose-send-button']");
    private By mailSentMessageTextBoxBy = By.xpath("//*[@data-test-id='navigate-button']/parent::span");
    private By unreadMailBy = By.xpath("//a[@data-test-read='false']");
    private By unreadMailFromTextBoxBy = By.xpath("//*[@data-test-id='message-from']");
    private By unreadMailContentTextBoxBy = By.xpath("//*[@data-test-id='message-view-body-content']");
    
    public YahooMailPage(WebDriver driver) {
      super(driver);
    }
    
    public boolean isPageOpen() {
      WebElement composeButton = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.presenceOfElementLocated(composeButtonBy));
      return (composeButton != null) ? true : false;
    }

    public YahooMailPage sendNewMail(Mail mail) {
      driver.findElement(composeButtonBy).click();
      WebElement mailToTextBox = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.elementToBeClickable(mailToTextBoxBy));
      mailToTextBox.sendKeys(mail.getEmail());
      driver.findElement(mailSubjectTextBoxBy).sendKeys(mail.getSubject());
      driver.findElement(mailContentTextBoxBy).sendKeys(mail.getContent());
      driver.findElement(sendMailButtonBy).click();
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
      String mailFrom = unreadMailFromTextBox.getText();
      String mailEmail = mailFrom.substring(mailFrom.indexOf("<")+1, mailFrom.indexOf(">"));      
      String mailText = driver.findElement(unreadMailContentTextBoxBy).getText();
      String mailContent = mailText.substring(0, mailText.indexOf("--")-2);      
      return new Mail(mailEmail, subjectOfMail, mailContent);
    }
    
    private WebElement isMailReceived(String subjectOfMail) {
      List<WebElement> unreadMails = getUnreadMails();
      for (WebElement unreadMail : unreadMails) {
        if (unreadMail.getAttribute("aria-label").contains(subjectOfMail)) {
          return unreadMail;
        }
      }
      return null;      
    }
    
    private List<WebElement> getUnreadMails() {
      return driver.findElements(unreadMailBy);
    }
 }	
