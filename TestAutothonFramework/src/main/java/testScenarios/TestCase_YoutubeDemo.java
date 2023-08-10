package testScenarios;

import org.testng.annotations.Test;
import businessComponents.FunctionalComponents;

public class TestCase_YoutubeDemo extends FunctionalComponents{
	
	FunctionalComponents fc = new FunctionalComponents();
	
	
	@Test
	public void TC01_Youtube_Video_Quality_Change() throws InterruptedException {
		fc.launchApplication("https://www.youtube.com/watch?v=cSED556_KCg&t=14s");
		fc.gotoYoutubeVideoSettings();
		fc.changeVideoQuality("144p");
		
	}


}
