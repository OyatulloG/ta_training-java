package com.epam.training.oyatullogulomjonov.page;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YahooLoginPage extends AbstractPage {
    private final String PAGE_URL = "https://login.yahoo.com";
    private final Logger logger = LogManager.getRootLogger();
        
    private By accountSwitcherThreeDotsButtonBy = By.xpath("//*[@id='account-switcher-form']//*[@class='card-right']");        
    private By removeAccountButtonBy = By.xpath("//*[@data-action='remove-account']");
        
    @FindBy(id = "login-username")
    private WebElement usernameEmailOrMobileTextBox;
    
    @FindBy(id = "login-signin")
    private WebElement signInButton;
    
    private By passwordTextBoxBy = By.id("login-passwd");
    
    @FindBy(id = "username-error")
    private WebElement emailNotRegisteredTextBox;
    
    @FindBy(xpath = "//*[@id='password-container']/following-sibling::*[@class='error-msg']")
    private WebElement wrongPasswordTextBox;
            
    public YahooLoginPage(WebDriver driver) {
      super(driver);
      PageFactory.initElements(this.driver, this);
    }
    
    public YahooLoginPage openPage(){
      driver.navigate().to(PAGE_URL);
      logger.info("Yahoo Login page opened");
      return this;
    }
    
    public void removeAccount() {
      driverWaitForElementToBeClickable(accountSwitcherThreeDotsButtonBy, WAIT_TIMEOUT_SECONDS).click();
      driverWaitForElementToBeClickable(removeAccountButtonBy, WAIT_TIMEOUT_SECONDS).click();
    }
    
    public YahooLoginPage enterEmail(String email) {
      usernameEmailOrMobileTextBox.sendKeys(email);     
      return this;
    }
    
    public YahooLoginPage clickNextButton() {
      signInButton.click();
      return this;    
    }
    
    public YahooLoginPage enterPassword(String password) {
      driverWaitForElementToBeClickable(passwordTextBoxBy, WAIT_TIMEOUT_SECONDS).sendKeys(password);
      return this;
    }
    
    public YahooHomePage clickSignInButton() {
      signInButton.click();
      logger.info("Login performed");
      return new YahooHomePage(driver);      
    }
    
    public boolean isEmailNotRegistered() {
      return driverWaitForVisibilityOf(emailNotRegisteredTextBox, WAIT_TIMEOUT_SECONDS).isDisplayed();
    }
    
    public boolean isPasswordWrong() {
      return driverWaitForVisibilityOf(wrongPasswordTextBox, WAIT_TIMEOUT_SECONDS).isDisplayed();
    }    
}
