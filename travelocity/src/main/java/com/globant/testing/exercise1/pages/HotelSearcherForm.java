package com.globant.testing.exercise1.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HotelSearcherForm extends BasePage{
    public HotelSearcherForm(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private static final String HOTEL_TAP_ID = "tab-hotel-tab-hp";

    //WebElements
    @FindBy(id=HOTEL_TAP_ID)
    private WebElement flightTab;

    public ResultFlightsPage completeForm(String fromCity, String destiny, String dateDeparting, String dateReturn, String adults){
        return new ResultFlightsPage(getDriver(),log);
    }
}
