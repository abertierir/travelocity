package com.globant.testing.exercise1.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.globant.testing.exercise1.BrowserDriverFactory;
import com.globant.testing.exercise1.pages.FlightsSearcherForm;
import com.globant.testing.exercise1.utils.DataRepository;

public class BaseTest {
	
	BrowserDriverFactory factory;
	FlightsSearcherForm searcherForm;
	private DataRepository repo= new DataRepository();
	protected Logger log;
	
	/**
	 * @description Import basic Data to complete search form and sort results
	 * @author andrea.bertieri
	 */
	@DataProvider(name="searchFlightsData")
	public Object[][] createUsers(){		
		return new Object[][] {
		{repo.getSortFlightsOptions(),repo.getBasicFormInfo()}
		};
	}
	
	@DataProvider(name="choseFlightsData")
	public Object[][] createFlightsChoice(){		
		return new Object[][] {
		{repo.getSortFlightsOptions(),repo.getBasicFormInfo(),1,3}
		};
	}
	
	@BeforeMethod(alwaysRun=true)
	@Parameters("Browser")
	public void beforeSuite(String Browser, ITestContext ctx) {
		String testName= ctx.getCurrentXmlTest().getName();
		log= LogManager.getLogger(testName);
		factory=new BrowserDriverFactory(Browser, log);
		searcherForm= new FlightsSearcherForm(factory.getDriver(), log);
	}
	
	@AfterMethod(alwaysRun=true)
	public void afterSuite() {
		log.info("Close Driver");
		factory.getDriver().quit();
	}
	

}
