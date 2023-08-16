package testScenarios;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import businessComponents.FunctionalComponentsMobile;

public class MobileTestCases extends FunctionalComponentsMobile {

	// all the mobile related test cases should be here

	FunctionalComponentsMobile fcM = new FunctionalComponentsMobile();

	ExtentReports extent = new ExtentReports();
	ExtentSparkReporter spark = new ExtentSparkReporter(
			"/Users/I532546/git/TestAutothonFramework/TestAutothonFramework/src/test/resources/HTMLReports/ExtentReportMobile.html");

	@BeforeTest
	public void reportConfiguration() {
		extent.attachReporter(spark);

	}

	@Test
	public void TC01_Youtube_Video_Mobile_IOS() throws InterruptedException {
		ExtentTest test = extent.createTest("Mobile IOS Testing");
		try {
			fcM.launchApplication("https://www.youtube.com/watch?v=cSED556_KCg&t=14s", "ios");

		} catch (Exception e) {

			test.log(Status.FAIL, e.toString());

			System.out.println(e.toString());
		}

	}

	@Test
	public void TC01_Youtube_Video_Mobile_Android() throws InterruptedException {
		fcM.launchApplication("https://www.youtube.com/watch?v=cSED556_KCg&t=14s", "Android");
	}
}
