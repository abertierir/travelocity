package com.globant.testing.exercise1.pages;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Logger log;
	
	public BasePage(WebDriver driver, Logger log) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 60);
		this.driver = driver;
		this.log = log;
	}
	
	public WebDriverWait getWait() {
		return wait;
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public void dispose() {
		if(driver!=null) {
			driver.quit();
		}
	}
	
	protected List<WebElement> findAllElementsPresentOnPage(By locator) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		return driver.findElements(locator);
	}
	
	protected WebElement find(By locator) {
		waitForVisibilityOf(locator, 40);
		return driver.findElement(locator);
	}
	
	protected WebElement find(By locator, int time) {
		waitForVisibilityOf(locator, time);
		return driver.findElement(locator);
	}
	
	protected void click(By locator) {
		waitForVisibilityOf(locator, 20);
		find(locator).click();
	}
	
	protected void type(String text, By locator) {
		waitForVisibilityOf(locator ,20);
		find(locator).click();
		find(locator).sendKeys(text);
	}
	
	protected void select(String text, By locator) {
		Select dropdown = new Select(driver.findElement(locator));
		dropdown.selectByVisibleText(text);
	}

	protected void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
		timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(condition);
	}
	
	protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
		int attempts = 0;
		while (attempts < 2) {
			try {
				waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
						(timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
	}
	

	protected void waitForVisibilityOfAllElements(By locator, Integer... timeOutInSeconds) {
		int attempts = 0;
		while (attempts < 2) {
			try {
				waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
						(timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
		
	}
	
}
