package smarte.webautomation;


import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import antlr.collections.List;
import smarte.utility.DBUtil;

import static smarte.webautomation.WebpageFactory.*;

public class WebSanityTest extends BaseTest{	
	Properties prop = new Properties();
	String strURL = "http://dglocales1.smarteinc.com:9200/";
	String constructURLead;
	String constructURContact;
	String constructURAccount;
	
	@BeforeTest
	public void loadDriver() {
		WebDriver driver;
		String propFileName = "/properties/config.properties";
		InputStream inputStream = CreateCustomFields.class.getResourceAsStream(propFileName);
		if (inputStream != null) {
			try {
				prop.load(inputStream);
			} catch (IOException e) {

			}
		}
	}

	@Test	
	public void CleanUpEnvironment()
	{	
		String connectionId = prop.getProperty("con");
		//webSanity.cleanUpSQL(connectionId);
		String strPrefix = "select index_prefix from dg_platform_qc.connection where id = "+ connectionId + " ";
		
		ResultSet rs = DBUtil.executeQuery(strPrefix);
		
		String strPrefixName = DBUtil.getColumnValue(rs, "index_prefix")[0];
		
		constructURLead = strURL+ strPrefixName  + "_Lead";
		constructURAccount = strURL+  strPrefixName + "_account";		
		constructURContact = strURL+ strPrefixName + "_contact";
		
		webSanity.deleteIndexesFromCCI(constructURLead);
		webSanity.deleteIndexesFromCCI(constructURAccount);
		webSanity.deleteIndexesFromCCI(constructURContact);		
		
	}
	
	@Test	
	public void uploadFiles()
	{	
		
		driver.get("http://dgftp.smarteinc.com/conf/upload.html");
		
	}
	
	@Test 
	void verifyOverviewDashboard()
	{
		driver.get("https://login.salesforce.com/");
		salesforceCustom.login("sam.sharma@smarteinc.com", "Admin@123");
		webSanity.verifyOverview();	
		
	}

}
