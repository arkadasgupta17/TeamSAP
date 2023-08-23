package pageObjectRepository;

public class TwitterHomePage {
	
	public static String textField = "//*[@aria-label=\"Tweet text\"]";
	public static String postButton = "//*[@aria-label='Add post']/following::*[text()='Post']";
	public static String linkExplore = "//a[contains(@href,'explore')]";
	public static String txtSearch = "//input[@placeholder='Search']";
	public static String divTweets = "//div[@aria-label='Timeline: Search timeline']/div/div/div/div";
	
	

}
