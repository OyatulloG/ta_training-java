package com.epam.training.oyatullogulomjonov.page;

import com.epam.training.oyatullogulomjonov.util.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Actions;
import java.util.List;

public class YahooMailPage extends AbstractPage {   
    private final Logger logger = LogManager.getRootLogger();
    public final String YAHOOMAIL = "sampleonesample@yahoo.com";
    
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
    
    public YahooMailPage clickAccountProfileButton() {
      accountProfileButton.click();
      return this;
    }
    
    public YahooLoginPage clickProfileSignOutButton() {
      driverWaitForElementToBeClickable(profileSignOutButtonBy, WAIT_TIMEOUT_SECONDS).click();
      return new YahooLoginPage(driver);    
    }
    
    public YahooMailPage clickComposeButton() {
      composeButton.click();
      return this;
    }
    
    public YahooMailPage sendKeysToMailToTextBox(String mailTo) {
      driverWaitForElementToBeClickable(mailToTextBoxBy, WAIT_TIMEOUT_SECONDS).sendKeys(mailTo);
      return this;
    }
    
    public YahooMailPage sendKeysToMailSubjectTextBox(String mailSubject) {
      mailSubjectTextBox.sendKeys(mailSubject);
      return this;
    }
    
    public YahooMailPage sendKeysToMailContentTextBox(String mailContent) {
      new Actions(driver)
      		.sendKeys(mailContentTextBox, mailContent)
      		.perform();      
      return this;
    }
    
    public YahooMailPage clickSendMailButton() {
      sendMailButton.click();
      return this;
    }
    
    public boolean isMailSent() {
      return driverWaitForPresenceOfElementLocated(mailSentMessageTextBoxBy, WAIT_TIMEOUT_SECONDS).isDisplayed();
    }
        
    public YahooMailPage clickMail(WebElement mail) {
      mail.click();
      return this;
    }
    
    public String getMailFromText() {
      WebElement unreadMailFromTextBox = driverWaitForPresenceOfElementLocated(unreadMailFromTextBoxBy, WAIT_TIMEOUT_SECONDS);
      return StringUtils.extractEmailForYahoo(unreadMailFromTextBox.getText());    
    }        
    
    public String getMailContentText() {
      return StringUtils.extractMailContentForYahoo(unreadMailContentTextBox.getText());
    }    
    
    public WebElement getUnreadMailWithSubject(String subjectOfMail) {
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
