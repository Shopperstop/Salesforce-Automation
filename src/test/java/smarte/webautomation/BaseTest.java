package smarte.webautomation;

import java.io.File;
import java.io.InputStream;

import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import smarte.utility.DBUtil;

@Listeners({ BaseTest.class, smarte.report.TestNGListener.class })
public class BaseTest implements ISuiteListener {
	public static WebDriver driver;
	private DesiredCapabilities desiredCapabilities;

	private static String currentDriver = "";
	private static String driverFile = "";
	// public static DBUtil dbUtil;

	@BeforeTest()
	public void initializemanager() {
		setDriver("chrome");
		driver.get(getApplicationUrl("salesforce"));
		driver.manage().window().maximize();
		WebpageFactory.initializePageObjects(driver);
		DBUtil.createConnection();
	}

	@AfterSuite
	public void closeConnection() {
		DBUtil.createConnection();
	}

	public WebDriver getDriver() {
		if (driver instanceof ChromeDriver) {
			return (ChromeDriver) driver;
		} else if (driver instanceof InternetExplorerDriver) {
			return (InternetExplorerDriver) driver;
		} else if (driver instanceof FirefoxDriver) {
			return (FirefoxDriver) driver;
		}
		return driver;
	}

	public String getApplicationUrl(String app) {
		switch (app.toLowerCase()) {
		case "salesforce":
			return "http://login.salesforce.com";

		default:
			return "http://login.salesforce.com";
		}
	}

	private final static String getChromeDriverLocation() {
		String osName = System.getProperty("os.name").toLowerCase();
		String driverResource = "";
		if (osName.startsWith("windows")) {
			driverResource = "src\\test\\resources\\driver\\chromedriver.exe";
		} else if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")) {
			driverResource = "chromedriver";
		}
//		InputStream driverStream = WebConfigHandler.class.getResourceAsStream(driverResource);
//		return getTempDriver(driverStream, "CHROME");
		return driverResource;
		// return "";
	}

	protected void setDesiredCapabilites(String driverType) {
		switch (driverType.toLowerCase()) {
		case "chrome":
			desiredCapabilities = DesiredCapabilities.chrome();
			break;
		case "ie":
			this.desiredCapabilities = DesiredCapabilities.internetExplorer();
			break;
		case "firefox":
			this.desiredCapabilities = DesiredCapabilities.firefox();
			break;

		/*
		 * case "cloud": DesiredCapabilities cloudDesiredCapabilities = new
		 * DesiredCapabilities();
		 * 
		 * this.desiredCapabilities = cloudDesiredCapabilities; break;
		 */

		default:
			desiredCapabilities = DesiredCapabilities.chrome();
			break;
		}
	}

	public void setDriver(String driverType) {
		setDesiredCapabilites(driverType);
		// addDownloadCapability(driverType,getDesiredCapabilities());
		switch (driverType.toLowerCase()) {
		// case "chrome":
		case "chrome":
			String chromeDriverPath = "";
			try {
				chromeDriverPath = getChromeDriverLocation();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			driver = new ChromeDriver(getDesiredCapabilities());
			// driver = new ChromeDriver();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;

		case "firefox":
			String firefoxDriverPath = "";
			try {
				// firefoxDriverPath = getFirefoxDriverLocation();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
			driver = new FirefoxDriver(getDesiredCapabilities());
			break;

		case "ie":
			String ieDriverPath = "";
			try {
				// ieDriverPath = getIEDriverLocation();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			System.setProperty("webdriver.ie.driver", ieDriverPath);
			driver = new InternetExplorerDriver(getDesiredCapabilities());
			break;

		/*
		 * case "cloud": try{ driver = new RemoteWebDriver(new
		 * URL("http://"+"rashmiagrawal1"+":"+"rGs6EgvHXQF72svfYqyb"+"@"+
		 * "hub-cloud.browserstack.com"+"/wd/hub"), getDesiredCapabilities()); } catch
		 * (Exception e) { System.out.println(e.getMessage()); } break;
		 */

//		case "headlesschrome":
//			driver = SeleniumUtility.getHeadlessDriver();
//			break;			
		default:
			String defaultDriverPath = "";
			try {
				defaultDriverPath = getChromeDriverLocation();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			System.setProperty("webdriver.chrome.driver", defaultDriverPath);
			driver = new ChromeDriver(getDesiredCapabilities());
			break;
		}
	}

	public DesiredCapabilities getDesiredCapabilities() {
		return this.desiredCapabilities;
	}

	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub

	}

}
