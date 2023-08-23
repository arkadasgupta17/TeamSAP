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
import dataExtractionUtilities.AccessSourceFile;

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
	AccessSourceFile asf = new AccessSourceFile();
	
	String testData = System.getProperty("user.dir")+"/src/test/resources/testData/stepinhacakthon.xlsx";

	@BeforeTest
	public void reportConfiguration() {
		extent.attachReporter(spark);

	}

	@Test(priority = 1)
	public void TC01_Twitter_Post() throws InterruptedException {

		ExtentTest test = extent.createTest("TC01 Tweet Post");
		try {
			fc.launchApplication("https://twitter.com/i/flow/login");
			test.log(Status.PASS, "Twitter Launched");

			fc.twitterLogin();
			test.log(Status.PASS, "Twitter Login successful");

			for(int i = 1; i<=5 ; i++) {
				
				
				String textid = Integer.toString(i);
				String tweet = asf.getValue(testData, "data", "UniqueText"+textid);
				
				Thread.sleep(2000);
				
				fc.enterTweetText(tweet);
				test.log(Status.PASS, "Tweeting Successful");
				
				Thread.sleep(2000);
				
				fc.clickPostButton();
				test.log(Status.PASS, "Click on Post is Successful");
				
				Thread.sleep(5000);
				
			}

			Thread.sleep(10000);
			fc.searchForTweet("SAP_At_STEPINSUMMIT2023_1701020118");
			test.log(Status.PASS, "Tweet Search Successful");

			for (int j = 1; j <= 5; j++) {
				String ss = fc.getTweetsScreenshots(j);
				test.log(Status.PASS, "Tweets" + j + "Screenshot is taken and stored Successfully")
						.addScreenCaptureFromPath(ss);
			}

		} catch (Exception e) {

			test.log(Status.FAIL, "The Test Case is failed. ");
			test.log(Status.FAIL, "The Failure Reason: " + e.getMessage())
					.addScreenCaptureFromPath(fc.captureScreenshot());
		}

	}

	@Test(priority = 2, enabled = false)
	public void TC02_Twitter_Post_Search_Take_Screenshots() throws InterruptedException {

		ExtentTest test = extent.createTest("TC02 Twitter Post Search and Take Screenshots");
		try {
			fc.launchApplication("https://twitter.com/i/flow/login");
			test.log(Status.PASS, "Twitter Launched");

			fc.twitterLogin();
			test.log(Status.PASS, "Twitter Login successful");
			// Thread.sleep(60000);

			Thread.sleep(10000);
			fc.searchForTweet("SAP_At_STEPINSUMMIT2023_1701020117");
			test.log(Status.PASS, "Tweet Search Successful");

			for (int i = 1; i <= 5; i++) {
				String ss = fc.getTweetsScreenshots(i);
				test.log(Status.PASS, "Tweets Screenshot is taken and stored Successfully")
						.addScreenCaptureFromPath(ss);
			}

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
