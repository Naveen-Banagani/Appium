package com.ey.il.appium.core;

import java.sql.SQLException;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
	

	@DataProvider(name = "loginCredentials")
	public static Object[][] loginDataProviderMethod() 
	{
		return new Object[][] { { "dummy1","dummy1" } };
	}
	
	@org.testng.annotations.DataProvider(name="getSignup")
	public static Object [][] getSignupTestData() throws SQLException 
	{
		return DBDriver.getTestData("CREATEUSER");
	}
	
	@org.testng.annotations.DataProvider(name="getLogin")
	public static Object [][] getLoginTestData() throws SQLException 
	{
		return DBDriver.getTestData("LOGINUSER","USERNAME","PASSWORD","VALID");
	}
	
	@org.testng.annotations.DataProvider(name="getKill")
	public static Object [][] getKillTestData() throws SQLException 
	{
		return DBDriver.getTestData("KILL","USERNAME","PASSWORD","VALID");
	}
	
	@org.testng.annotations.DataProvider(name="getLogout")
	public static Object [][] getLogoutTestData() throws SQLException 
	{
		return DBDriver.getTestData("LOGOUT","USERNAME","PASSWORD","VALID");
	}
	
	@org.testng.annotations.DataProvider(name="getLoginPage")
	public static Object [][] getMainTestData() throws SQLException 
	{
		return DBDriver.getTestData("LOGINPAGE","USERNAME","PASSWORD");
	}
	
	@org.testng.annotations.DataProvider(name="Login")
	public static Object [][] Login() throws SQLException 
	{
		String [][] propArray = null;
		String testDataSql = "SELECT USERNAME,PASSWORD  FROM TD_LOGIN WHERE VALID='Valid' LIMIT 1";

		DBDriver sqlDriver = new DBDriver();
		sqlDriver.setStrQuery(testDataSql);

		propArray = (String[][]) sqlDriver.getTestData();

		return propArray;
	}

	@org.testng.annotations.DataProvider(name="Myclub")
	public static Object [][] getMyclub() throws SQLException 
	{
		String [][] propArray = null;
		String testDataSql = "SELECT * FROM TD_MYCLUB";

		DBDriver sqlDriver = new DBDriver();
		sqlDriver.setStrQuery(testDataSql);

		propArray = (String[][]) sqlDriver.getTestData();

		return propArray;
		
	
	}
	@org.testng.annotations.DataProvider(name="EmailSignUpVerification")
	public static Object [][] emailSignUpVerification() throws SQLException 
	{
		String [][] propArray = null;
		String testDataSql = "SELECT EMAIL,EMAIL_VALID FROM TD_SIGNUP";

		DBDriver sqlDriver = new DBDriver();
		sqlDriver.setStrQuery(testDataSql);

		propArray = (String[][]) sqlDriver.getTestData();

		return propArray;
	}



}


