package practice.datadriventesting;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class CreateOrgTest {

	@Test
	public void createOrgtest() throws Throwable{
		//read data from Cmd line
		String URL = System.getProperty("url");
		String BROWSER = System.getProperty("browser");
		String USERNAME = System.getProperty("username");
		String PASSWORD = System.getProperty("password");
		
		//generate the random Number
		Random ran = new Random();
		int randomint = ran.nextInt(1000); // Set upper limit

	/*	// read common data from properties
		FileInputStream fis = new FileInputStream("C:\\Users\\Tushar Gavare\\Desktop\\data\\commondata.properties");
		Properties p = new Properties();
		p.load(fis);
		String browser  = p.getProperty("browser");
		String url      = p.getProperty("url");
		String username = p.getProperty("username");
		String password = p.getProperty("password");
		
		// read testscriptdata from excel
		FileInputStream fis1 = new FileInputStream("C:\\Users\\Tushar Gavare\\Desktop\\data\\testScriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		wb.getSheet("Sheet1").getRow(0).getCell(1).getStringCellValue();
		
		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new ChromeDriver();
		}
		*/
		// Step1 :Login to app
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		// step 2: navigate to the organisation module
		driver.findElement(By.linkText("Organizations")).click();
		
		// step 3: click on "create Organisations Button"
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		// step4:enter all the details and create new organisation
		driver.findElement(By.name("accountname")).sendKeys("facebook_"+randomint);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		Thread.sleep(1000);
		// step 5:logout
		Actions act = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		act.moveToElement(ele).perform();
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		driver.quit();
		
		
	}
}
