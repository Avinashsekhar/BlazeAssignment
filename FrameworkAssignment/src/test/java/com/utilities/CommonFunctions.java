package com.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CommonFunctions extends Staticvariables {
//	WebDriver driver;
	// Constructor
	public CommonFunctions() {
		projectDir = System.getProperty("user.dir");
		// screenshotPath = projectDir+"\\Screenshots\\";
		File f = new File(projectDir + "\\Screenshots\\");
		// Check whether the Screenshot folder is available or not in your project
		// location?
		if (f.exists()) {
			System.out.println("Screenshot folder is available already****");
		} else {
			// System will create a file using mkdir method
			f.mkdir();
			System.out.println("System has created Screenshot folder##########****");
		}
	}

	/****************** Browser Launch **********************/
	public void chromeBrowserLaunch() {
//		WebDriverManager.chromedriver().version("81").setup();
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	public void firefoxBrowserLaunch() {
		WebDriverManager.firefoxdriver().version("76").setup();
		driver = new FirefoxDriver();
	}

	public void ieBrowserLaunch() {
		WebDriverManager.iedriver().setup();
		driver = new InternetExplorerDriver();
	}

	public void crossBrowserLaunch(String browserName) {
		if (browserName.equalsIgnoreCase("Chrome")) {
			// Chrome
//			System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
//			WebDriverManager.chromedriver().setup();
			WebDriverManager.chromedriver().version("83").setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			// Firefox browser
			System.setProperty("webdriver.gecko.driver", ".\\BrowserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("ie")) {
			// IE
			System.setProperty("webdriver.ie.driver", ".\\BrowserDrivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} else {
			System.out.println("Enter valid browser name ****************");
		}

	}

	/****************** Time Stamp **********************/
	public String timeStamp() {
		Date d = new Date();
		DateFormat df = new SimpleDateFormat("ddMMMyyyy_HHmmss");
		String timeStamp = df.format(d);
		System.out.println(" My timeStamp is: " + timeStamp);
		return timeStamp;
	}

	/*****************
	 * TakeScreenShot
	 * 
	 * @throws Exception
	 ***********************/
	public void takeScreenshot(String name) throws Exception {
		File from = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(from, new File(".\\Screenshots\\" + name + timeStamp() + ".PNG"));
	}

	/*************
	 * takeScreenshot pass or fail with iTestResults
	 * 
	 * @throws Exception
	 ******************/
	public void takeScreenshotWithStatus(ITestResult res) throws Exception {
		// Fail_PackageName_ClassName_MethodName_TimeStamp.PNG
		if (res.getStatus() == ITestResult.SUCCESS) {
			// Get the runtimeClassName
			className = res.getTestClass().getName().trim();
			methodName = res.getName().trim();

			File from = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(from,
					new File(".\\Screenshots\\" + "Pass_" + className + methodName + timeStamp() + ".PNG"));
		}
		if (res.getStatus() == ITestResult.FAILURE) {
			// Get the runtimeClassName
			className = res.getTestClass().getName().trim();
			methodName = res.getName().trim();

			File from = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(from,
					new File(".\\Screenshots\\" + "Fail_" + className + "_" + methodName + "_" + timeStamp() + ".PNG"));
		}

	}

	/****************** SendKeys By Any Locator *************************/
	public void sendkeysByAnyLocator(By locator, String inputData) {
		try {
			WebElement ele = driver.findElement(locator);
			// Is locator display?
			if (ele.isDisplayed()) {
				// Is locator enabled?
				if (ele.isEnabled()) {
					highlightTheElement(ele);
					// Clear the existing data
					ele.clear();
					// send the data
					ele.sendKeys(inputData);

				} else {
					System.out.println("The Element is disable state, Please check it once**********");
				}

			} else {
				System.out.println("The Element is NOT displayed on the DOM, Please check it once**********");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*************** Click By any locator ****************************/
	public void clickByAnyLocator(By locator) {
		try {
			WebElement ele = driver.findElement(locator);
			// element is Displayed?
			if (ele.isDisplayed()) {
				// element is enabled?
				if (ele.isEnabled()) {
					highlightTheElement(ele);
					ele.click();
				} else {
					System.out.println("Element is disabled/NOT enabled state****");
				}

			} else {
				System.out.println("Element is NOT displayed****");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**************** Highlight the element ******************/
	public void highlightTheElement(WebElement ele) {
		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		jse.executeScript("arguments[0].style.border='6px groove green'", ele);
	}

	/*************
	 * Scroll to element
	 * 
	 * @throws Exception
	 *********/
	public void scrollToElement(WebElement ele) throws Exception {
		System.out.println("Scroll to Element ***");
		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		jse.executeScript("arguments[0].scrollIntoView();", ele);
		Thread.sleep(3000);
	}

//	/************* Scroll to element to bottom *********/
//	public void scrollToElementBottom(WebElement ele) {
//		System.out.println("Scroll to Element ***");
//		JavascriptExecutor jse = ((JavascriptExecutor) driver);
//		jse.executeScript("arguments[0].scrollIntoView(true);", ele);
//	}

	/***************** Open URL ************/
	public void openURL(String URL) {
		driver.navigate().to(URL);
		driver.manage().window().maximize();
		// Want to delete cookies
//		driver.manage().deleteAllCookies();

	}

	/********************* dropdown *******************/
	public void selectByVisibleText(By locater, String text) {
		try {
			WebElement element = driver.findElement(locater);
			Select dropdown = new Select(element);
			dropdown.selectByVisibleText(text);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
