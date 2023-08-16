package businessComponents;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import pageObjectRepository.YoutubeVideoPage;
import webDriverFunctions.WebDriverFactory;

public class FunctionalComponents extends WebDriverFactory{
	
	WebDriverFactory wf = new WebDriverFactory();
	WebDriver driver ;
	
	public void launchApplication(String url) throws InterruptedException {
		

			driver = wf.browserSetup();
			driver.get(url);

			
	}
	
	
	public void gotoYoutubeVideoSettings() {
		

		wf.waitforElementToBeClickableByCSSSelector(YoutubeVideoPage.settings);
		wf.webElementClickByCssSelector(YoutubeVideoPage.settings);
		System.out.println(driver.getTitle());

		
	}
	
	
	public void changeVideoQuality(String quality) {
		
		wf.waitforElementToBeClickableByXpath(YoutubeVideoPage.quality);
		wf.webElementClickByXPath(YoutubeVideoPage.quality);
		
		wf.waitforElementToBeClickableByXpath(YoutubeVideoPage.qualityid);
		wf.webElementClickByXPath(YoutubeVideoPage.qualityid);
						
	}
	
	public void closeBrowser() {
		wf.closeBrowser();
	}
	
	

}
