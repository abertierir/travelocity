package com.globant.testing.exercise1;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserDriverFactory {
	
	WebDriver driver;
	Logger log;

	public BrowserDriverFactory(String browser, Logger log) {
		if (browser.equals("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		if (browser.equals("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		this.log=log;
	}

	public WebDriver getDriver() {
		return this.driver;
	}
	
}
