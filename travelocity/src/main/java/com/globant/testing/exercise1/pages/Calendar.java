package com.globant.testing.exercise1.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.apache.logging.log4j.Logger;

/**
 * This object represents the calendar element located on Fligths Searcher Form.
 * 
 * @author Andrea.Bertieri
 *
 */
public class Calendar extends BasePage {
	
	/**
	 * Constructor method.
	 * 
	 * @author Andrea.Bertieri
	 * 
	 * @param driver: WebDriver
	 */
	public Calendar(WebDriver pDriver, Logger log) {
		super(pDriver, log);
	}
	//Locators Constants
	private static final String NEXT_ARROW_CLASS= "datepicker-next";
	private static final String MONTH_CLASS= "datepicker-cal-month-header";
	
	//WebElements
	@FindBy(className=NEXT_ARROW_CLASS)
	WebElement followButton;
	@FindBy(className=MONTH_CLASS)
	List<WebElement> dateTitle;
	
	/**
	 * Flow of date location on calendar 
	 * 
	 * @author Andrea.bertieri
	 * 
	 * @param date: date to locate
	 */
	public void completeCalendar(String date) {
		log.info("Searching Date");
		DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH);
		String dateKey = LocalDate.parse(date).format(dateTime);
		monthSearch(dateKey);
		LocalDate ld = LocalDate.parse(date);
		int month=ld.getMonthValue()-1;
		int day=ld.getDayOfMonth();
		daySearch(month,day);
		
	}
	
	/**
	 * month location.
	 * 
	 * @author Andrea.bertieri
	 * 
	 * @param dateKey: month to locate
	 */
	private void monthSearch(String dateKey) {
		boolean found=false;
		for (WebElement e: dateTitle) {
			found=e.getText().equals(dateKey);
			if (found){break;}
		}
		if(!found) {
			followButton.click();
			monthSearch(dateKey);
		}	
	}
	
	/**
	 * day location.
	 * 
	 * @author Andrea.bertieri
	 * 
	 * @param month: month previously found
	 * @param day: day to locate
	 */
	private void daySearch(int month, int day) {
		WebElement dayElement = driver.findElement(By.xpath("//*[@data-month='"+month+"' and @data-day='"+day+"']"));
		dayElement.click();			
	}
}