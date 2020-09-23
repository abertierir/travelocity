package com.globant.testing.exercise1.tests;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.globant.testing.exercise1.pages.ResultFlightsPage;
import com.globant.testing.exercise1.pages.ReviewYourTripPage;
import com.globant.testing.exercise1.pages.WhosTravellingPage;

public class buyTicketsBasicFlowTest extends BaseTest {
	
	
		
	/**
	 * @description Complete Flights Search Form to verify the result page structure
	 * @author andrea.bertieri
	 */
	@Test(dataProvider="searchFlightsData")
	public void completeFormTest(List<String> sortOptions, Map<String, String> basicInfo) {
		searcherForm.FlightTabClick();
		ResultFlightsPage FlightsPage=searcherForm.completeForm(basicInfo.get("from"), basicInfo.get("to"), basicInfo.get("departureDate"), basicInfo.get("arriveDate"), basicInfo.get("adults"));

		Assert.assertTrue(FlightsPage.areSortOptionsPresentInDropdown(sortOptions), "Verify:if any option from the sort dropdown was not found.");
		Assert.assertTrue(FlightsPage.allFlightsBoxesHaveSelectButtons(), "Verify: Select Buttons and Flights Boxes did not match");
		Assert.assertTrue(FlightsPage.allFlightsBoxesHaveFlightDurationInfo(), "Verify: Flights Duration Info and Flights Boxes did not match");
		Assert.assertTrue(FlightsPage.allFlightsBoxesHaveDetailsAndBaggageFees(), "Verify: Details and BaggageFees Links and Flights Boxes did not match");
	}
	
	/**
	 * @description Verify sorted flights result  by duration
	 * @author andrea.bertieri
	 */
	@Test(dataProvider="searchFlightsData")
	public void sortingFlightsResultTest(List<String> sortOptions, Map<String, String> basicInfo) {
		
		searcherForm.FlightTabClick();
		ResultFlightsPage FlightsPage=searcherForm.completeForm(basicInfo.get("from"), basicInfo.get("to"), basicInfo.get("departureDate"), basicInfo.get("arriveDate"), basicInfo.get("adults"));

		FlightsPage.sortFlightsResult("Duration (Shortest)");	
		
		Assert.assertTrue(FlightsPage.FlightsAreSortingByUpwardDuration(),"Verify: Fligths are not sorted By Upward Duration");
	}
	
	/**
	 * Verify Review your trip Page after select departure and arrival Flight
	 * @author andrea.bertieri
	 */
	@Test( dataProvider="choseFlightsData")
	public void selectFlightsTest(List<String> sortOptions, Map<String, String> basicInfo, int departureFlight, int arrivalFlight) {
		
		searcherForm.FlightTabClick();
		ResultFlightsPage FlightsPage=searcherForm.completeForm(basicInfo.get("from"), basicInfo.get("to"), basicInfo.get("departureDate"), basicInfo.get("arriveDate"), basicInfo.get("adults"));

		FlightsPage.sortFlightsResult("Duration (Shortest)");	
		FlightsPage=FlightsPage.selectDepartureFlight(departureFlight);
		ReviewYourTripPage reviewYourTrip=FlightsPage.selectArrivalFlight(arrivalFlight);

		Assert.assertTrue(reviewYourTrip.isTripTotalPricePresent(), "There isn't Total Price");
		Assert.assertTrue(reviewYourTrip.isDepartureInfoPresent(), "There isn't Departure Info");
		Assert.assertTrue(reviewYourTrip.isReturnInfoPresent(),"There isn't Return Info");
		Assert.assertTrue(reviewYourTrip.isPriceGuaranteePresent(),"There isn't Guarantee Price");
	}
	
	@Test(dataProvider="choseFlightsData")
	public void completeFormWithEmptyFieldsWhoIsTravelingTest(List<String> sortOptions, Map<String, String> basicInfo, int departureFlight, int arrivalFlight) {
		
		//Data
		List<String> errorMessage = Arrays.asList("Please enter a first name using letters only.", 
				"Please enter a last name using letters only (minimum 2 characters).",
				"Please enter a valid phone number.",
				"Please select a gender.", "Please select a valid date of birth.");
				
		
		searcherForm.FlightTabClick();
		ResultFlightsPage FlightsPage=searcherForm.completeForm(basicInfo.get("from"), basicInfo.get("to"), basicInfo.get("departureDate"), basicInfo.get("arriveDate"), basicInfo.get("adults"));

		FlightsPage.sortFlightsResult("Duration (Shortest)");	
		FlightsPage=FlightsPage.selectDepartureFlight(departureFlight);
		ReviewYourTripPage reviewYourTrip=FlightsPage.selectArrivalFlight(arrivalFlight);
		reviewYourTrip.changeWindow();
		WhosTravellingPage whosTravelling = reviewYourTrip.clickOnSubmit();
		
		//Then
		whosTravelling.completeWhosTravelingForm("","","","","","","","").stream()
	 	.forEach(message -> Assert.assertTrue(errorMessage.contains(message), message+" Wrong message"));
		
	}
	
	
}
