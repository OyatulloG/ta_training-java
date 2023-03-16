package com.epam.training.oyatullogulomjonov.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class MailRuLoginPage extends AbstractPage {    
    private final String PAGE_URL = "https://account.mail.ru";

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
    
    public MailRuLoginPage openPage(){
      driver.navigate().to(PAGE_URL);
      return this;
    }
        
    public MailRuLoginPage enterEmail(String email) {
      WebElement accountTextBox = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.elementToBeClickable(accountTextBoxBy));
      accountTextBox.sendKeys(email);
      driver.findElement(enterPasswordButtonBy).click();
      return this;
    }
    
    public MailRuMailPage enterPassword(String password) {
      WebElement passwordTextBox = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.elementToBeClickable(passwordTextBoxBy));
      passwordTextBox.sendKeys(password);
      driver.findElement(signInButtonBy).click();
      return new MailRuMailPage(driver);      
    }

    public boolean isAccountNotRegistered() {
      WebElement accountNotRegisteredTextBox = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.presenceOfElementLocated(accountNotRegisteredTextBoxBy));
      return accountNotRegisteredTextBox.isDisplayed();
    }    
    
    public boolean isPasswordWrong() {
      WebElement wrongPasswordTextBox = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.presenceOfElementLocated(wrongPasswordTextBoxBy));
      return wrongPasswordTextBox.isDisplayed();
    }    
    
    public boolean isAccountEmpty() {
      WebElement accountEmptyTextBox = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.presenceOfElementLocated(accountEmptyTextBoxBy));
      return accountEmptyTextBox.isDisplayed();
    }
}
