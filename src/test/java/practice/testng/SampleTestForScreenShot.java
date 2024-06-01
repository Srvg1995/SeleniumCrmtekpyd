package practice.testng;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;


public class SampleTestForScreenShot {
	@Test
	public void amazonTest() throws IOException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://amazon.com");
			
		
		//step 1 create an object to eventFiring webdriver
		EventFiringWebDriver edriver = new EventFiringWebDriver(driver);
		
		//step 2:use get screenshotAs method to get file type of screenshot
		File srcFile = edriver.getScreenshotAs(OutputType.FILE);
		
		//step 3 :store Screenshot in local drive
		 FileUtils.copyFile(srcFile,new File("./Screenshot/test.png"));
	}

}
