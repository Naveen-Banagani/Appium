package tests.android.app;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.FindsByAndroidUIAutomator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ey.il.appium.core.Driver;
import com.ey.il.appium.core.DriverFactory;
import com.ey.il.appium.core.TestdataManger;
import com.ey.il.appium.pages.android.app.AerLingusContinuePage;
import com.ey.il.appium.pages.android.app.AerLingusFlightBookingPage;
import com.ey.il.appium.pages.android.app.AerLingusFlightSelectionPage;
import com.ey.il.appium.pages.android.app.AerLingusHomePage;
import com.ey.il.appium.pages.android.app.AerLingusPassengerDetailsPage;
import com.ey.il.appium.pages.android.app.AerLingusPaymentPage;

import static io.appium.java_client.touch.TapOptions.tapOptions;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.io.IOException;

public class Aerlingus1 extends Driver {

	private AppiumDriver<MobileElement> LocalDriver = null;

	@BeforeMethod
	public void preTestSetUp() {
		LocalDriver = DriverFactory.getDriver();

	};

	@Test
	public void AerLingus() throws IOException, InterruptedException {

		AerLingusHomePage home = new AerLingusHomePage(LocalDriver);
		AerLingusFlightBookingPage book = new AerLingusFlightBookingPage(LocalDriver);
		AerLingusFlightSelectionPage flight = new AerLingusFlightSelectionPage(LocalDriver);
		AerLingusPassengerDetailsPage passenger = new AerLingusPassengerDetailsPage(LocalDriver);
		AerLingusPaymentPage payment = new AerLingusPaymentPage(LocalDriver);
		AerLingusContinuePage con = new AerLingusContinuePage(LocalDriver);

		// Reding the Data from Excel Sheet
		TestdataManger tdm = new TestdataManger("W001_Test");

		String originCity = tdm.readData("City1");
		String destinationCity = tdm.readData("City2");
		String firstName = tdm.readData("FirstName");
		String familyName = tdm.readData("FamilyName");
		String email = tdm.readData("Email");
		String localNumber = tdm.readData("LocalNumber");
		String prefix = tdm.readData("Prefix");
		String cardNumber = tdm.readData("CardNumber");
		String cVV = tdm.readData("CVV");
		String addressOne = tdm.readData("AddressOne");
		String addressTwo = tdm.readData("AddressOne");
		String city = tdm.readData("City");
		String county = tdm.readData("County");
		String postal = tdm.readData("Postal");
		String expYear = tdm.readData("ExpYear");
		String expMonth = tdm.readData("ExpMonth");

		// Home page
		home.privacy().click();
		home.mainButton().click();
		home.bookFlight().click();

		// Flight Booking
		book.oneway().click();
		book.originCity().click();
		book.citySearch().click();
		book.cityName().sendKeys(originCity);
		book.originCitySelect().click();
		book.destinationCity().click();
		book.citySearch().click();
		book.cityName().sendKeys(destinationCity);
		book.destinationCitySelect().click();
		book.chooseDateButton().click();
		book.selectDate().click();
		book.continueButton().click();

		// Flight Selection
		flight.selectFare().click();
		con.continueButton().click();

		// Passenger Details
		passenger.titleSelection().click();
		passenger.selectMr().click();
		passenger.firstName().sendKeys(firstName);
		passenger.familyName().sendKeys(familyName);
		passenger.scrollToView("Email Address");
		passenger.country().click();
		passenger.selectCountry().click();
		passenger.prefix().sendKeys(prefix);
		passenger.localNumber().sendKeys(localNumber);
		passenger.emailAddress().sendKeys(email);
		con.continueButton().click();
		con.continueButton().click();
		con.continueButton().click();
		con.continueButton().click();

		// Payment:
		passenger.scrollToView("Card Details");
		payment.cardType().click();
		payment.selectCardType().click();
		payment.cardNumber().sendKeys(cardNumber);
		payment.expiryDate().click();
		passenger.scrollToView("Jan");
		passenger.scrollToView("2022");
		payment.button().click();
		payment.cvv().sendKeys(cVV);
		passenger.scrollToView("Country");
		payment.firstName().sendKeys(firstName);
		payment.familyName().sendKeys(familyName);
		payment.addressLineOne().sendKeys(addressOne);
		payment.addressLineTwo().sendKeys(addressTwo);
		payment.city().sendKeys(city);
		payment.postalCode().sendKeys(postal);
		payment.state().sendKeys(county);
		payment.country().click();
		payment.selectCountry().click();
		driver.quit();
	}
	
	@Override
	public void modifyResultFile(ITestResult result) {
		
	}

}
