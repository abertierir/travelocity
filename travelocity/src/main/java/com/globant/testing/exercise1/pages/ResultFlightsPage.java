package com.globant.testing.exercise1.pages;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.google.common.collect.Ordering;

/**
 * Page of available flights which have been found
 * 
 * @author andrea.bertieri
 *
 */

public class ResultFlightsPage extends BasePage {
	
	/**
	 * Constructor method.
	 * 
	 * @author Andrea.Bertieri
	 * 
	 * @param driver
	 *            : WebDriver
	 */
	public ResultFlightsPage(WebDriver driver, Logger log) {
		super(driver, log);
	}
	
	// Locators Constants
	private static final String SORT_DROPDOWN_ID = "sortDropdown";
	private static final String SELECT_BUTTON_CSS="div[class='uitk-col all-col-shrink'] button[data-test-id='select-button']";
	private static final String ARRIVAL_TIME_CSS="span[data-test-id='arrival-time']";
	private static final String DEPARTURE_TIME_CSS="span[data-test-id='departure-time']";
	private static final String SECOND_SELECT_BUTTON_CSS="div[class='basic-economy-footer uitk-grid all-grid-align-end'] button[class='btn-secondary btn-action t-select-btn']";
		
	//Locators Departure Flights
	private static final String FLIGHT_RESULT_BOX_CSS="div[class=\"grid-container standard-padding \"]";
	private By FlightResultBoxLocator=By.cssSelector(FLIGHT_RESULT_BOX_CSS);
	
	private static final String SELECT_DEPARTURE_BUTTON_CSS="div[class='uitk-col all-col-shrink'] button[class='btn-secondary btn-action t-select-btn']";
	private By selectDepartureButtonLocator=By.cssSelector(SELECT_DEPARTURE_BUTTON_CSS);
	
	private By selectButton=By.cssSelector(SELECT_BUTTON_CSS);
	private By sortingDropdownLocator=By.id(SORT_DROPDOWN_ID);
	private By ArrivalTimeLocator= By.cssSelector(ARRIVAL_TIME_CSS);
	private By DepartureTimeLocator= By.cssSelector(DEPARTURE_TIME_CSS);
	private By FlightDurationLocator = By.className("duration-emphasis");
	private By DetailsAndBaggageLinkText= By.partialLinkText("Details & baggage fees");
	
	//Locators Arrival Flights
	
	private static final String BUTTON_DIV_ARRIVAL_FLIGHT_BOX_CSS="li[data-test-id='offer-listing']";
	private By ButtonDivArrivalFlightBoxLocator=By.cssSelector(BUTTON_DIV_ARRIVAL_FLIGHT_BOX_CSS);
	
	private static final String ARRIVAL_SELECT_BUTTON_CSS="button[class='btn-secondary btn-action t-select-btn']";
	private By ArrivalSelectButtonLocator=By.cssSelector(ARRIVAL_SELECT_BUTTON_CSS);
	
	/**
	 * @author Andrea.Bertieri
	 *
	 * @description: Sort the flights results given a selected dropdown value
	 * 
	 */	
	public void sortFlightsResult(String option) {
		
		log.info("Sorting Flights");
		select(option,sortingDropdownLocator);
		
	}
	
	/**
	 * @author Andrea.Bertieri
	 *
	 * @description: Returns true if all elements from sort dropdown list are found
	 * 
	 */
	public boolean areSortOptionsPresentInDropdown(List<String> sortOptions) {
		
		Select dropdown = new Select(driver.findElement(sortingDropdownLocator));
		long dropdownMatches=dropdown.getOptions()
			.stream()
			.filter(c -> sortOptions.contains(c.getText()))
			.count();
		if (dropdownMatches==sortOptions.size()) {return true;}			
		else{return false;}
		
	}
	
	/**
	 * @author Andrea.Bertieri
	 *
	 * @description: Returns true if  there are the same quantity 
	 * of flights boxes and select buttons
	 * 
	 */
	public boolean allFlightsBoxesHaveSelectButtons() {
		
		int flightsBoxes=findAllElementsPresentOnPage(FlightResultBoxLocator).size();
		int selectButtons=findAllElementsPresentOnPage(selectButton).size();
		log.info("Verifying if there are the same quantity of flights boxes: "
				+flightsBoxes+" and select buttons: "
				+selectButtons);
		if(flightsBoxes==selectButtons && flightsBoxes!=0) {return true;}
		else {return false;}
		
	}

