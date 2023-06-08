package com.epam.training.oyatullogulomjonov.page;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MailRuLoginPage extends AbstractPage {    
    private final String PAGE_URL = "https://account.mail.ru";
    private final Logger logger = LogManager.getRootLogger();

    private By accountTextBoxBy = By.name("username");    
    private By enterPasswordButtonBy = By.xpath("//*[@data-test-id='next-button']");    
    private By passwordTextBoxBy = By.name("password");    
    private By signInButtonBy = By.xpath("//*[@data-test-id='submit-button']");
    private By accountNotRegisteredTextBoxBy = By.xpath("//*[@data-test-id='accountNotRegistered']");
    private By wrongPasswordTextBoxBy = By.xpath("//*[@data-test-id='password-input-error']"); 
    private By accountEmptyTextBoxBy = By.xpath("//*[@data-test-id='required']");           
    
    public MailRuLoginPage(WebDriver driver) {
      super(driver);
    }
    
    public MailRuLoginPage openPage() {
      driver.navigate().to(PAGE_URL);
      logger.info("MailRu Login page opened");
      return this;
    }
        
    public MailRuLoginPage enterEmail(String email) {
      driverWaitForElementToBeClickable(accountTextBoxBy, WAIT_TIMEOUT_SECONDS).sendKeys(email);
      return this;
    }
    
    public MailRuLoginPage clickEnterPasswordButton() {
      driverWaitForElementToBeClickable(enterPasswordButtonBy, WAIT_TIMEOUT_SECONDS).click();
      return this;      
    }
    
    public MailRuLoginPage enterPassword(String password) {
      driverWaitForElementToBeClickable(passwordTextBoxBy, WAIT_TIMEOUT_SECONDS).sendKeys(password);
      return this;
    }
    
    public MailRuMailPage clickSignInButton() {
      driverWaitForElementToBeClickable(signInButtonBy, WAIT_TIMEOUT_SECONDS).click();
      logger.info("Login performed");
      return new MailRuMailPage(driver);    
    }

    public boolean isAccountNotRegistered() {
      return driverWaitForPresenceOfElementLocated(accountNotRegisteredTextBoxBy, WAIT_TIMEOUT_SECONDS).isDisplayed();
    }    
    
    public boolean isPasswordWrong() {
      return driverWaitForPresenceOfElementLocated(wrongPasswordTextBoxBy, WAIT_TIMEOUT_SECONDS).isDisplayed();
    }    
    
    public boolean isAccountEmpty() {
      return driverWaitForPresenceOfElementLocated(accountEmptyTextBoxBy, WAIT_TIMEOUT_SECONDS).isDisplayed();
    }
}
