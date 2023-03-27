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
public class AerLingusContinuePage extends BasePageObject {
// 1. Is to call the driver object from testcase to Pageobject file
	
	//Concatenate driver
	public AerLingusContinuePage(AppiumDriver<MobileElement> driver)
	{
		super(driver);
	}
	
	
	@AndroidFindBy(id = "com.aerlingus.mobile:id/continue_btn_inner")
	private WebElement continueButton;
	
	
	public WebElement continueButton() throws InterruptedException {
		System.out.println("Click on continue button");
		Thread.sleep(3000);
		return continueButton;
	}
}
