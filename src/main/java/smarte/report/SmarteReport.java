package smarte.report;

import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class SmarteReport {
	
	public ExtentHtmlReporter htmlReport = new ExtentHtmlReporter(System.getProperty("user.dir") + "/target/surefire-reports/Smarte_Report.html");
	public ExtentReports extent = new ExtentReports();
	public ExtentTest test ;
	
	public void startTest(ITestResult result)
	{
		String testClass = result.getTestClass().getName();
		testClass = testClass.substring(testClass.lastIndexOf(".")+1, testClass.length());
		
		test = extent.createTest(testClass+"::"+result.getName());
		if(extent.getStartedReporters().size() == 0) {
			
		    extent.attachReporter(htmlReport);
			extent.setSystemInfo("Environment","QA");
			extent.setSystemInfo("User Name",System.getProperty("user.name"));
			
			htmlReport.config().enableTimeline(false);
			htmlReport.config().setReportName("smarte Automation Report");
			htmlReport.config().setDocumentTitle("Smarte Automation Report");			
		}
	}
	
	public void saveReport() {
		extent.flush();
	}
	
	public void getResult(ITestResult result) {
		
		String testClass = result.getTestClass().getName();
		testClass = testClass.substring(testClass.lastIndexOf(".")+1,testClass.length());
		
		for(String message : Reporter.getOutput(result)){
			test.log(Status.INFO, message);		}		

		if(result.getStatus() == ITestResult.FAILURE){
				test.log(Status.FAIL, MarkupHelper.createLabel("Test Case Failed : "+result.getName(), ExtentColor.RED));
				test.log(Status.FAIL, MarkupHelper.createLabel("Reason : "+result.getThrowable(), ExtentColor.RED));
				test.log(Status.FAIL, result.getThrowable());
				test.assignCategory(testClass);
		}
		else if(result.getStatus() == ITestResult.SKIP && result.getThrowable() instanceof Exception) {
			test.assignCategory(testClass);
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped due to failed configuration", ExtentColor.ORANGE));
		}
		else if(result.getStatus() == ITestResult.SKIP ) {
			test.assignCategory(testClass);
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped because  it depends on not successfully finished methods", ExtentColor.ORANGE));
		}
		else if(result.getStatus() == ITestResult.SUCCESS){
			//test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
			test.assignCategory(testClass);
		}		
	}
}