	/**
	 * @author Andrea.Bertieri
	 *
	 * @description: Returns true if all Flight duration info are present on every flight result
	 * 
	 */
	public boolean allFlightsBoxesHaveFlightDurationInfo() {
		
		int flightsBoxes=findAllElementsPresentOnPage(FlightResultBoxLocator).size();
		int flightDurationInfo=findAllElementsPresentOnPage(FlightDurationLocator).size();
		log.info("Verifying if there are the same quantity of flights boxes: "
				+flightsBoxes+" and Flight Duration info: "
				+flightDurationInfo);
		if(flightsBoxes==flightDurationInfo && flightsBoxes!=0) {return true;} 
		else {return false;}
		
	}

	/**
	 * @author Andrea.Bertieri
	 *
	 * @description: Returns true if all Details And BaggageFees info are present on every flight result
	 * 
	 */
	public boolean allFlightsBoxesHaveDetailsAndBaggageFees() {
		
		int flightsBoxes=findAllElementsPresentOnPage(FlightResultBoxLocator).size();
		int flightDetails=findAllElementsPresentOnPage(DetailsAndBaggageLinkText).size();
		log.info("Verifying if there are the same quantity of flights boxes: "
				+flightsBoxes+" and Flight Details /BaggageFees : "
				+flightDetails);
		if(flightsBoxes==flightDetails && flightsBoxes!=0) {return true;}
		else {return false;}
		
	}

	/**
	 * @author Andrea.Bertieri
	 *
	 * @description: Returns true if flight boxes are sorted by upward duration
	 * 
	 */
	public boolean FlightsAreSortingByUpwardDuration() {

		wait.until(ExpectedConditions.stalenessOf(find(ArrivalTimeLocator)));
		List<WebElement> departureTime= findAllElementsPresentOnPage(DepartureTimeLocator);
		List<WebElement> arrivalTime= findAllElementsPresentOnPage(ArrivalTimeLocator);
		return Ordering.natural().isOrdered(getTimeDifference(departureTime, arrivalTime));

	}
	
	/**
	 * @author Andrea.Bertieri
	 *
	 * @description: Returns and array with arrival and departure time difference.
	 * 
	 */
	private List<Duration> getTimeDifference(List<WebElement> departureTime, List<WebElement> arrivalTime) {
		
		DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("h:mma", Locale.ENGLISH);
		List<Duration> timeDifference = new ArrayList<Duration>();
		for (int i = 0; i < departureTime.size()/2; i++) {
	    	 LocalTime departureLocal = LocalTime.parse(departureTime.get(i).getText().toUpperCase(),dateTime);
	    	 LocalTime arrivalLocal = LocalTime.parse(arrivalTime.get(i).getText().toUpperCase(),dateTime);
	    	 timeDifference.add(Duration.between(departureLocal, arrivalLocal));
	    	 log.info("Time difference is"+Duration.between(departureLocal, arrivalLocal));
	     }
		return timeDifference;
		
	}

	/**
	 * @author Andrea.Bertieri
	 *
	 * @description: Select departure Flight doing click on Select Buttons
	 * 
	 */	
	public ResultFlightsPage selectDepartureFlight(int i) {
		
		log.info("Choosing first flight");
		wait.until(ExpectedConditions.stalenessOf(find(FlightResultBoxLocator)));
		findAllElementsPresentOnPage(FlightResultBoxLocator)
			.get(i-1)
			.findElement(selectDepartureButtonLocator)
			.click();
		clickIfExistsSecondSelect(SECOND_SELECT_BUTTON_CSS, i);		
		return this;
	} 
	
	/**
	 * @author Andrea.Bertieri
	 *
	 * @description: Select arrival Flight doing click on Select Buttons
	 * 
	 */	
	public ReviewYourTripPage selectArrivalFlight(int i) {
		
		log.info("Choosing second flight");
		wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(ButtonDivArrivalFlightBoxLocator,  ArrivalSelectButtonLocator));
		driver.findElements(ButtonDivArrivalFlightBoxLocator)
							.get(i-1)
							.findElement(ArrivalSelectButtonLocator)
							.click();

		clickIfExistsSecondSelect(SECOND_SELECT_BUTTON_CSS, i);		
		return new ReviewYourTripPage(driver, log);
	} 
	/**
	 * @author Andrea.Bertieri
	 *
	 * @description: Press second select button if exist
	 * 
	 */	
	private void clickIfExistsSecondSelect(String locator, int index) {
		try {
			find(By.cssSelector("div[id='basic-economy-tray-content-"+index+"'] "+locator)).click();
		}catch(Exception ex){}
	}

}
