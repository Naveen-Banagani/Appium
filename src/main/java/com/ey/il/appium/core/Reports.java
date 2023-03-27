package com.ey.il.appium.core;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

public class Reports {
	private static ExtentReports report = null;
	protected static ExtentTest test=null;
	public Reports(){
		report = new ExtentReports(getReportName(), false,NetworkMode.OFFLINE);
	}
	public static String strReportName, strReportFolderDate,strReportFileDate;

	public void startReport(String testcaseDesc) {
		test= report.startTest(testcaseDesc);
	}

	public void endReport() {
		report.endTest(test);
	}

	public void logInfo(LogStatus logStatus, String details) {
		test.log(logStatus, details);
	}
	public void logInfo(LogStatus logStatus, String details, boolean takeScreenshot,AppiumDriver driver) {
		String screenshotPath = takeScreenShot(details,driver);
		test.log(logStatus, details+test.addScreenCapture(screenshotPath));
	}
	public void localSystemInfo() {
		
		if(ConfigUtil.TEST_PLATFORM.equalsIgnoreCase("android")) {
			report.addSystemInfo("Mobile OS", ConfigUtil.PLATFORM_NAME_ANDROID);
			report.addSystemInfo("Testing Device Name", ConfigUtil.DEVICE_NAME_ANDROID);
			report.addSystemInfo("AUT Package Name", ConfigUtil.APP_PACKAGE_ANDROID);
			report.addSystemInfo("AUT Activity Name", ConfigUtil.APP_ACTIVITY_ANDROID);
		}else if(ConfigUtil.TEST_PLATFORM.equalsIgnoreCase("ios")) {
			report.addSystemInfo("Mobile OS", ConfigUtil.PLATFORM_NAME_IOS);
			report.addSystemInfo("Testing Device Name", ConfigUtil.DEVICE_NAME_IOS);
		}
		report.addSystemInfo("Framework Version", "1802019");
	}

	public void cloudSystemInfo() {
		report.addSystemInfo("MobileTAFVersion", "1802019");
		report.addSystemInfo("TestObject Appium Version", ConfigUtil.SL_TO_APPIUM_VERSION);
		report.addSystemInfo("TestObject OS", ConfigUtil.SL_TO_PLATFORM_NAME);
		report.addSystemInfo("TestObject URL", ConfigUtil.SL_TO_REMOTE_APPIUM_SERVER);
	}

	public void flushReport() {
		report.flush();
	}

	public void addScreenshot(AppiumDriver<MobileElement> driver) {
		String screenPath = FrameworkUtils.captureScreenShot(getClass().getSimpleName(),driver);
		String image = test.addScreenCapture(screenPath);
		test.log(LogStatus.INFO, "Test" ,image);
	}

	public static String getReportName() {

		DateFormat onlyDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date onlyDate = new Date();
		String onlyDisplayDate = onlyDateFormat.format(onlyDate).toString();

		DateFormat fullDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		Date fullDate = new Date();
		String fullDisplayDate = fullDateFormat.format(fullDate).toString();

		String reportName = "./test-output/"+onlyDisplayDate+"/test-report_"+ConfigUtil.TEST_PLATFORM+"_"+ConfigUtil.DEVICE_NAME_ANDROID+"-"+ConfigUtil.PLATFORM_VERSION_ANDROID+"_"+fullDisplayDate+".html";
		strReportName = reportName;
		strReportFolderDate =onlyDisplayDate;
		strReportFileDate = fullDisplayDate;
		return reportName;

	}

	public String reportResult(ITestResult result,AppiumDriver driver) {
		String testMethodName = result.getMethod().getMethodName();
		if(result.getStatus() == ITestResult.FAILURE){
			logInfo(LogStatus.FAIL, "Test Case Failed is "+testMethodName);
			logInfo(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
			logInfo(LogStatus.FAIL, testMethodName,true,driver);
		}else if(result.getStatus() == ITestResult.SKIP){
			logInfo(LogStatus.SKIP, "Test Case Skipped is "+testMethodName);
		}else {
			logInfo(LogStatus.PASS, "Test Case PASSED is "+testMethodName);
		}
		return testMethodName;
	}

	// Take ScreenShot of page and return the path
	public synchronized static String takeScreenShot(String screenshotName, AppiumDriver<MobileElement> driver) {
		DateFormat df = new SimpleDateFormat("dd-MM-yy_HHmmss");
		Date dateobj = new Date();
		String currentDate = df.format(dateobj).toString();
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenFileLocation = Driver.userDir + "/test-output/"+strReportFolderDate+"/screenshots/"+strReportFileDate+"_" + screenshotName + currentDate + ".jpg";
		File targetFile = new File(screenFileLocation);
		try {
			FileUtils.copyFile(src, targetFile, true);
		} catch (IOException e) {
			LogUtil.error("Error on taking screenshot:" + e.getMessage());
		}
		return screenFileLocation;
	}

}
