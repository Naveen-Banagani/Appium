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
public class AerLingusPaymentPage extends BasePageObject {
// 1. Is to call the driver object from testcase to Pageobject file
	
	//Concatenate driver
	public AerLingusPaymentPage(AppiumDriver<MobileElement> driver)
	{
		super(driver);
	}
	
	

	
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Card Type']")
	private WebElement cardType;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='MasterCard']")
	private WebElement selectCardType;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Card Number']")
	private WebElement cardNumber;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Expiry Date']")
	private WebElement expiryDate;

	@AndroidFindBy(id = "android:id/button1")
	private WebElement button;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='CVV Number']")
	private WebElement cvv;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='First Name']")
	private WebElement firstName;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Family Name']")
	private WebElement familyName;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Address Line One']")
	private WebElement addressLineOne;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Address Line Two']")
	private WebElement addressLineTwo;
	

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='City/Town']")
	private WebElement city;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Postal Code']")
	private WebElement postalCode;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='State/Province']")
	private WebElement state;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Country']")
	private WebElement country;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Ireland']")
	private WebElement selectCountry;

	public WebElement cardType() {
		System.out.println("Click on title");
		return cardType;
	}

	public WebElement selectCardType() {
		System.out.println("select Mr");
		return selectCardType;
	}

	public WebElement cardNumber() {
		System.out.println("Enter first name");
		return cardNumber;
	}

	public WebElement expiryDate() {
		System.out.println("Enter family name");
		return expiryDate;
	}

	public WebElement button() {
		System.out.println("click on country icon");
		return button;
	}

	public WebElement cvv() {
		System.out.println("select country code");
		return cvv;
	}

	public WebElement firstName() {
		System.out.println("Enter Prefix");
		return firstName;
	}

	public WebElement familyName() {
		System.out.println("Enter Local Numner");
		return familyName;
	}

	public WebElement addressLineOne() {
		System.out.println("Enter Email");
		return addressLineOne;
	}

	public WebElement addressLineTwo() {
		System.out.println("Click on continue button");
		return addressLineTwo;
	}
	
	public WebElement city() {
		System.out.println("select country code");
		return city;
	}

	public WebElement postalCode() {
		System.out.println("Enter Prefix");
		return postalCode;
	}

	public WebElement state () {
		System.out.println("Enter Local Numner");
		return state;
	}

	public WebElement country() {
		System.out.println("click on country");
		return country;
	}

	public WebElement selectCountry() {
		System.out.println("select country");
		return selectCountry;
	}
	
	
}
