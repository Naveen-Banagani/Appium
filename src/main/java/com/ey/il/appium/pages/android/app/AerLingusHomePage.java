package com.ey.il.appium.pages.android.app;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

// All th objects belonging to one page will be defined in java class
public class AerLingusHomePage extends BasePageObject {
// 1. Is to call the driver object from testcase to Pageobject file

	// Concatenate driver
	public AerLingusHomePage(AppiumDriver<MobileElement> driver) {
		super(driver);
	}

	

	@AndroidFindBy(id = "android:id/button3")
	private WebElement PrivacyButton;

	@AndroidFindBy(className = "android.widget.ImageButton")
	private WebElement MainButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Book a Flight']")
	private WebElement BookaFlight;

	public WebElement privacy() {
		System.out.println("Seleceting Got it");
		return PrivacyButton;
	}

	public WebElement mainButton() {
		System.out.println("Click on main Menu Option");
		return MainButton;
	}

	public WebElement bookFlight() {
		System.out.println("Click on Book a Flight Option");
		return BookaFlight;
	}

}
