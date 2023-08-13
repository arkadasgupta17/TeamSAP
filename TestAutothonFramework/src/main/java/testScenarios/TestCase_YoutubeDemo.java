package testScenarios;

import org.testng.annotations.Test;
import businessComponents.FunctionalComponents;
import businessComponents.FunctionalComponentsMobile;

public class TestCase_YoutubeDemo extends FunctionalComponents{
	
	FunctionalComponents fc = new FunctionalComponents();
	FunctionalComponentsMobile fcM = new FunctionalComponentsMobile();
	
	@Test
	public void TC01_Youtube_Video_Quality_Change() throws InterruptedException {
		fc.launchApplication("https://www.youtube.com/watch?v=cSED556_KCg&t=14s");
		fc.gotoYoutubeVideoSettings();
		fc.changeVideoQuality("144p");
		
	}
	
	@Test
	public void TC01_Youtube_Video_Mobile_IOS() throws InterruptedException{
		fcM.launchApplication("https://www.youtube.com/watch?v=cSED556_KCg&t=14s","ios");
	}
	
	@Test
	public void TC01_Youtube_Video_Mobile_Android() throws InterruptedException{
		fcM.launchApplication("https://www.youtube.com/watch?v=cSED556_KCg&t=14s","Android");
	}
	
	


}
