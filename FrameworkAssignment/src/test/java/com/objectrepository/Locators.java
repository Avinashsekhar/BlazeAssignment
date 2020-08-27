package com.objectrepository;

import org.openqa.selenium.By;

public class Locators {

	public final By blazedemo_departure_dropdown = By.xpath("//*[@name='fromPort']");
	public final By blazedemo_destination_dropdown = By.xpath("//*[@name='toPort']");
	public final By blazedemo_FindFlights_button = By.xpath("//*[@type='submit']");
	public final By blazedemo_ChooseThisFlight_button = By.xpath("//tr[1]//td[1]//input[1]");
	public final By blazedemo_Name_Editbox  = By.xpath("//*[@id='inputName']");
	public final By blazedemo_Address_Editbox  = By.xpath("//*[@id='address']");
	public final By blazedemo_City_Editbox  = By.xpath("//*[@id='city']");
	public final By blazedemo_State_Editbox  = By.xpath("//*[@id='state']");
	public final By blazedemo_zipCode_Editbox  = By.xpath("//*[@id='zipCode']");
	public final By blazedemo_creditCardNumber_Editbox = By.xpath("//*[@id='creditCardNumber']");
	public final By blazedemo_cardType_dropdown = By.xpath("//*[@id='cardType']");
	public final By blazedemo_creditCardMonth_Editbox = By.xpath("//*[@id='creditCardMonth']");
	public final By blazedemo_creditCardYear_Editbox = By.xpath("//*[@id='creditCardYear']");
	public final By blazedemo_nameOnCard_Editbox= By.xpath("//*[@id='nameOnCard']");
	public final By blazedemo_PurchaseFlight_button = By.xpath("//*[@type='submit']");
	
	public final By blazedemo_Thankyou_Message = By.xpath("//h1[contains(text(),'Thank you for your purchase today!')]");
	
	
	public final By blazedemo_BookingID = By.xpath("/html[1]/body[1]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[2]");
	public final By blazedemo_JSonData = By.xpath("/html[1]/body[1]/div[2]/div[1]/pre[1]");

	
	
	
	
	
}
