package pageObjectRepository;

public class TwitterLogin {
	
	public static String txtUsername = "//input[contains(@autocomplete,'username')]";
	public static String btnNext = "//span[contains(text(),'Next')]/parent::span/parent::div";
	public static String txtPasswword = "//input[contains(@name,'password')]";
	public static String btnLogin = "//span[contains(text(),'Log in')]/parent::span/parent::div";
	
	

}
