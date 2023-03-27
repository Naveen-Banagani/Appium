package com.ey.il.appium.core;

import io.appium.java_client.AppiumDriver;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;

public abstract class Driver {

	protected static AppiumDriver driver;
	public static Properties propRepo = new Properties();
	public static final String userDir = System.getProperty("user.dir");

	private static String allSettings = userDir + "/src/test/resources/db/AppTest.db";

	private final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
	private final int TIMEOUT = 30;

	public static String getAllSettings() {
		return allSettings;
	}

	public static void setAllSettings(String allSettings) {
		Driver.allSettings = allSettings;
	}

	static {
		try {
			ConfigUtil.setProperty();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeSuite
	public void setUp() throws IOException, InterruptedException, NumberFormatException {
		if(ConfigUtil.CLOUD_AWS){
			URL url = new URL(URL_STRING);
			driver = new AppiumDriver<>(url, new DesiredCapabilities());
			driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		}
		else{
			if (ConfigUtil.LOCAL_DEVICE) {

				if (ConfigUtil.AUTO_SERVER_START) {
					RunAppium.appiumStart(ConfigUtil.SERVER_IP, ConfigUtil.APPIUM_PORT, ConfigUtil.APPIUM_JS);
				} else if (!ConfigUtil.AUTO_SERVER_START) {
					LogUtil.info("Please start appium server manually to continue execution.!");
					LogUtil.info("Manually start appium server on " + ConfigUtil.SERVER_IP + ":" + ConfigUtil.APPIUM_PORT);
					LogUtil.info("Use following command to start 'appium -a " + ConfigUtil.SERVER_IP + " -p "
							+ ConfigUtil.APPIUM_PORT + "'");
				}
				if (driver == null) {
					ConfigUtil.setDriver();

					driver = DriverFactory.getDriver();
					driver.manage().timeouts().implicitlyWait(ConfigUtil.IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
				}
			} else if (!ConfigUtil.LOCAL_DEVICE) {

				if (driver == null) {
					if (ConfigUtil.SL_TO_AUTO_FILEUPLOAD) {
						try {
							int toAppVersion = AppUploader.curlCommand_SauceLab(ConfigUtil.SL_TO_APK_LOC, ConfigUtil.SL_TO_USERNAME,
									ConfigUtil.SL_TO_API_KEY, ConfigUtil.SL_TO_APK_FILEUPLOAD_SITE);
							LogUtil.info(
									"Successfully uploaded the App.The newly uploaded apk version number:" + toAppVersion);
						} catch (Exception e) {
							LogUtil.error("Error on APK upload. Test execution terminating - " + e.getMessage());
							return;
						}
					}
					if (ConfigUtil.BS_AUTO_APK_UPLOAD) {
						try {
							String toAppVersion = AppUploader.curlCommand_BrowserStack(ConfigUtil.BS_APK_LOCATION, ConfigUtil.BS_USERNAME,
									ConfigUtil.BS_ACCESSKEY, ConfigUtil.BS_APK_FILEUPLOAD_SITE);
							LogUtil.info(
									"Successfully uploaded the App.The newly uploaded apk version number:" + toAppVersion);
						} catch (Exception e) {
							LogUtil.error("Error on APK upload. Test execution terminating - " + e.getMessage());
							return;
						}
					}
					if(ConfigUtil.CLOUD_SAUCELAB)
						ConfigUtil.setCloudDriver_SauceLab();
					else if (ConfigUtil.CLOUD_BROWSERSTACK)
						ConfigUtil.setCloudDriver_BrowserStack();
					driver = DriverFactory.getDriver();
					driver.manage().timeouts().implicitlyWait(ConfigUtil.IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
				}
			}
		}

	}

	@AfterSuite
	public static void tearDown() throws IOException {
		//rep.flushReport();
		driver.quit();
		if (ConfigUtil.AUTO_SERVER_START && ConfigUtil.LOCAL_DEVICE) {
			RunAppium.appiumStop();
			LogUtil.info("Appium server stopped!");
		}

	}

	public void impliWait() {
		driver.manage().timeouts().implicitlyWait(ConfigUtil.IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
	}

	public static void manageTimeOut(int timeInSecond) {
		if (timeInSecond < 2) {
			timeInSecond = 2;
		}
		driver.manage().timeouts().implicitlyWait(timeInSecond, TimeUnit.SECONDS);
	}

	public abstract void preTestSetUp();

	public abstract void modifyResultFile(ITestResult result);

}
