package testScenarios;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ChangeYoutubeVideoQuality {

	public static void main(String[] args) throws InterruptedException, AWTException {
		// TODO Auto-generated method stub
		
		// Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Open YouTube video URL
        String videoUrl = "https://www.youtube.com/watch?v=1zSW1bQX7PE";
        driver.get(videoUrl);

        Thread.sleep(10000);
        
        driver.manage().window().maximize();

        // Find the settings button and click on it
        WebElement settingsButton = driver.findElement(By.cssSelector(".ytp-settings-button"));
        settingsButton.click();

        // Wait for the settings menu to appear
        Thread.sleep(10000);

        // Find the quality submenu and click on it
        WebElement qualitySubmenu = driver.findElement(By.cssSelector(".ytp-panel-menu .ytp-menuitem:nth-child(3)"));
        qualitySubmenu.click();

        // Wait for the quality options to appear
        Thread.sleep(5000);
        
        Robot robot = new Robot();
        
        robot.keyPress(KeyEvent.VK_UP);
        Thread.sleep(3000);
        robot.keyPress(KeyEvent.VK_UP);
        Thread.sleep(3000);
        
        robot.keyPress(KeyEvent.VK_ENTER);
        
        Thread.sleep(7000);
        
        settingsButton.click();
        

        // Find and click on the 144p quality option
       // WebElement quality144p = driver.findElement(By.xpath("//span[text='144p']/parent::div/parent::div/parent::div"));
        //quality144p.click();
        
        String quality = driver.findElement(By.xpath("//span[@class='ytp-menu-label-secondary']")).getText();
        
        System.out.println(quality);

        // Close the settings menu
        //settingsButton.click();
        
        
        


	}

}
