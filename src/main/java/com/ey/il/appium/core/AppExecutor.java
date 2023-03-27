
package com.ey.il.appium.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class AppExecutor {

	public static boolean bResult = true;	

	public static void main(String[] args) throws Exception
	{
		BasicConfigurator.configure();
		AppExecutor obj = new AppExecutor();;
		try {
			obj.execute_TestCases();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void execute_TestCases() throws Exception {

		DBDriver sql = new DBDriver();
		sql.setStrQuery("SELECT * FROM TESTCASES WHERE RUNMODE='YES'");
		String[][] dbArr = (String[][]) sql.getTestData();
		//Generate the TestNG xml file
		testNGXmLCreateRun(dbArr);

	}

	@SuppressWarnings("deprecation")
	public void testNGXmLCreateRun(String[][] dbArr) throws IOException{

		LinkedHashSet<String> testClasses = new LinkedHashSet<>();
		List<XmlInclude> methodsToRun = new ArrayList<> ();
		ArrayList<String> testMethods = new ArrayList<>();
		List<XmlClass> pack = new ArrayList<>();

		//Create an instance on TestNG
		TestNG myTestNG = new TestNG();
		XmlSuite suite = new XmlSuite ();
		suite.setName ("App Test Automation");

		
		XmlTest test = new XmlTest (suite);
		
		test.setPreserveOrder ("true");

		// Getting total unique class names
		for(int k=0;k<dbArr.length;k++){
			test.setName (dbArr[k][0]);
			testClasses.add(dbArr[k][1]);
			testMethods.add(dbArr[k][2]);
		}

		// Converting Set into Array.
		String[] newClasses = new String[testClasses.size()];
		int index=0;
		for(String  newClass:testClasses){
			newClasses[index]=newClass;
			index++;
		}

		for(int i=0;i<testClasses.size();i++){

			XmlClass testClass = new XmlClass ();
			ArrayList<String> testMethods1 = new ArrayList<>();

			testClass.setName(newClasses[i]);

			for(int j=0;j<dbArr.length;j++){
				if(dbArr[j][1].equalsIgnoreCase(newClasses[i])){
					testMethods1.add(dbArr[j][2]);
				}
			}
			methodsToRun = constructIncludes (testMethods1);
			testClass.setIncludedMethods (methodsToRun);
			pack.add(testClass);

		}

		test.setXmlClasses(pack);
		//System.out.println (suite.toXml());
		LogUtil.info(suite.toXml());

		//Create a list of XmlTests and add the Xmltest you created earlier to it.
		List<XmlTest> myTests = new ArrayList<>();
		myTests.add(test);

		//add the list of tests to your Suite.
		suite.setTests(myTests);

		//Add the suite to the list of suites.
		List<XmlSuite> mySuites = new ArrayList<>();
		mySuites.add(suite);

		//Set the list of Suites to the testNG object you created earlier.
		myTestNG.setXmlSuites(mySuites);

		//invoke run() - this will run your class.
		myTestNG.run();

	}

	public List<XmlInclude> constructIncludes (ArrayList<String> methodNames) {
		List<XmlInclude> includes = new ArrayList<> ();
		for (String eachMethod : methodNames) {
			includes.add (new XmlInclude (eachMethod));
		}
		return includes;
	}
}
