package businessComponents;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import pageObjectRepository.TwitterHomePage;
import pageObjectRepository.YoutubeVideoPage;
import webDriverFunctions.WebDriverFactory;

public class FunctionalComponents extends WebDriverFactory {

	WebDriverFactory wf = new WebDriverFactory();
	WebDriver driver;

	public void launchApplication(String url) throws InterruptedException {
		driver = wf.browserSetup();
		driver.get(url);

	}

	public String captureScreenshot() {

		String screenshotPath = null;

		try {

			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			screenshotPath = System.getProperty("user.dir") + "/src/test/resources/Screenshots/FailedScreenshot_"
					+ (formater.format(calendar.getTime())) + " .jpg";

			TakesScreenshot scrShot = (TakesScreenshot) driver;
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile = new File(screenshotPath);
			FileUtils.copyFile(SrcFile, DestFile);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return screenshotPath;
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

	public String validateCurrentVideoQuality() {

		wf.waitforElementToBeClickableByCSSSelector(YoutubeVideoPage.settings);
		wf.webElementClickByCssSelector(YoutubeVideoPage.settings);
		wf.waitforElementToBeClickableByXpath(YoutubeVideoPage.quality);
		return wf.getTextFromElement(YoutubeVideoPage.quality);

	}
	
	
	public String enterTweetText(String tweet) {

		wf.waitforElementToBeClickableByXpath(TwitterHomePage.textField);
		wf.sendTextByJS(tweet,TwitterHomePage.textField);
//		wf.webElementClickByCssSelector(YoutubeVideoPage.settings);
//		wf.waitforElementToBeClickableByXpath(YoutubeVideoPage.quality);
		return wf.getTextFromElement(TwitterHomePage.textField);

	}
	
	public void clickPostButton() {

		wf.waitforElementToBeClickableByXpath(YoutubeVideoPage.settings);
		wf.webElementClickByXPath(YoutubeVideoPage.quality);

	}

	public void closeBrowser() {
		wf.closeBrowser();
	}

}
