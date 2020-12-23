package smarte.pagefactory.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import smarte.APILibrary.ResponseService;
import smarte.utility.DBUtil;

public class WebSanity extends ResponseService {

	String strURL = "http://dglocales1.smarteinc.com:9200/";
	String constructURLead;
	String constructURContact;
	String constructURAccount;
	public WebDriver driver;

	public WebSanity(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 150), getClass());
		this.driver = driver;
	}

	public void cleanUpSQL(String connectionId) {

		String strDashboardOverView = "delete from dg_platform_qc.dg_dashboard_overview  where con_id = " + connectionId
				+ " ";

		String strFieldOverView = "delete from dg_platform_qc.dg_field_overview where con_id = " + connectionId + " ";

		String strLiftTrend = "delete from dg_platform_qc.dg_dashboard_lift_trend where con_id = " + connectionId + " ";

		String strFieldQualityStatus = "delete from dg_platform_qc.dg_field_quality_status where con_id = "
				+ connectionId + " ";

		String strFieldEnrichment = "delete from dg_platform_qc.dg_dashboard_field_enrichment where con_id = "
				+ connectionId + " ";

		String strTimesEnrichment = "delete from dg_platform_qc.dg_dashboard_times_enrichment where con_id = "
				+ connectionId + " ";

		String strPersonaCoverage = "delete from dg_platform_qc.dg_dashboard_persona_coverage where con_id = "
				+ connectionId + " ";

		String strPersonaOverview = "delete from dg_platform_qc.dg_persona_overview where con_id = " + connectionId
				+ " ";

		String personaListtrend = "delete from dg_platform_qc.dg_persona_lift_trend where con_id = " + connectionId
				+ " ";

		String strpersonaRecordStatus = "delete from dg_platform_qc.dg_persona_record_status where con_id =  "
				+ connectionId + " ";

		String strFieldQQRecordStatus = "delete from dg_platform_qc.dg_persona_fieldqq_record_status where con_id = "
				+ connectionId + " ";

		String strSpreadOverFunction = "delete from dg_platform_qc.dg_spread_over_function where con_id = "
				+ connectionId + " ";

		String strSptreadOverlevel = "delete from dg_platform_qc.dg_spread_over_level where con_id = " + connectionId
				+ " ";

		String strChangeHistory = "delete from dg_platform_qc.dg_formula_change_history where con_id = " + connectionId
				+ " ";

		String strOpportunityFieldEnrichment = "delete from dg_platform_qc.dg_opportunity_field_enrichment where con_id = "
				+ connectionId + " ";

		String stropportunityFieldOverview = "delete from dg_platform_qc.dg_opportunity_field_overview where con_id = "
				+ connectionId + " ";

		String strOpportinityOverView = "delete from dg_platform_qc.dg_opportunity_overview where con_id = "
				+ connectionId + " ";

		String strPersonaFieldEnrichment = "delete from dg_platform_qc.dg_persona_field_enrichment where con_id = "
				+ connectionId + " ";

		String strversionHistory = "delete from dg_platform_qc.persona_version_history where con_id = " + connectionId
				+ " ";

		DBUtil.deleteQuery(strDashboardOverView);
		DBUtil.deleteQuery(strFieldOverView);
		DBUtil.deleteQuery(strLiftTrend);
		DBUtil.deleteQuery(strPersonaCoverage);
		DBUtil.deleteQuery(strFieldEnrichment);
		DBUtil.deleteQuery(strTimesEnrichment);
		DBUtil.deleteQuery(strPersonaOverview);
		DBUtil.deleteQuery(personaListtrend);
		DBUtil.deleteQuery(strpersonaRecordStatus);
		DBUtil.deleteQuery(strFieldQQRecordStatus);
		DBUtil.deleteQuery(strSpreadOverFunction);
		DBUtil.deleteQuery(strSptreadOverlevel);
		DBUtil.deleteQuery(strChangeHistory);
		DBUtil.deleteQuery(strOpportunityFieldEnrichment);
		DBUtil.deleteQuery(strOpportunityFieldEnrichment);
		DBUtil.deleteQuery(stropportunityFieldOverview);
		DBUtil.deleteQuery(strOpportinityOverView);
		DBUtil.deleteQuery(strPersonaFieldEnrichment);
		DBUtil.deleteQuery(strversionHistory);
		DBUtil.deleteQuery(strFieldQualityStatus);

		String strPullTask = "String Select * from pull_task where con_id in (" + connectionId + ") order by id desc";
		String strPullBatch = "Select * from pull_batch where pull_task_id in (select id from pull_task where con_id in ("
				+ connectionId + ")) order by id desc";
		String strPushTask = "select * from push_task where con_id in (" + connectionId + ") order by id desc";
		String strpushBatch = "select * from push_batch where push_task_id in (select id from push_task where con_id in ("
				+ connectionId + "))";

		DBUtil.executeQuery(strPullTask);
		DBUtil.executeQuery(strPullBatch);
		DBUtil.executeQuery(strPushTask);
		DBUtil.executeQuery(strpushBatch);
	}

	public void deleteIndexesFromCCI(String url) {
		getResponse(url, null, null, "application/json", null, RequestMethodType.DELETE);
	}

	public void verifyOverview()
	{
		WebElement ele = driver.findElement(By.xpath("//ul/li/a[@title='DataGenie Tab']"));
		ele.click();
		
		driver.switchTo().frame("canvas-outer-_:DataGenie:j_id0:j_id2:canvasapp");
		
		WebElement eleOverview = driver.findElement(By.xpath("//ul[@class='page-tabs']/li/a[@title='Overview']"));
		eleOverview.click();
		
		WebElement leadWidget = driver.findElement(By.xpath("//div[@class ='repeater show-table-view posRel'][1]"));
		Assert.assertTrue(leadWidget.isDisplayed());		
		
		WebElement qualityWidget = driver.findElement(By.xpath("//*[@class ='mTop5 repeater gray'][contains(text(),'Quality')]"));
		Assert.assertTrue(qualityWidget.isDisplayed());
		
		
	}
}
