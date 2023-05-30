package com.epam.training.oyatullogulomjonov.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected final int WAIT_TIMEOUT_SECONDS = 30;
    
    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public WebElement driverWaitForElementToBeClickable(By elementBy, int timeInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds))
        	.until(ExpectedConditions.elementToBeClickable(elementBy));
    }
    
    public WebElement driverWaitForPresenceOfElementLocated(By elementBy, int timeInSeconds) {
	return new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds))
		.until(ExpectedConditions.presenceOfElementLocated(elementBy));        
    }
    
    public WebElement driverWaitForVisibilityOf(WebElement element, int timeInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds))
        	.until(ExpectedConditions.visibilityOf(element));
    }
}
