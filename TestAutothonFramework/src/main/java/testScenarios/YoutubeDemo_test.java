package testScenarios;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.*;

import businessComponents.FunctionalComponents;
import businessComponents.FunctionalComponentsMobile;
import businessComponents.FunctionalComponentAPI;

public class YoutubeDemo_test {
	

	FunctionalComponents fc = new FunctionalComponents();
	FunctionalComponentsMobile fcM = new FunctionalComponentsMobile();
	FunctionalComponentAPI fca = new FunctionalComponentAPI();

	ExtentReports extent = new ExtentReports();
	ExtentSparkReporter spark = new ExtentSparkReporter(
			"Users/I573505/OneDrive - SAP SE/Documents/Github/TestAutothonFramework/TestAutothonFramework/src/test/resources/HTMLReports/ExtentReport1.html");

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
		}

	}

	@Test(priority = 2)
	public void TC01_Youtube_Video_Mobile_IOS() throws InterruptedException {

		ExtentTest test = extent.createTest("Mobile IOS Testing");
		try {
			fcM.launchApplication("https://www.youtube.com/watch?v=cSED556_KCg&t=14s", "ios");
			test.log(Status.PASS, "Application Launched");
			
			fcM.gotoYoutubeVideoSettings();
			test.log(Status.PASS, "Clicking on Youtube Settings is successful");
			
			fcM.changeVideoQuality("144p");
			test.log(Status.PASS, "Quality Change successful");
			
			String quality = fc.validateCurrentVideoQuality();
			test.log(Status.PASS, "Quality Change Successful. Current Video Quality is: " + quality);
		} catch (Exception e) {

			test.log(Status.FAIL, "The Test Case is failed. ");
			test.log(Status.FAIL, "The Failure Reason: " + e.getMessage());
		}

	}

	@Test(priority = 3)
	public void TC01_Youtube_Video_Mobile_Android() throws InterruptedException {
		ExtentTest test = extent.createTest("Mobile Android Testing");
		try {
			fcM.launchApplication("https://www.youtube.com/watch?v=cSED556_KCg&t=14s", "Android");
			test.log(Status.PASS, "Application Launched");
			
			fcM.gotoYoutubeVideoSettings();
			test.log(Status.PASS, "Clicking on Youtube Settings is successful");
			
			fcM.changeVideoQuality("144p");
			test.log(Status.PASS, "Quality Change successful");
			
			String quality = fc.validateCurrentVideoQuality();
			test.log(Status.PASS, "Quality Change Successful. Current Video Quality is: " + quality);
		} catch (Exception e) {

			test.log(Status.FAIL, "The Test Case is failed. ");
			test.log(Status.FAIL, "The Failure Reason: " + e.getMessage());
		}
	}
	
	
	@Test(priority = 4)
	public void testAPI() {
		ExtentTest test = extent.createTest("API Testing");
		
		try {

			// open google
			String googleRes = fca.getStringResponseFromGetAPIandCheckStatusCode("https://www.google.com", 200);
			Document docGoogle = fca.convertStringToXMLDocument(googleRes);
			String titleGoogle = fca.getHtmlTitle(docGoogle);
			test.log(Status.PASS, "Open Google");
			
			// search wikipedia in google
			String searchRes = fca.getStringResponseFromGetAPIQueryParamsandCheckStatusCode("https://www.google.com/search", 200, 
					"q", "youtube");
			Document docSearch = fca.convertStringToXMLDocument(searchRes);
			String titleSearch = fca.getHtmlTitle(docSearch);
			String link = fca.getAnchorTagUrl(docSearch);
			link = link.substring((link.indexOf("?q=") + 3), link.indexOf("/&"));
			test.log(Status.PASS, "Search youtube in google");
			
			// open youtube
			String youtubeRes = fca.getStringResponseFromGetAPIandCheckStatusCode("https://www.youtube.com/", 200);
			Document docYoutube = fca.convertStringToXMLDocument(youtubeRes);
			String titleYoutube = fca.getHtmlTitle(docYoutube);
			test.log(Status.PASS, "Open youtube");
			
			// open youtube search
			String youTubeSearch = fca.getStringResponseFromGetAPIQueryParamsandCheckStatusCode("https://www.youtube.com/results", 200,
					"search_query", "selenium");
			Document docYoutubeSearch = fca.convertStringToXMLDocument(youTubeSearch);
			String titleYoutubeSearch = fca.getHtmlTitle(docYoutubeSearch);
			test.log(Status.PASS, "search for selenium in youtube");
			
		} catch (Exception e) {
			// TODO: handle exception
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
