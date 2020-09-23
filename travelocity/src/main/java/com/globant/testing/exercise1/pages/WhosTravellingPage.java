package com.globant.testing.exercise1.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WhosTravellingPage extends BasePage{
	
	public WhosTravellingPage(WebDriver pDriver, Logger log) {
		super(pDriver, log);
	}
	
	//locators
	private static final String NAME_ID="firstname[0]";
	private By nameLocator=By.id(NAME_ID);
	
	private static final String LASTNAME_ID="lastname[0]";
	private By lastnameLocator=By.id(LASTNAME_ID);
	
	private static final String COUNTRY_CODE_ID="country_code[0]";
	private By countryCodeLocator=By.id(COUNTRY_CODE_ID);
	
	private static final String PHONE_NUMBER="phone-number[0]";
	private By phoneLocator=By.id(PHONE_NUMBER);
	
	private static final String GENDER_ID="firstname[0]";
	private By genderLocator=By.id(GENDER_ID);
	
	private static final String BIRTH_MONTH_ID="date_of_birth_month0";
	private By birthMonthLocator=By.id(BIRTH_MONTH_ID);
	
	private static final String BIRTH_DAY_ID="date_of_birth_day[0]";
	private By birthDayLocator=By.id(BIRTH_DAY_ID);
	
	private static final String BIRTH_YEAR_ID="date_of_birth_year[0]";
	private By birthYearLocator=By.id(BIRTH_YEAR_ID);
	
	private static final String COMPLETE_BOOKING_BUTTON_ID="complete-booking";
	private By completeBookingLocator=By.id(COMPLETE_BOOKING_BUTTON_ID);
	
	private static final String OPTIONAL_PREFS_LINK_ID="optional-prefs-toggle-link-0";
	private By optionalPrefsLocator=By.id(OPTIONAL_PREFS_LINK_ID);
	
	//message locators
	
	private static final String ERROR_MESSAGE="form[class='air-trip-preference preferences-form'] fieldset[class='travelerDetails'] p[class='uitk-validation-error']"; 
	private By errorMessageLocator=By.cssSelector(ERROR_MESSAGE);
	
	public List<String> completeWhosTravelingForm(String name, String lastname, String countryCode, String phoneNumber, String gender, String month, String day, String year) {
		log.info("complete Who is Traveling Form");
		type(name,nameLocator);
		type(lastname,lastnameLocator);
		type(phoneNumber,phoneLocator);
		click(completeBookingLocator);
		log.info(driver.getCurrentUrl());
		return Arrays.asList(findAllElementsPresentOnPage(errorMessageLocator).get(0).getText(), 
				findAllElementsPresentOnPage(errorMessageLocator).get(1).getText(),
				findAllElementsPresentOnPage(errorMessageLocator).get(2).getText(),
				findAllElementsPresentOnPage(errorMessageLocator).get(3).getText(),
				findAllElementsPresentOnPage(errorMessageLocator).get(4).getText());
	}

}
