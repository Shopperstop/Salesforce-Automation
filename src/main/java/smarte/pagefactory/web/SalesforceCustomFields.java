package smarte.pagefactory.web;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SalesforceCustomFields {
	public WebDriver driver;
	
	@FindBy(xpath= "//input[@id='username']")
	public  WebElement emailTextBox;
	
	
	public SalesforceCustomFields(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 150), getClass());
		this.driver = driver;
	}

	
	public void login(String userId, String pwd) {
		// driver.get("https://login.salesforce.com/?locale=in");
		WebElement emailTextBox = driver.findElement(By.xpath("//input[@id='username']"));
		WebElement passwordTextBox = driver.findElement(By.xpath("//input[@type='password']"));

		WebElement submit = driver.findElement(By.xpath("//input[@type='submit']"));

		emailTextBox.clear();
		emailTextBox.sendKeys(userId);

		passwordTextBox.clear();
		passwordTextBox.sendKeys(pwd);

		submit.click();
	}

	int count;
	public void clickOnSetupLink() {
		count = driver.findElements(By.xpath("//div/a[@id='setupLink']")).size();
		if (count > 0) {
			WebElement setup = driver.findElement(By.xpath("//div/a[@id='setupLink']"));
			setup.click();
		} else
			ClickOnMenu();
	}

	public void ClickOnMenu() {
		WebElement menuLink = driver.findElement(By.xpath("//div[@Class='menuButtonButton']"));
		WebElement setupLink = driver.findElement(By.xpath("//div/a[@title='Setup']"));
		menuLink.click();
		setupLink.click();
	}

	public void clickOnCustomize() {
		WebElement customize = driver.findElement(By.xpath("//div/a[contains(text(),'Customize')]"));
		customize.click();
	}

	public void clickOnLead() {
		WebElement lead = driver.findElement(By.xpath("//div/a[contains(text(),'Leads')]"));
		lead.click();
	}

	public void clickOnAccount() {
		WebElement account = driver.findElement(By.xpath("//div[@id='Account']/a[contains(text(),'Accounts')]"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		account.click();
	}

	public void clickOnContact() {
		WebElement account = driver.findElement(By.xpath("//div[@id='Contact']/a[contains(text(),'Contacts')]"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("arguments[0].scrollIntoView();", account);
		js.executeScript("window.scrollBy(0,1000)");
		account.click();
	}

	public void clickOnAddCustomFieldstoLeads() {
		WebElement addCustomeFieldsLead = driver
				.findElement(By.xpath("//a[contains(text(),'Add a custom field to leads')]"));
		addCustomeFieldsLead.click();
	}

	public void clickOnAddCustomFieldstoAccounts() {
		WebElement addCustomeFieldsAccount = driver
				.findElement(By.xpath("//a[contains(text(),'Add a custom field to accounts')]"));
		addCustomeFieldsAccount.click();
	}

	public void clickOnAddCustomFieldstoContact() {
		WebElement addCustomeFieldsContact = driver
				.findElement(By.xpath("//a[contains(text(),'Add a custom field to contacts')]"));
		addCustomeFieldsContact.click();
	}

	public void clickOnNew() {
		WebElement New = driver.findElement(By.xpath("//div[@class='noStandardTab']//td/input"));
		New.click();
	}

	public void clickOnNewAccount() {
		WebElement New = driver.findElement(By.xpath("//td[@class='pbButton']/input"));
		New.click();
	}

	public void clickOnDataType(String dataType) {
		WebElement textAreaLong = driver.findElement(By.xpath("//table/tbody/tr/td/label[@for='dtypeX']"));
		WebElement textArea = driver.findElement(By.xpath("//table/tbody/tr/td/label[@for='dtypeX']"));

		switch (dataType) {
		case "textAreaLong":
			textAreaLong.click();
			break;
		default:
			textArea.click();

		}
	}

	public void clickOnNext() {
		WebElement next = driver.findElement(By.xpath("//div[@class='pbBottomButtons']/input[1]"));
		next.click();
	}

	public void clickOnSave() {
		WebElement save = null;

		if (driver.findElements(By.xpath("//input[@name='save']")).size() != 0) {
			save = driver.findElement(By.xpath("//input[@name='save']"));
			save.click();
		}

	}

	public void clickOnGoNext() {
		WebElement gotoNext = driver.findElement(By.xpath("//input[@name='goNext']"));

		if (gotoNext.isDisplayed())
			gotoNext.click();
	}

	public void createCustomFieldForLead(String fieldLabel, String fieldName, String desc) throws InterruptedException {
		WebElement leadFieldLabel = driver.findElement(By.xpath("//input[@name='MasterLabel']"));
		WebElement leadFieldName = driver.findElement(By.xpath("//input[@name='DeveloperName']"));
		WebElement leadDescription = driver.findElement(By.xpath("//textarea[@name='Description']"));
		WebElement goNext = driver.findElement(By.xpath("//input[@name='goNext']"));

		Thread.sleep(1000);
		leadFieldLabel.clear();
		leadFieldLabel.sendKeys(fieldLabel);

		Thread.sleep(1000);
		leadFieldName.click();
		Thread.sleep(1000);
		leadDescription.clear();
		leadDescription.sendKeys(desc);
		goNext.click();

		clickOnGoNext();
		clickOnSave();

	}

}
