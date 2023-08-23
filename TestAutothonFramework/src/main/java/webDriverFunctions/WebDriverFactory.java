package webDriverFunctions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.apache.commons.io.FileUtils;

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

	
	public void sendTextByXpath(String xpath, String value) {
		driver.findElement(By.xpath(xpath)).sendKeys(value);;
	}
	
	public void openURL(String url) {
		driver.get(url);
	}

	public void webElementClickByCssSelector(String css) {
		driver.findElement(By.cssSelector(css)).click();
	}

	public void waitforElementToBeVisibleByXpath(String xpath) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

	public void waitforElementToBeVisibleByCSSSelector(String css) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(css)));
	}

	public void waitforElementToBeClickableByXpath(String xpath) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}

	public void waitforElementToBeClickableByCSSSelector(String css) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(css)));
	}

	public void closeBrowser() {
		driver.close();
	}

	public void closeAllBrowserEvents() {
		driver.quit();
	}

	public String getTextFromElement(String xpath) {
		return driver.findElement(By.xpath(xpath)).getText();
	}

	public String getAttributeFromElement(String xpath, String attribute) {
		return driver.findElement(By.xpath(xpath)).getAttribute(attribute);
	}

	public void selectDropdownByValue(String xpath, String value) {

		Select s = new Select((driver.findElement(By.xpath(xpath))));
		s.selectByValue(value);

	}

	public void selectDropdownByVisibleText(String xpath, String value) {

		Select s = new Select((driver.findElement(By.xpath(xpath))));
		s.selectByVisibleText(value);

	}

	public void mousehover(String xpath) {
		Actions ac = new Actions(driver);
		WebElement we = driver.findElement(By.xpath(xpath));
		ac.moveToElement(we).build().perform();

	}

	public void scrollToElement(String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", element);
	}

	public void clickOnElementByJScript(String xpath) {

		WebElement element = driver.findElement(By.xpath(xpath));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);

	}

	public void sendTextByJS(String value, String xpath) {

		WebElement element = driver.findElement(By.xpath(xpath));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].value='" + value + "';", element);

	}

	public Boolean objectExists(String xpath) {
		return !driver.findElements(By.xpath(xpath)).isEmpty();
	}

	public void waitUntilPageReadyStateComplete(long timeOutInSeconds) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
		ExpectedCondition<Boolean> pageReadyStateComplete = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		wait.until(pageReadyStateComplete);
	}



}
