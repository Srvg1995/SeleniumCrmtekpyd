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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrgTestXml {

	public static void main(String[] args) throws InterruptedException, IOException {
    
		//generate the random Number
		Random ran = new Random();
		int randomint = ran.nextInt(1000); // Set upper limit

		// read common data from properties
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
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			driver = new ChromeDriver();
		} else if (browser.equals("edge")) {
			driver = new ChromeDriver();
		}
		
		// Step1 :Login to app
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		// step 2: navigate to the organisation module
		driver.findElement(By.linkText("Organizations")).click();
		
		// step 3: click on "create Organisations Button"
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		// step4:enter all the details and create new organisation
		driver.findElement(By.name("accountname")).sendKeys("facebook_"+randomint);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		Thread.sleep(1000);
		
	/*	//verify the organization name in the header of the message
		String header = driver.findElement(By.className("dvHeaderText")).getText();
		if(header.contains("facebook_"))
		{
			System.out.println("Org is successfully created hence pass");
		}
		else {
			System.out.println("Org not Created hence fail");
		}
		*/
		// step 5:logout
		Actions act = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		act.moveToElement(ele).perform();Thread.sleep(1000);
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}

}
