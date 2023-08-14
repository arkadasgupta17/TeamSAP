package mobileTesting;


import java.net.URL;
import java.time.Duration;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class AppiumTestDriverFactory {
    public AppiumDriver driver;
	
	
	public AppiumDriver browserSetupIOS(JSONObject capabilities) {
		
		 DesiredCapabilities caps = new DesiredCapabilities();
         caps.setCapability("platformName", capabilities.getString("platformName"));
         caps.setCapability("platformVersion", capabilities.getString("platformVersion"));
         caps.setCapability("automationName", capabilities.getString("automationName"));
         caps.setCapability("browserName", capabilities.getString("browserName"));
         URL url;
		try {
			url = new URL("http://127.0.0.1:4723/wd/hub");
			driver = new AppiumDriver(url, caps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}
	
	public AppiumDriver browserSetupAndroid(JSONObject capabilities) {
		
		DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", capabilities.getString("platformName"));
        caps.setCapability("platformVersion", capabilities.getString("platformVersion"));
        caps.setCapability("automationName", capabilities.getString("automationName"));
//        caps.setCapability("browserName", capabilities.getString("browserName"));
        caps.setCapability("deviceName", capabilities.getString("deviceName"));
        caps.setCapability("appPackage", "com.android.chrome");
        caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");
        
        URL url;
		try {
			url = new URL("http://127.0.0.1:4723/wd/hub");
			driver = new AndroidDriver(url, caps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}
	
	
	public void webElementClickByXPath(String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}
	
	public void openURL(String url) {
		driver.get(url);
	}
	
	public void webElementClickByCssSelector(String css) {
		driver.findElement(By.cssSelector(css)).click();
	}
	
	
	public void waitforElementToBeVisibleByXpath(String xpath) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30000));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}
	
	public void waitforElementToBeVisibleByCSSSelector(String css) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30000));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(css)));
	}
	
	public void waitforElementToBeClickableByXpath(String xpath) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30000));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}
	
	public void waitforElementToBeClickableByCSSSelector(String css) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30000));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(css)));
	}
}
