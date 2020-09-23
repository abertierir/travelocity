package com.globant.testing.exercise1.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Object of the Flights Searcher Form that appears in Home Page
 * 
 * @author Andrea.Bertieri
 *
 */
public class FlightsSearcherForm extends BasePage{
	
	/**
	 * Constructor method.
	 * 
	 * @author Andrea.Bertieri
	 * @param log 
	 * 
	 * @param driver: WebDriver
	 */
	public FlightsSearcherForm(WebDriver driver, Logger log) {
		super(driver, log);
		driver.get("https://www.travelocity.com/");
	}
	
	//Locators Constants
	private static final String FLIGHT_TAP_ID = "tab-flight-tab-hp";
	private static final String FLYING_FORM_FIELD_ID = "flight-origin-hp-flight";
	private static final String FLYING_TO_FIELD_ID = "flight-destination-hp-flight";
	private static final String DATE_DEPARTING_FIELD_ID = "flight-departing-hp-flight";
	private static final String DATE_RETURN_FIELD_ID = "flight-returning-hp-flight";
	private static final String SUBMIT_BUTTON_CLASS = "gcw-submit";
	private static final String ADULTS_FIELD_ID = "flight-adults-hp-flight";
	
	//Locators
	private By AdultsFieldLocator=By.id(ADULTS_FIELD_ID );
	
	//WebElements
	@FindBy(id=FLIGHT_TAP_ID)
	private WebElement flightTab;

	@FindBy(id=FLYING_FORM_FIELD_ID)
	private WebElement fromField;
	
	@FindBy(id=FLYING_TO_FIELD_ID)
	private WebElement toField;
	
	@FindBy(id=DATE_DEPARTING_FIELD_ID)
	private WebElement departingField;
	
	@FindBy(id=DATE_RETURN_FIELD_ID)
	private WebElement returnField;
	
	@FindBy(className=SUBMIT_BUTTON_CLASS)
	private WebElement submitButton;
	
	Calendar calendar = new Calendar(driver,log);
	
	/**
	 * Select the Flight Form Tap.
	 * 
	 * @author Andrea.bertieri
	 */
	public void FlightTabClick() {
		log.info("Changing to Flight Window");
		flightTab.click();
	}
	
	/**
	 * Complete search flights form with basic data input.
	 * 
	 * @author Andrea.bertieri
	 * 
	 * @param fromCity: Flying from info
	 * @param destiny: Flying to info
	 * @param dateDeparting: Departing date info
	 * @param dateReturn: Returning date info
	 * @param adults: How many adults would travel?
	 * 
	 * @return Next Page with flight results
	 */
	public ResultFlightsPage completeForm(String fromCity, String destiny, String dateDeparting, String dateReturn, String adults) {
		log.info("Executing Fligths Searcher Form with Flying from: [" + fromCity 
				+ "], Flying to: [" + destiny 
				+ "], Departing date: ["+ dateDeparting 
				+ "], Returning date: ["+ dateReturn 
				+ "], 	How many adults?: ["+ adults +"]");
		type(fromCity, By.id(FLYING_FORM_FIELD_ID));
		type(destiny, By.id(FLYING_TO_FIELD_ID));
		departingField.click();
		calendar.completeCalendar(dateDeparting);
		returnField.click();
		calendar.completeCalendar(dateReturn);
		select(adults, AdultsFieldLocator);
		submitButton.click();
		return new ResultFlightsPage(getDriver(),log);
	}
	
}

	
