package businessComponents;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.StartsActivity;
import mobileTesting.AppiumTestDriverFactory;
import pageObjectRepository.YoutubeVideoPage;
import webDriverFunctions.WebDriverFactory;

public class FunctionalComponentsMobile extends AppiumTestDriverFactory{
	
	AppiumTestDriverFactory wf = new AppiumTestDriverFactory();
	AppiumDriver driver;
	
	public void launchApplication(String url,String os) throws InterruptedException {
		
		JSONObject capabilities = new JSONObject();
		if(StringUtils.equalsAnyIgnoreCase(os, "ios")) {
			capabilities.put("platformName", "ios");
			capabilities.put("platformVersion", "16.4");
			capabilities.put("browserName", "Safari");
			capabilities.put("automationName", "XCUITest");
			driver = wf.browserSetupIOS(capabilities);
		}else {
			//To Do for Android
			capabilities.put("platformName", "Android");
			capabilities.put("platformVersion", "14");
			capabilities.put("browserName", "chromium");
			capabilities.put("automationName", "UiAutomator2");
			capabilities.put("deviceName", "emulator-5554");
			 
			
			driver = wf.browserSetupAndroid(capabilities);
		}
		driver.navigate().to(url);
		// Get the current context
        
		System.out.println(driver.getCurrentUrl());
		
	}
	
	

}
