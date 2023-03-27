package com.ey.il.appium.pages.android.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

// All th objects belonging to one page will be defined in java class
public class AerLingusPassengerDetailsPage extends BasePageObject {
// 1. Is to call the driver object from testcase to Pageobject file

	// Concatenate driver
	public AerLingusPassengerDetailsPage(AppiumDriver<MobileElement> driver) {
		super(driver);
	}



	@AndroidFindBy(id = "com.aerlingus.mobile:id/title_spinner")
	private WebElement titleSelection;

	@AndroidFindBy(xpath = "//*[@text='Mr']")
	private WebElement selectMr;

	@AndroidFindBy(xpath = "//*[@text='First name']")
	private WebElement firstName;

	@AndroidFindBy(xpath = "//*[@text='Family name']")
	private WebElement familyName;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Country']")
	private WebElement country;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Ireland (+353)']")
	private WebElement selectCountry;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Prefix']")
	private WebElement prefix;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Local Number']")
	private WebElement localNumber;

	@AndroidFindBy(xpath = "//*[@text='Email Address']")
	private WebElement emailAddress;

	@AndroidFindBy(id = "com.aerlingus.mobile:id/continue_btn_inner")
	private WebElement continueButton;

	public WebElement titleSelection() {
		System.out.println("Click on title");
		return titleSelection;
	}

	public WebElement selectMr() {
		System.out.println("select Mr");
		return selectMr;
	}

	public WebElement firstName() {
		System.out.println("Enter first name");
		return firstName;
	}

	public WebElement familyName() {
		System.out.println("Enter family name");
		return familyName;
	}

	public WebElement country() {
		System.out.println("click on country icon");
		return country;
	}

	public WebElement selectCountry() {
		System.out.println("select country code");
		return selectCountry;
	}

	public WebElement prefix() {
		System.out.println("Enter Prefix");
		return prefix;
	}

	public WebElement localNumber() {
		System.out.println("Enter Local Numner");
		return localNumber;
	}

	public WebElement emailAddress() {
		System.out.println("Enter Email");
		return emailAddress;
	}

	public WebElement continueButton() {
		System.out.println("Click on continue button");
		return continueButton;
	}

	public void scrollToView(String text) {
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))"));
	}
}
