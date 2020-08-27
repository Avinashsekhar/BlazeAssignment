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
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;

public class Blazedemo extends Staticvariables {

	CommonFunctions cfn = new CommonFunctions();
	Locators obj = new Locators();

	@Test
	public void TC_001_FilghtSearch() throws IOException, InterruptedException {
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

	@Test
	public void TC_002_UserDetails() throws IOException, InterruptedException {
		// Call the test data which is under property file
		Properties p = new Properties();
		FileInputStream fi = new FileInputStream(".\\TestData\\testdata.properties");
		p.load(fi);

		Thread.sleep(6000);
		cfn.sendkeysByAnyLocator(obj.blazedemo_Name_Editbox, p.getProperty("Name"));

		cfn.sendkeysByAnyLocator(obj.blazedemo_Address_Editbox, p.getProperty("Address"));
		cfn.sendkeysByAnyLocator(obj.blazedemo_State_Editbox, p.getProperty("State"));
		cfn.sendkeysByAnyLocator(obj.blazedemo_City_Editbox, p.getProperty("City"));
		cfn.sendkeysByAnyLocator(obj.blazedemo_zipCode_Editbox, p.getProperty("ZipCode"));

		cfn.selectByVisibleText(obj.blazedemo_cardType_dropdown, p.getProperty("cardType_dropdown"));

		cfn.sendkeysByAnyLocator(obj.blazedemo_creditCardNumber_Editbox, p.getProperty("creditCardNumber"));
		cfn.sendkeysByAnyLocator(obj.blazedemo_creditCardMonth_Editbox, p.getProperty("creditCardMonth"));
		cfn.sendkeysByAnyLocator(obj.blazedemo_creditCardYear_Editbox, p.getProperty("creditCardYear"));
		cfn.sendkeysByAnyLocator(obj.blazedemo_nameOnCard_Editbox, p.getProperty("nameOnCard"));

		cfn.clickByAnyLocator(obj.blazedemo_PurchaseFlight_button);
		Thread.sleep(6000);

	}

	@Test
	public void TC_003_VerifyAndPrintMessages() throws IOException, InterruptedException {
		// Call the test data which is under property file
		Properties p = new Properties();
		FileInputStream fi = new FileInputStream(".\\TestData\\testdata.properties");
		p.load(fi);
		System.out.println("********************************************************");
		Thread.sleep(6000);
		String actualMessage = driver.findElement(obj.blazedemo_Thankyou_Message).getText();
		Assert.assertEquals(actualMessage, p.getProperty("successMessage"), "Success Message is not matching");
		System.out.println("********************************************************");
		Thread.sleep(6000);
		String bookingID = driver.findElement(obj.blazedemo_BookingID).getText();
		System.out.println(bookingID);
		System.out.println("********************************************************");
		Thread.sleep(6000);
		String JSonData = driver.findElement(obj.blazedemo_JSonData).getText();
		System.out.println(JSonData);
		System.out.println("********************************************************");
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
