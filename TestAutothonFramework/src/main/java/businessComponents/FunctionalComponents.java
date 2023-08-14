package businessComponents;

import pageObjectRepository.YoutubeVideoPage;
import webDriverFunctions.WebDriverFactory;

public class FunctionalComponents extends WebDriverFactory{
	
	WebDriverFactory wf = new WebDriverFactory();
	
	public void launchApplication(String url) throws InterruptedException {
		
		wf.browserSetup();
		driver.get(url);
		
	}
	
	
	public void gotoYoutubeVideoSettings() {
		
		wf.waitforElementToBeClickableByCSSSelector(YoutubeVideoPage.settings);
		wf.webElementClickByCssSelector(YoutubeVideoPage.settings);
		System.out.println(driver.getTitle());
		
	}
	
	
	public void changeVideoQuality(String quality) {
		
		wf.waitforElementToBeClickableByCSSSelector(YoutubeVideoPage.quality);
		wf.webElementClickByCssSelector(YoutubeVideoPage.quality);
		
		wf.waitforElementToBeClickableByXpath(YoutubeVideoPage.qualityid);
		wf.webElementClickByXPath(YoutubeVideoPage.qualityid);
						
	}
	
	

}
