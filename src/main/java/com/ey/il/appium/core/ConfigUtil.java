package com.ey.il.appium.core;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ConfigUtil {
	public static String TEST_PLATFORM;
	public static String NEW_COMMAND_TIMEOUT;
	public static int IMPLICIT_WAIT_TIME;
	public static String AUTOMATION_NAME_ANDROID;
	public static String APPLICATION_PATH_ANDROID;
	public static String BROWSER_NAME_ANDROID;
	public static String BROWSER_VERSION_ANDROID;
	public static String PLATFORM_NAME_ANDROID;
	public static String PLATFORM_VERSION_ANDROID;
	public static String DEVICE_NAME_ANDROID;
	public static String APP_PACKAGE_ANDROID;
	public static String APP_ACTIVITY_ANDROID;
	public static String DEVICE_UDID_ANDROID;
	public static String AUTOMATION_NAME_IOS;
	public static String PLATFORM_NAME_IOS;
	public static String PLATFORM_VERSION_IOS;
	public static String DEVICE_NAME_IOS;
	public static String APPLICATION_PATH_IOS;
	public static String UDID_IOS;
	public static String BUNDLEID_IOS;
	public static String BROWSERNAME_IOS;
	public static String USENEWWDA_IOS;
	public static String waitForQuiescence_ios;
	
	
	public static String APPIUM_PORT;
	public static String SERVER_IP;
	public static String APPIUM_JS;
	public static Boolean LOCAL_DEVICE;
	public static Boolean AUTO_SERVER_START;
	public static String SL_TO_API_KEY;
	public static String SL_TO_REMOTE_APPIUM_SERVER;
	public static String SL_TO_PLATFORM_NAME;
	public static String SL_TO_PLATFORM_VERSION;
	public static String SL_TO_DEVICE_NAME;
	public static Boolean SL_TO_PHONE_ONLY;
	public static Boolean SL_TO_TABLET_ONLY;
	public static Boolean SL_TO_PRIVATE_DEVICE_ONLY;
	public static String SL_TO_APPIUM_VERSION;
	public static Boolean SL_TO_NO_RESET;
	public static Boolean SL_TO_CACHE_DEVICE;
	public static String SL_TO_SESSION_CREATION_TIMEOUT;
	public static String SL_TO_APP_ID;
	public static String SL_TO_SUITE_NAME;
	public static String SL_TO_TEST_NAME;
	public static String SL_TO_APK_LOC;
	public static String SL_TO_USERNAME;
	public static String SL_TO_APK_FILEUPLOAD_SITE;
	public static Boolean SL_TO_AUTO_FILEUPLOAD;

	public static String BS_USERNAME;
	public static String BS_ACCESSKEY;
	public static String BS_DEVICE;
	public static String BS_OS_VERSION;
	public static String BS_PROJECT;
	public static String BS_BUILD;
	public static String BS_NAME;
	public static String BS_APP;
	public static Boolean BS_AUTO_APK_UPLOAD;
	public static String BS_APK_LOCATION;
	public static String BS_APK_FILEUPLOAD_SITE;

	public static Boolean CLOUD_BROWSERSTACK;
	public static Boolean CLOUD_SAUCELAB;
	public static Boolean CLOUD_AWS;

	//private static AppiumDriver<MobileElement> driver;
	private static DesiredCapabilities cap = new DesiredCapabilities();
	private static DesiredCapabilities cloudCapSL = new DesiredCapabilities();
	private static DesiredCapabilities cloudCapBS = new DesiredCapabilities();

	public static void setProperty() throws IOException, NumberFormatException{
		
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = Thread.currentThread().getContextClassLoader().getResourceAsStream("testSettings.properties");
			prop.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		LogUtil.info("Getting driver properties....");
		
		TEST_PLATFORM = prop.getProperty("test.platform");
		LOCAL_DEVICE = Boolean.parseBoolean(prop.getProperty("local.device.execution"));
		AUTO_SERVER_START = Boolean.parseBoolean(prop.getProperty("auto.server.start"));
		SL_TO_AUTO_FILEUPLOAD = Boolean.parseBoolean(prop.getProperty("auto.testobject.fileupload"));
		
		APPIUM_PORT=prop.getProperty("appium.server.port");
		SERVER_IP = prop.getProperty("appium.server.URL");
		APPIUM_JS = prop.getProperty("appium.js");
		NEW_COMMAND_TIMEOUT = prop.getProperty("new.command.timeout");
		
		IMPLICIT_WAIT_TIME=Integer.parseInt(prop.getProperty("implicit.wait"));
		
		AUTOMATION_NAME_ANDROID=prop.getProperty("automation.name.android");
		APPLICATION_PATH_ANDROID=prop.getProperty("application.path.android");
		BROWSER_NAME_ANDROID=prop.getProperty("browser.name.android");
		BROWSER_VERSION_ANDROID=prop.getProperty("browser.version.android");
		PLATFORM_NAME_ANDROID=prop.getProperty("platform.name.android");
		PLATFORM_VERSION_ANDROID=prop.getProperty("platform.version.android");
		DEVICE_NAME_ANDROID=prop.getProperty("device.name.android");
		APP_PACKAGE_ANDROID=prop.getProperty("app.package.android");
		APP_ACTIVITY_ANDROID=prop.getProperty("app.activity.android");
		DEVICE_UDID_ANDROID=prop.getProperty("device.udid.android");
		
		AUTOMATION_NAME_IOS=prop.getProperty("automation.name.ios");
		PLATFORM_NAME_IOS=prop.getProperty("platform.name.ios");
		PLATFORM_VERSION_IOS=prop.getProperty("platform.version.ios");
		DEVICE_NAME_IOS=prop.getProperty("device.name.ios");
		APPLICATION_PATH_IOS=prop.getProperty("application.path.ios");
		UDID_IOS =prop.getProperty("udid.ios");
		BUNDLEID_IOS =prop.getProperty("bundleid.ios");
		BROWSERNAME_IOS =prop.getProperty("browsername.ios");
		USENEWWDA_IOS=prop.getProperty("useNewWDA.ios");
		waitForQuiescence_ios=prop.getProperty("waitForQuiescence.ios");

		CLOUD_BROWSERSTACK=Boolean.parseBoolean(prop.getProperty("cloud.browserStack"));
		CLOUD_SAUCELAB=Boolean.parseBoolean(prop.getProperty("cloud.sauceLab"));
		CLOUD_AWS=Boolean.parseBoolean(prop.getProperty("cloud.aws"));

		SL_TO_API_KEY =prop.getProperty("sl.testobject_api_key");
		SL_TO_REMOTE_APPIUM_SERVER =prop.getProperty("sl.remote_appium_server");
		SL_TO_PLATFORM_NAME =prop.getProperty("sl.platformName");
		SL_TO_PLATFORM_VERSION =prop.getProperty("sl.platformVersion");
		SL_TO_DEVICE_NAME =prop.getProperty("sl.deviceName");
		SL_TO_PHONE_ONLY =Boolean.parseBoolean(prop.getProperty("sl.phoneOnly"));
		SL_TO_TABLET_ONLY =Boolean.parseBoolean(prop.getProperty("sl.tabletOnly"));
		SL_TO_PRIVATE_DEVICE_ONLY =Boolean.parseBoolean(prop.getProperty("sl.privateDevicesOnly"));
		SL_TO_APPIUM_VERSION =prop.getProperty("sl.appiumVersion");
		SL_TO_NO_RESET =Boolean.parseBoolean(prop.getProperty("sl.noReset"));
		SL_TO_CACHE_DEVICE =Boolean.parseBoolean(prop.getProperty("sl.testobject_cache_device"));
		SL_TO_SESSION_CREATION_TIMEOUT =prop.getProperty("sl.testobject_session_creation_timeout");
		SL_TO_APP_ID =prop.getProperty("sl.testobject_app_id");
		SL_TO_SUITE_NAME =prop.getProperty("sl.testobject_suite_name");
		SL_TO_TEST_NAME =prop.getProperty("sl.testobject_test_name");
		SL_TO_APK_LOC = prop.getProperty("sl.apk_loc");
		SL_TO_USERNAME = prop.getProperty("sl.saucelab_username");
		SL_TO_APK_FILEUPLOAD_SITE = prop.getProperty("sl.fileupload_site");

		BS_USERNAME=prop.getProperty("bs.userName");
		BS_ACCESSKEY=prop.getProperty("bs.accessKey");
		BS_DEVICE = prop.getProperty("bs.device");
		BS_OS_VERSION = prop.getProperty("bs.os.version");
		BS_PROJECT = prop.getProperty("bs.project");
		BS_BUILD = prop.getProperty("bs.build");
		BS_NAME = prop.getProperty("bs.name");
		BS_APP = prop.getProperty("bs.appURL");
		BS_AUTO_APK_UPLOAD = Boolean.parseBoolean(prop.getProperty("bs.auto.appUpload"));
		BS_APK_LOCATION = prop.getProperty("bs.apkLocation");
		BS_APK_FILEUPLOAD_SITE = prop.getProperty("bs.fileupload_Site");
		LogUtil.info("Getting driver properties done!");
		
	}
	
	private static void setAndroidCapability(){
		
		LogUtil.info("Setting android driver capability....");
		
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, ConfigUtil.AUTOMATION_NAME_ANDROID);
		//cap.setCapability(MobileCapabilityType.APP,Driver.userDir+ConfigUtil.APPLICATION_PATH_ANDROID);
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME,ConfigUtil.PLATFORM_NAME_ANDROID);
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION,ConfigUtil.PLATFORM_VERSION_ANDROID);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME,ConfigUtil.DEVICE_NAME_ANDROID);
		cap.setCapability("appPackage",ConfigUtil.APP_PACKAGE_ANDROID);
		cap.setCapability("appActivity",ConfigUtil.APP_ACTIVITY_ANDROID);
		cap.setCapability("udid",ConfigUtil.DEVICE_UDID_ANDROID);
		cap.setCapability("newCommandTimeout",ConfigUtil.NEW_COMMAND_TIMEOUT);
		cap.setCapability("unicodeKeyboard", true);
		cap.setCapability("resetKeyboard", true);
		cap.setCapability("noReset",false);
		cap.setCapability("autoGrantPermissions", true);
		LogUtil.info("Setting android driver capability done!");
		
	}
	private static void setAndroidChromeCapability(){

		LogUtil.info("Setting android driver capability....");

		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, ConfigUtil.AUTOMATION_NAME_ANDROID);
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME,ConfigUtil.PLATFORM_NAME_ANDROID);
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION,ConfigUtil.PLATFORM_VERSION_ANDROID);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME,ConfigUtil.DEVICE_NAME_ANDROID);
		cap.setCapability("browserName",ConfigUtil.BROWSER_NAME_ANDROID);
	//	cap.setCapability("version",ConfigUtil.BROWSER_VERSION_ANDROID);
		cap.setCapability("udid",ConfigUtil.DEVICE_UDID_ANDROID);
		cap.setCapability("newCommandTimeout",ConfigUtil.NEW_COMMAND_TIMEOUT);
		cap.setCapability("unicodeKeyboard", true);
		cap.setCapability("resetKeyboard", true);
		cap.setCapability("noReset",true);
		cap.setCapability("autoGrantPermissions", true);
		cap.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
		LogUtil.info("Setting android driver capability done!");

	}
	private static void setIOSCapability(){
		
		LogUtil.info("Setting iOS driver capability....");
		
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, ConfigUtil.AUTOMATION_NAME_IOS);
		//cap.setCapability(MobileCapabilityType.APP,Driver.userDir+ConfigUtil.APPLICATION_PATH_IOS);
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME,ConfigUtil.PLATFORM_NAME_IOS);
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION,ConfigUtil.PLATFORM_VERSION_IOS);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME,ConfigUtil.DEVICE_NAME_IOS);
		cap.setCapability("newCommandTimeout",ConfigUtil.NEW_COMMAND_TIMEOUT);
		cap.setCapability("udid",ConfigUtil.UDID_IOS);
		cap.setCapability("bundleId",ConfigUtil.BUNDLEID_IOS);
		//cap.setCapability("useNewWDA",ConfigUtil.USENEWWDA_IOS);
		//cap.setCapability("waitForQuiescence.ios",ConfigUtil.waitForQuiescence_ios);
		cap.setCapability("unicodeKeyboard", true);
		cap.setCapability("resetKeyboard", true);
		
		LogUtil.info("Setting iOS driver capability done!");
		
	}

	private static void setIOSSafariCapability(){

		LogUtil.info("Setting iOS driver capability....");

		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, ConfigUtil.AUTOMATION_NAME_IOS);
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME,ConfigUtil.PLATFORM_NAME_IOS);
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION,ConfigUtil.PLATFORM_VERSION_IOS);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME,ConfigUtil.DEVICE_NAME_IOS);
		cap.setCapability("newCommandTimeout",ConfigUtil.NEW_COMMAND_TIMEOUT);
		cap.setCapability("udid",ConfigUtil.UDID_IOS);
		cap.setCapability("browserName",ConfigUtil.BROWSERNAME_IOS);
		cap.setCapability("waitForQuiescence.ios",ConfigUtil.waitForQuiescence_ios);
		cap.setCapability("unicodeKeyboard", true);
		cap.setCapability("resetKeyboard", true);

		LogUtil.info("Setting iOS Safari capability done!");

	}
	
	private static void setCloudCapability() {
		if(CLOUD_SAUCELAB){

			LogUtil.info("Setting TestObject driver capability....");

			cloudCapSL.setCapability("sl.testobject_api_key", ConfigUtil.SL_TO_API_KEY);
			cloudCapSL.setCapability("sl.platformName", ConfigUtil.SL_TO_PLATFORM_NAME);
			//cloudCap.setCapability("platformVersion", ConfigUtil.TO_PLATFORM_VERSION);
			//cloudCap.setCapability("tabletOnly", ConfigUtil.TO_TABLET_ONLY);
			//cloudCap.setCapability("phoneOnly", ConfigUtil.TO_PHONE_ONLY);
			//cloudCap.setCapability("privateDevicesOnly", ConfigUtil.TO_PRIVATE_DEVICE_ONLY);
			//cloudCap.setCapability("deviceName", ConfigUtil.TO_DEVICE_NAME);
			cloudCapSL.setCapability("sl.appiumVersion", ConfigUtil.SL_TO_APPIUM_VERSION);
			//cloudCap.setCapability("noReset", ConfigUtil.TO_NO_RESET);
			//cloudCap.setCapability("testobject_cache_device", ConfigUtil.TO_CACHE_DEVICE);
			//cloudCap.setCapability("testobject_session_creation_timeout", ConfigUtil.TO_SESSION_CREATION_TIMEOUT);
			cloudCapSL.setCapability("sl.testobject_app_id", ConfigUtil.SL_TO_APP_ID);
			cloudCapSL.setCapability("sl.testobject_suite_name", ConfigUtil.SL_TO_SUITE_NAME);
			cloudCapSL.setCapability("sl.testobject_test_name", ConfigUtil.SL_TO_TEST_NAME);

			LogUtil.info("Setting TestObject driver capability done!");
		}
		if(CLOUD_BROWSERSTACK){

			LogUtil.info("Setting BrowserStack capability....");

			cloudCapBS.setCapability("device", BS_DEVICE);
			cloudCapBS.setCapability("os_version", BS_OS_VERSION);
			cloudCapBS.setCapability("project", BS_PROJECT);
			cloudCapBS.setCapability("build", BS_BUILD);
			cloudCapBS.setCapability("name", BS_NAME);
			cloudCapBS.setCapability("autoGrantPermissions", "true");
			if(!BS_AUTO_APK_UPLOAD)
				cloudCapBS.setCapability("app", BS_APP);

			LogUtil.info("Setting BrowserStack capability done!");
		}

		if(CLOUD_AWS){

			LogUtil.info("Setting AWS capability....");
			LogUtil.info("Setting AWS capability done!");
		}
	}
	
	public static void setDriver() throws MalformedURLException{
		if(CLOUD_AWS){

		}
		else{
			URL serverURL;
			LogUtil.info("Getting driver....");
			serverURL = new URL("http://"+SERVER_IP+":"+ConfigUtil.APPIUM_PORT+"/wd/hub");
			if(TEST_PLATFORM.equalsIgnoreCase("android")) {
				setAndroidCapability();
				DriverFactory.setDriver(new AndroidDriver<>(serverURL,cap));
			}if(TEST_PLATFORM.equalsIgnoreCase("android-chrome")) {
				setAndroidChromeCapability();
				DriverFactory.setDriver( new AppiumDriver<MobileElement>(serverURL, cap));
			}else if(TEST_PLATFORM.equalsIgnoreCase("ios")) {
				setIOSCapability();
				DriverFactory.setDriver(new IOSDriver<>(serverURL,cap));
			}else if(TEST_PLATFORM.equalsIgnoreCase("ios-safari")) {
				setIOSSafariCapability();
				DriverFactory.setDriver(new AppiumDriver<>(serverURL,cap));
			}else {
				LogUtil.info("The Framework not supporting test platform '"+TEST_PLATFORM+"'. It should be iOS or Android. Please update the config!");
				return;
			}
			LogUtil.info("Getting driver done!");
		}
	}
	
	public static void setCloudDriver_SauceLab() throws MalformedURLException{
		URL serverURL;
		setCloudCapability();
		LogUtil.info("Getting TestObject driver....");
		LogUtil.info("Preparing live view on TestObject...");
		LogUtil.info("The device for your Appium session is being allocated.");
		LogUtil.info("This usually takes about 30 seconds, but can take longer if the device is in use.");
		LogUtil.info("When the device is ready, you can watch the execution on TestObject");
		serverURL = new URL(ConfigUtil.SL_TO_REMOTE_APPIUM_SERVER);
		if(TEST_PLATFORM.equalsIgnoreCase("android")) {
			DriverFactory.setDriver(new AndroidDriver<>(serverURL, cloudCapSL));
		}else if(TEST_PLATFORM.equalsIgnoreCase("ios")) {
			DriverFactory.setDriver(new IOSDriver<>(serverURL, cloudCapSL));
		}
		LogUtil.info("Getting TestObject driver done!");
	}

	public static void setCloudDriver_BrowserStack() throws MalformedURLException{
		URL serverURL;
		setCloudCapability();
		LogUtil.info("Getting BrowserStack driver....");
		LogUtil.info("Preparing live view on BrowserStack...");
		LogUtil.info("The device for your Appium session is being allocated.");
		LogUtil.info("This usually takes about 30 seconds, but can take longer if the device is in use.");
		LogUtil.info("When the device is ready, you can watch the execution on BrowserStack");
		serverURL = new URL("https://"+BS_USERNAME+":"+BS_ACCESSKEY+"@hub-cloud.browserstack.com/wd/hub");
		if(TEST_PLATFORM.equalsIgnoreCase("android")) {
			DriverFactory.setDriver(new AndroidDriver<>(serverURL, cloudCapBS));
		}else if(TEST_PLATFORM.equalsIgnoreCase("ios")) {
			DriverFactory.setDriver(new IOSDriver<>(serverURL, cloudCapBS));
		}
		LogUtil.info("Getting BrowserStack driver done!");
	}

}
