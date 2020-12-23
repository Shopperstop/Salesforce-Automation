package smarte.pagefactory.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class UploadFilesForPushPull {
	
	public WebDriver driver;

	public UploadFilesForPushPull(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 150), getClass());
		this.driver = driver;
	}
	
	@FindBy(how = How.ID, using = "fileToUpload")
	private WebElement ele;
	
	public void selectFileAndUpload(String url)
	{
		driver.get(url);
		
		
	}
	
	


}
