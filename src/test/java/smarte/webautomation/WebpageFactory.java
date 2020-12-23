package smarte.webautomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import smarte.pagefactory.web.SalesforceCustomFields;
import smarte.pagefactory.web.UploadFilesForPushPull;
import smarte.pagefactory.web.WebSanity;

public class WebpageFactory {
	
	public static SalesforceCustomFields salesforceCustom;
	public static WebSanity webSanity;
	private static boolean initializedPages=false;
	public static UploadFilesForPushPull upload;

	
	public static void initializePageObjects(WebDriver driver)
	{
		if(initializedPages == false)
		{
			salesforceCustom = new SalesforceCustomFields(driver);
			//salesforceCustom = PageFactory.initElements(driver, SalesforceCustomFields.class);
			webSanity = PageFactory.initElements(driver, WebSanity.class);
			upload = new UploadFilesForPushPull(driver);
			
		}
	}

}
