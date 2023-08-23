package testScenarios;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.jsoup.nodes.Element;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.reporter.*;

import businessComponents.FunctionalComponents;
import businessComponents.FunctionalComponentsMobile;
import webDriverFunctions.WebDriverFactory;
import businessComponents.FunctionalComponentAPI;

public class TestAutothon_Twitter_TestCase {

	WebDriverFactory wf = new WebDriverFactory();
	FunctionalComponents fc = new FunctionalComponents();
	FunctionalComponentsMobile fcM = new FunctionalComponentsMobile();
	FunctionalComponentAPI fca = new FunctionalComponentAPI();

	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

	ExtentReports extent = new ExtentReports();
	ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")
			+ "/src/test/resources/HTMLReports/ExtentReport_" + (formater.format(calendar.getTime())) + ".html");

	@BeforeTest
	public void reportConfiguration() {
		extent.attachReporter(spark);

	}

	@Test(priority = 1)
	public void TC01_Youtube_Video_Quality_Change() throws InterruptedException {

		ExtentTest test = extent.createTest("Youtube Video Quality Change");
		try {
			fc.launchApplication("https://twitter.com/i/flow/login");
			test.log(Status.PASS, "Twitter Launched");

			fc.twitterLogin();
			test.log(Status.PASS, "Twitter Login successful");


		} catch (Exception e) {

			test.log(Status.FAIL, "The Test Case is failed. ");
			test.log(Status.FAIL, "The Failure Reason: " + e.getMessage())
					.addScreenCaptureFromPath(fc.captureScreenshot());
		}

	}


	@AfterTest
	public void closeTest() {
		try {
			fc.closeBrowser();
		} catch (Exception e) {

		}
		try {
			fcM.closeBrowser();
		} catch (Exception e) {
		}

		extent.flush();

	}

}
