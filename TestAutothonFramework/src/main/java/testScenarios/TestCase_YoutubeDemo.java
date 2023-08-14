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

public class TestCase_YoutubeDemo extends FunctionalComponents {

	FunctionalComponents fc = new FunctionalComponents();
	FunctionalComponentsMobile fcM = new FunctionalComponentsMobile();
	
	ExtentReports extent = new ExtentReports();
	ExtentSparkReporter spark = new ExtentSparkReporter(
			"/Users/I532546/git/TestAutothonFramework/TestAutothonFramework/src/test/resources/HTMLReports/ExtentReport1.html");

	@BeforeTest
	public void reportConfiguration() {
		extent.attachReporter(spark);

	}

	@Test
	public void TC01_Youtube_Video_Quality_Change() throws InterruptedException {

		ExtentTest test = extent.createTest("Youtube Video Quality Change");
		try {
			fc.launchApplication("https://www.youtube.com");
			fc.gotoYoutubeVideoSettings();
			fc.changeVideoQuality("144p");
			test.log(Status.PASS, "Quality Change Successful.");
		} catch (Exception e) {

			test.log(Status.FAIL, e.toString());

			System.out.println(e.toString());
		}

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

	
	
	@AfterTest
	public void closeTest() {
		fc.closeBrowser();
		extent.flush();

	}

}
