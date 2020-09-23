package com.globant.testing.exercise1.pages;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ReviewYourTripPage extends BasePage {

	public ReviewYourTripPage(WebDriver pDriver, Logger log) {
		super(pDriver, log);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	}
	
	private static final String TOTAL_PRICE_CSS_1="div.uitk-grid"; //span.packagePriceTotal";
	private By TotalPriceLocator=By.cssSelector(TOTAL_PRICE_CSS_1);
	
	private static final String TOTAL_PRICE_CSS_2="span.packagePriceTotal";
	private By TripTotalPriceLocator=By.cssSelector(TOTAL_PRICE_CSS_2);
	
	private By departureInfo=By.className("importantInfoRules");
	private By returnInfo= By.cssSelector("div.flex-card.flex-tile.details.OD1");
	private By PriceGuarantee= By.className("priceGuarantee");

	public boolean isTripTotalPricePresent() {
		log.info(driver.getCurrentUrl());
	    changeWindow();
	    log.info(find(TotalPriceLocator,100).findElement(TripTotalPriceLocator).getText());
	    return true;
	}

	public boolean isDepartureInfoPresent() {
		log.info(driver.getCurrentUrl());
	    changeWindow();
	    log.info(find(TotalPriceLocator,100).findElement(departureInfo).getText());
	    return true;
	}

	public boolean isReturnInfoPresent() {
		log.info(driver.getCurrentUrl());
	    changeWindow();
	    log.info(find(TotalPriceLocator,100).findElement(returnInfo).getText());
	    return true;
	}

	public boolean isPriceGuaranteePresent() {
		log.info(driver.getCurrentUrl());
	    changeWindow();
	    log.info(find(TotalPriceLocator,100).findElement(PriceGuarantee).getText());
	    return true;
	}
	
	public WhosTravellingPage clickOnSubmit() {
		log.info(driver.getCurrentUrl());
		click(By.id("bookButton"));
		return new WhosTravellingPage(driver, log);	
	}

	public void changeWindow() {
		log.info("Close Other windows");
		while(driver.getCurrentUrl().contains("www.travelocity.com/Flight-Information")==false) {
			 for (String handle : driver.getWindowHandles()) {
				    driver.switchTo().window(handle);
				    while(driver.getCurrentUrl().equals("about:blank")){}
				    if (driver.getCurrentUrl().contains("www.travelocity.com/Flight-Information")) {
				      break;
				    }
			}
		}
		log.info("Ohter windows closed. We are on "+driver.getCurrentUrl());
	}
	
}
