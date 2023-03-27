package com.ey.il.appium.core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class DriverFactory {

	public static ThreadLocal<AppiumDriver<MobileElement>> localDriver = new ThreadLocal<>();

	public static void setDriver(AppiumDriver<MobileElement> driver) {
		localDriver.set(driver);
	}

	public static AppiumDriver<MobileElement> getDriver() {
		return(localDriver.get());
	}

}
