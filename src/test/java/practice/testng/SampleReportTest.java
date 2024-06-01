package practice.testng;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SampleReportTest {
	public ExtentReports report;

	@BeforeSuite
	public void configBS() {
		//Spark Report Config
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvancedReport/report.html");
		spark.config().setDocumentTitle("CRM TEST SUITE RESULT");
		spark.config().setReportName("CRM REPORT");
		spark.config().setTheme(Theme.DARK);

		//Add Env Info & Create Test
	    report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "CHROME-100");
	}
	@AfterSuite
	public void configAS() {
		//backup of the log
		report.flush();

	}


	@Test
	public void createContactTest() {
		
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8888");
		
		//take Screen Shot in base 64 type  so that we can attach
	TakesScreenshot eDriver = (TakesScreenshot)driver;
	String filePath = eDriver.getScreenshotAs(OutputType.BASE64);

		ExtentTest test= report.createTest("createContactTest");

		//To Generate Report
		test.log(Status.INFO,"login to app");	
		test.log(Status.INFO,"Navigate to contact page");	
		test.log(Status.INFO,"Create Contact");	
		if("LOGOoo".equals("LOGO")) {
			test.log(Status.PASS,"Contact is created");
		}else {
			test.addScreenCaptureFromBase64String(filePath,"ErrorFile");
		}
		driver.close();
	}
	@Test
	public void createContactWithOrgTest() {

		ExtentTest test= report.createTest("createContactWithOrgTest");

		//To Generate Report
		test.log(Status.INFO,"login to app");	
		test.log(Status.INFO,"Navigate to contact page");	
		test.log(Status.INFO,"Create Contact with Org");	
		if("HELLO".equals("HELLO")) {
			test.log(Status.PASS,"Contact is created");
		}else {
			test.log(Status.FAIL,"Contact is not created");
		}
	}
	@Test
	public void createContactWithPhoneNumberTest() {

		ExtentTest test= report.createTest("createContactWithPhoneNumberTest");
		//To Generate Report
		test.log(Status.INFO,"login to app");	
		test.log(Status.INFO,"Navigate to contact page");	
		test.log(Status.INFO,"Create Contact with Phone Number");	
		if("LAVA".equals("LAVA")) {
			test.log(Status.PASS,"Contact is created");
		}else {
			test.log(Status.FAIL,"Contact is not created");
		}
	}
}