package businessComponents;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import mobileTesting.AppiumTestDriverFactory;
import pageObjectRepository.InstagramLoginPage;
import pageObjectRepository.InstagramPostPage;
import pageObjectRepository.YoutubeVideoPage;

public class FunctionalComponentsMobile extends AppiumTestDriverFactory {

	AppiumTestDriverFactory wf = new AppiumTestDriverFactory();
	AndroidDriver driver;

	public void launchApplication(String url, String os) throws InterruptedException {

		JSONObject capabilities = new JSONObject();
		if (StringUtils.equalsAnyIgnoreCase(os, "ios")) {
			capabilities.put("platformName", "ios");
			capabilities.put("platformVersion", "16.4");
//			capabilities.put("browserName", "Safari");
			capabilities.put("deviceName", "iPad Air (5th generation)");
			capabilities.put("automationName", "XCUITest");
			capabilities.put("app", "path_to_your_instagram_app.ipa");

//			driver = wf.browserSetupIOS(capabilities);
		} else {
			// To Do for Android
			capabilities.put("platformName", "Android");
			capabilities.put("platformVersion", "12");
			capabilities.put("browserName", "Chrome");
			capabilities.put("automationName", "UiAutomator2");
			capabilities.put("deviceName", "JNON7PDYCM7D55CY");

			driver = wf.browserSetupAndroid(capabilities);
			
			try {
				driver.pushFile("/sdcard/download/Picture 1.png", new File("/Users/I320807/Downloads/Picture 1.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
		}
//		driver.navigate().to(url);
		// Get the current context
		
		

//		System.out.println(driver.getCurrentUrl());

	}

	public boolean loginToInstagram() {
		WebElement loginButton = driver.findElement(By.id("com.instagram.android:id/log_in_button"));
        WebElement usernameField = driver.findElement(By.id("com.instagram.android:id/login_username"));
        WebElement passwordField = driver.findElement(By.id("com.instagram.android:id/password"));
        
        
        usernameField.sendKeys("teamSAP_");
        passwordField.sendKeys("@SAPteam123@");
        loginButton.click();
		return false;
		
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
	
	public void loginInstagram() {
		// TODO Auto-generated method stub
		// Find and interact with login elements using WebElement
		
		wf.waitforElementToBeClickableByXpath(InstagramLoginPage.otherAccount);
		wf.webElementClickByXPath(InstagramLoginPage.otherAccount);
		
		wf.waitforElementToBeClickableByXpath(InstagramLoginPage.userNameField);
		wf.sendTextByXpath(InstagramLoginPage.userNameField, "wrongly_choosen");
		wf.sendTextByXpath(InstagramLoginPage.passwordField, "@SAPteam123@");
		wf.webElementClickByXPath(InstagramLoginPage.loginButton);
        
        wf.waitforElementToBeClickableByXpath(InstagramLoginPage.notNowButton);
        wf.webElementClickByXPath(InstagramLoginPage.notNowButton);
        
        wf.waitforElementToBeClickableByXpath(InstagramPostPage.allowButton);
        wf.webElementClickByXPath(InstagramPostPage.allowButton);
        
        wf.waitforElementToBeClickableByXpath(InstagramPostPage.allowButton2);
        wf.webElementClickByXPath(InstagramPostPage.allowButton2);
        
        wf.waitforElementToBeClickableByXpath(InstagramPostPage.profileButton);
        wf.webElementClickByXPath(InstagramPostPage.profileButton);
        
        wf.waitforElementToBeClickableByXpath(InstagramPostPage.postButton);
        wf.webElementClickByXPath(InstagramPostPage.postButton);
        
        wf.waitforElementToBeClickableByXpath(InstagramPostPage.newPostButton);
        wf.webElementClickByXPath(InstagramPostPage.newPostButton);
        
        wf.waitforElementToBeClickableByXpath(InstagramPostPage.allow_id);
        wf.webElementClickByXPath(InstagramPostPage.allow_id);
        
        
        
	}

	public void closeBrowser() {
		wf.closeBrowser();
	}

}
