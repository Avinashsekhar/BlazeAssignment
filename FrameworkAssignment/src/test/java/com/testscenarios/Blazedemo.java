package com.testscenarios;

import org.testng.annotations.Test;

import com.objectrepository.Locators;
import com.utilities.CommonFunctions;
import com.utilities.Staticvariables;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;

public class Blazedemo extends Staticvariables {

	CommonFunctions cfn = new CommonFunctions();
	Locators obj = new Locators();

	@Test
	public void TC_001() throws IOException, InterruptedException {
		// Call the test data which is under property file
		Properties p = new Properties();
		FileInputStream fi = new FileInputStream(".\\TestData\\testdata.properties");
		p.load(fi);

		cfn.openURL(p.getProperty("baseURL"));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		cfn.selectByVisibleText(obj.blazedemo_departure_dropdown, p.getProperty("departureCity"));
		cfn.selectByVisibleText(obj.blazedemo_destination_dropdown, p.getProperty("destinationCity"));
		cfn.clickByAnyLocator(obj.blazedemo_FindFlights_button);
		
		Thread.sleep(6000);
		cfn.clickByAnyLocator(obj.blazedemo_ChooseThisFlight_button);
		Thread.sleep(6000);
	}

	@AfterMethod
	public void afterMethod(ITestResult res) throws Exception {
		cfn.takeScreenshotWithStatus(res);
	}

	@Parameters("browserName")
	@BeforeClass
	public void beforeClass(@Optional("Chrome") String browserName) {
		cfn.crossBrowserLaunch(browserName);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
