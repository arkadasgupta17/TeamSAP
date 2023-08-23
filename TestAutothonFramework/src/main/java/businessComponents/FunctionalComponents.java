package businessComponents;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageObjectRepository.TwitterLogin;

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

	public void enterTweetText(String tweet) {

		try {
			wf.waitforElementToBeClickableByXpath(TwitterHomePage.textField);
			wf.sendTextByXpath(TwitterHomePage.textField, tweet);
//			wf.webElementClickByCssSelector(YoutubeVideoPage.settings);
//			wf.waitforElementToBeClickableByXpath(YoutubeVideoPage.quality);
//			return wf.getTextFromElement(TwitterHomePage.textField);
			Thread.sleep(2000);
			driver.findElement(By.xpath(TwitterHomePage.textField)).sendKeys(Keys.ENTER);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void clickPostButton() {

		wf.waitforElementToBeClickableByXpath(TwitterHomePage.postButton);
		wf.webElementClickByXPath(TwitterHomePage.postButton);

	}

	// Twitter Login Function
	public void twitterLogin() {

		wf.waitforElementToBeClickableByXpath(TwitterLogin.txtUsername);
		wf.sendTextByXpath(TwitterLogin.txtUsername, "teamSAP_");

		wf.waitforElementToBeClickableByXpath(TwitterLogin.btnNext);
		wf.webElementClickByXPath(TwitterLogin.btnNext);

		wf.waitforElementToBeClickableByXpath(TwitterLogin.txtPasswword);
		wf.sendTextByXpath(TwitterLogin.txtPasswword, "@SAPteam123@");

		wf.waitforElementToBeClickableByXpath(TwitterLogin.btnLogin);
		wf.webElementClickByXPath(TwitterLogin.btnLogin);

	}

	public void searchForTweet() throws InterruptedException {
		
		wf.waitforElementToBeClickableByXpath(TwitterHomePage.linkExplore);
		wf.webElementClickByXPath(TwitterHomePage.linkExplore);

		wf.waitforElementToBeClickableByXpath(TwitterHomePage.txtSearch);
		wf.sendTextByXpath(TwitterHomePage.txtSearch, "SAP_TEST_TWEET_IN_STEPIN_SUMMIT_2023");
		
		Thread.sleep(5000);
		
		driver.findElement(By.xpath(TwitterHomePage.txtSearch)).sendKeys(Keys.ENTER);

	}

	public String getTweetsScreenshots(int tweetNumber) {

		String screenshotPath = null;

		try {

				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
				screenshotPath = System.getProperty("user.dir") + "/src/test/resources/Screenshots/TweetScreenshot_"
						+ (formater.format(calendar.getTime())) + " .jpg";

				wf.waitforElementToBeVisibleByXpath(TwitterHomePage.divTweets + "[" + tweetNumber + "]");
				WebElement element = driver.findElement(By.xpath(TwitterHomePage.divTweets + "[" + tweetNumber + "]"));

				Thread.sleep(2000);
				
				File scrShot = ((TakesScreenshot) element).getScreenshotAs(OutputType.FILE);
				File DestFile = new File(screenshotPath);
				FileUtils.copyFile(scrShot, DestFile);


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		 return screenshotPath;

	}

	public void closeBrowser() {
		wf.closeBrowser();
	}

}
