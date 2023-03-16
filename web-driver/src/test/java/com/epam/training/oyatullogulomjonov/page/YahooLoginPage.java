package com.epam.training.oyatullogulomjonov.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class YahooLoginPage extends AbstractPage {
    private final String PAGE_URL = "https://login.yahoo.com";
        
    private By usernameEmailOrMobileTextBoxBy = By.id("login-username");
    private By signinButtonBy = By.id("login-signin");
    private By passwordTextBoxBy = By.id("login-passwd");
    private By emailNotRegisteredTextBoxBy = By.id("username-error");
    private By wrongPasswordTextBoxBy = By.xpath("//*[@id='password-container']/following-sibling::*[@class='error-msg']");
    
    public YahooLoginPage(WebDriver driver) {
      super(driver);
    }
    
    public YahooLoginPage openPage(){
      driver.navigate().to(PAGE_URL);
      return this;
    }
    
    public YahooLoginPage enterEmail(String email) {
      driver.findElement(usernameEmailOrMobileTextBoxBy).sendKeys(email);
      driver.findElement(signinButtonBy).click();      
      return this;
    }
    
    public YahooHomePage enterPassword(String password) {
      WebElement passwordTextBox = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.elementToBeClickable(passwordTextBoxBy));
      passwordTextBox.sendKeys(password);
      driver.findElement(signinButtonBy).click();            
      return new YahooHomePage(driver);
    }
    
    public boolean isEmailNotRegistered() {
      WebElement emailNotRegisteredTextBox = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.presenceOfElementLocated(emailNotRegisteredTextBoxBy));
      return (emailNotRegisteredTextBox != null) ? true : false;
    }
    
    public boolean isPasswordWrong() {
      WebElement wrongPasswordTextBox = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
      		.until(ExpectedConditions.presenceOfElementLocated(wrongPasswordTextBoxBy));
      return (wrongPasswordTextBox != null) ? true : false;
    }    
}
