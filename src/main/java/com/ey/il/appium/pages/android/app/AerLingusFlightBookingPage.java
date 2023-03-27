package com.ey.il.appium.pages.android.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

// All th objects belonging to one page will be defined in java class
public class AerLingusFlightBookingPage extends BasePageObject {
// 1. Is to call the driver object from testcase to Pageobject file

	// Concatenate driver
	public AerLingusFlightBookingPage(AppiumDriver<MobileElement> driver) {
		super(driver);
	}

	@AndroidFindBy(xpath = "//*[@text='ONE WAY']")
	private WebElement oneWay;

	@AndroidFindBy(id = "com.aerlingus.mobile:id/book_a_flight_origin")
	private WebElement originCity;

	@AndroidFindBy(id = "com.aerlingus.mobile:id/search_button")
	private WebElement citySearch;

	@AndroidFindBy(id = "com.aerlingus.mobile:id/search_src_text")
	private WebElement cityName;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Dublin, Ireland (DUB)']")
	private WebElement originCitySelect;

	@AndroidFindBy(id = "com.aerlingus.mobile:id/book_a_flight_destination")
	private WebElement destinationCity;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Paris, France (CDG)']")
	private WebElement destinationCitySelect;

	@AndroidFindBy(id = "com.aerlingus.mobile:id/book_a_flight_choose_dates")
	private WebElement chooseDateButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='04 December 2021']")
	private WebElement selectDate;

	@AndroidFindBy(id = "com.aerlingus.mobile:id/continue_component_detailed_sub_total_description")
	private WebElement continueButton;

	public WebElement oneway() {
		System.out.println("Select One Way");
		return oneWay;
	}

	public WebElement originCity() {
		System.out.println("select origin city");
		return originCity;
	}

	public WebElement citySearch() {
		System.out.println("select on search");
		return citySearch;
	}

	public WebElement cityName() {
		System.out.println("Enter city name");
		return cityName;
	}

	public WebElement originCitySelect() {
		System.out.println("select origin city");
		return originCitySelect;
	}

	public WebElement destinationCity() {
		System.out.println("select destination icon");
		return destinationCity;
	}

	public WebElement destinationCitySelect() {
		System.out.println("select destination city");
		return destinationCitySelect;
	}

	public WebElement chooseDateButton() {
		System.out.println("click on choose date");
		return chooseDateButton;
	}

	public WebElement selectDate() {
		System.out.println("Selecet the date");
		return selectDate;
	}

	public WebElement continueButton() {
		System.out.println("Click on continue button");
		return continueButton;
	}

}
