package webDriverFunctions;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class WebDriverFactory {
	
	

	public WebDriver driver;

	
	public WebDriver browserSetup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
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
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60000));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}
	
	public void waitforElementToBeVisibleByCSSSelector(String css) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60000));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(css)));
	}
	
	public void waitforElementToBeClickableByXpath(String xpath) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60000));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}
	
	public void waitforElementToBeClickableByCSSSelector(String css) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60000));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(css)));
	}
	
	public void closeBrowser() {
		driver.close();
	}
	
	
	
	

}
