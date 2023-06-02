package com.epam.training.oyatullogulomjonov.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;

public class YahooHomePage extends AbstractPage {
    private By mailButtonBy = By.id("ybar-navigation-item-mail");
    
    public YahooHomePage(WebDriver driver) {
      super(driver);
    }

    public YahooMailPage switchToMailPage() {
      driverWaitForElementToBeClickable(mailButtonBy, WAIT_TIMEOUT_SECONDS).click();
      return new YahooMailPage(driver);
    }
 }
