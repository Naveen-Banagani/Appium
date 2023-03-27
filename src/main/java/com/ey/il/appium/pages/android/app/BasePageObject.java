package com.ey.il.appium.pages.android.app;

import com.ey.il.appium.core.FrameworkUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class BasePageObject {
	
	AppiumDriver<MobileElement> driver = null;
	FrameworkUtils lib = new FrameworkUtils();
	
	public BasePageObject(AppiumDriver<MobileElement> driver) {
		this.driver =driver;
		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
	}
	
}
