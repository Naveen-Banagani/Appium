package com.ey.il.appium.core;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.io.IOException;

public class RunAppium {

	private static AppiumServiceBuilder builder;
	private static AppiumDriverLocalService appiumService;

	public static void appiumStart(String serverURL, String APPIUM_PORT, String APPIUM_JS) throws IOException {

		LogUtil.info("Starting appium server on "+serverURL+":"+APPIUM_PORT+", Please wait...!");

		try {
			builder = new AppiumServiceBuilder();
			builder.withAppiumJS(new File(APPIUM_JS));
			builder.withIPAddress(serverURL);
			builder.usingPort(Integer.parseInt(APPIUM_PORT));
			builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
			//builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");

			//Start the server with the builder
			appiumService = AppiumDriverLocalService.buildService(builder);
			appiumService.start();
		}catch(Exception e) {
			LogUtil.error("Error on Appium server start! : "+e.getMessage());
		}

		LogUtil.info("Appium server started!");
	}

	public static void appiumStop(){

		LogUtil.info("Stoping appium server....");
		appiumService.stop();
		LogUtil.info("Stopped appium server !");

	}

}
