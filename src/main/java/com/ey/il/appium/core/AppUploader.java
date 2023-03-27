/**
 * 
 */
package com.ey.il.appium.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AppUploader {

	private static String s = null;
	private static int responseCode;

	public static int curlCommand_SauceLab(String apk_loc, String saucelab_username, String testobject_api_key, String fileupload_site) throws IOException, InterruptedException {
		Process pro = null;
		String curl_exe_loc = Driver.userDir+"\\src\\test\\resources\\tool\\curl.exe";
		String curl_command = curl_exe_loc+" /c curl -u "+saucelab_username+":"+testobject_api_key+" -X POST "+fileupload_site+" -H \"Content-Type: application/octet-stream\" --data-binary @"+apk_loc+" --insecure";
		LogUtil.info("App upload to TestObject in-progress. Please wait...!");
		try {
			pro = Runtime.getRuntime().exec(curl_command);
		}catch (Exception e) {
			LogUtil.error("Error on App uploaded -"+e.getMessage());
			return 9999;
		}

		pro.waitFor();
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(pro.getInputStream()));

		while ((s = stdInput.readLine()) != null) {
			LogUtil.info("Standard output of the curl command-"+s);
			responseCode = Integer.parseInt(s);
		}
		return responseCode;	
	}

	public static String curlCommand_BrowserStack(String apk_loc, String browserStack_username, String browserStack_accessKey, String appUpload_site) throws IOException, InterruptedException {
		Process pro = null;
		String curl_exe_loc = Driver.userDir+"\\src\\test\\resources\\tool\\curl.exe";
		String curl_command = curl_exe_loc+" /c curl -u "+browserStack_username+":"+browserStack_accessKey+" -X POST "+appUpload_site+" -F"+apk_loc;
		LogUtil.info("App upload to BrowserStack in-progress. Please wait...!");
		try {
			pro = Runtime.getRuntime().exec(curl_command);
		}catch (Exception e) {
			LogUtil.error("Error on App uploaded -"+e.getMessage());
			return e.getMessage();
		}
		String response=null;
		pro.waitFor();
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(pro.getInputStream()));

		while ((s = stdInput.readLine()) != null) {
			LogUtil.info("Standard output of the curl command-"+s);
			//responseCode = Integer.parseInt(s);
			response=s;
			System.out.println(response);
		}
		return response;
	}
	
}