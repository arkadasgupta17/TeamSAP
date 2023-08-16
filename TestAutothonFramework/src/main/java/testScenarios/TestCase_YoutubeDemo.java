package testScenarios;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.*;

import businessComponents.FunctionalComponents;
import businessComponents.FunctionalComponentsMobile;

public class TestCase_YoutubeDemo {
	

	FunctionalComponents fc = new FunctionalComponents();
	FunctionalComponentsMobile fcM = new FunctionalComponentsMobile();

	ExtentReports extent = new ExtentReports();
	ExtentSparkReporter spark = new ExtentSparkReporter(
			"/Users/I532546/git/TestAutothonFramework/TestAutothonFramework/src/test/resources/HTMLReports/ExtentReport1.html");

	@BeforeTest
	public void reportConfiguration() {
		extent.attachReporter(spark);

	}

	@Test(priority = 1)
	public void TC01_Youtube_Video_Quality_Change() throws InterruptedException {

		ExtentTest test = extent.createTest("Youtube Video Quality Change");
		try {
			fc.launchApplication("https://www.youtube.com/watch?v=_pG4QLtuRYw");
			test.log(Status.PASS, "Application Launched");
			
			fc.gotoYoutubeVideoSettings();
			test.log(Status.PASS, "Clicking on Youtube Settings is successful");
			
			fc.changeVideoQuality("144p");
			test.log(Status.PASS, "Quality Change successful");
			
			String quality = fc.validateCurrentVideoQuality();
			test.log(Status.PASS, "Quality Change Successful. Current Video Quality is: " + quality);
		} catch (Exception e) {

			test.log(Status.FAIL, "The Test Case is failed. ");
			test.log(Status.FAIL, "The Failure Reason: " + e.getMessage());
			System.out.println(e.getMessage());
		}

	}

	@Test(priority = 2)
	public void TC01_Youtube_Video_Mobile_IOS() throws InterruptedException {

		ExtentTest test = extent.createTest("Mobile IOS Testing");
		try {
			fcM.launchApplication("https://www.youtube.com/watch?v=cSED556_KCg&t=14s", "ios");
			fcM.gotoYoutubeVideoSettings();
			fcM.changeVideoQuality("144p");
			test.log(Status.PASS, "Quality Change Successful.");
		} catch (Exception e) {

			test.log(Status.FAIL, "The Test Case is failed. ");
			test.log(Status.FAIL, "The Failure Reason: " + e.getMessage());
		}

	}

	@Test(priority = 3)
	public void TC01_Youtube_Video_Mobile_Android() throws InterruptedException {
		ExtentTest test = extent.createTest("Mobile Android Testing");
		fcM.launchApplication("https://www.youtube.com/watch?v=cSED556_KCg&t=14s", "Android");
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
