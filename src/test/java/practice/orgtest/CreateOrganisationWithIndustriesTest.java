package practice.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateOrganisationWithIndustriesTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		
	//read common data from property file
		
		FileInputStream fis = new FileInputStream("C:\\Users\\Tushar Gavare\\Desktop\\data\\commondata.properties");
		Properties p = new Properties();
		p.load(fis);
		String BROWSER  = p.getProperty("browser");
		String URL      = p.getProperty("url");
		String USERNAME = p.getProperty("username");
		String PASSWORD = p.getProperty("password");
		
		//generate the random Number
		
				Random ran = new Random();
				int randomint = ran.nextInt(1000); // Set upper limit

				// read testscriptdata from excel
				
				FileInputStream fis1 = new FileInputStream("C:\\Users\\Tushar Gavare\\Desktop\\data\\testScriptdata.xlsx");
				Workbook wb = WorkbookFactory.create(fis1);
				Sheet sh = wb.getSheet("org");
				Row row = sh.getRow(4);
				String orgName = row.getCell(2).toString() + randomint;
				String industry = row.getCell(3).toString();
				String type = row.getCell(4).toString();

				wb.close();
				
				WebDriver driver = null;
				
				if (BROWSER.equals("chrome")) {
					driver = new ChromeDriver();
				} else if (BROWSER.equals("firefox")) {
					driver = new FirefoxDriver();
				} else if (BROWSER.equals("edge")) {
					driver = new EdgeDriver();
				}else {
					driver = new ChromeDriver();
				}
				
				// Step1 :Login to app
				
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				driver.manage().window().maximize();
				driver.get(URL);
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();
				
				// step 2: navigate to the organization module
				
				driver.findElement(By.linkText("Organizations")).click();
				
				// step 3: click on "create Organizations Button"
				driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
				
				// step4:enter all the details and create new organization
				driver.findElement(By.name("accountname")).sendKeys(orgName);
				WebElement wbsele1 = driver.findElement(By.name("industry"));
				Select sel1 = new Select(wbsele1);
				sel1.selectByVisibleText(industry);
				
				WebElement wbsele2 = driver.findElement(By.name("accounttype"));
				Select sel2 = new Select(wbsele2);
				sel2.selectByVisibleText(type);
				
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				Thread.sleep(1000);
				
				//verify the industries and type info
				
				String actIndustries = driver.findElement(By.id("dtlview_Industry")).getText();
				
				if(actIndustries.equals(industry)) {
					System.out.println(industry + " Information is verified ====>PASS");
				}else {
					System.out.println(industry + " Information is not verified ====>FAIL");

				}
				
				String actType = driver.findElement(By.id("dtlview_Type")).getText();
				if(actType.equals(type)) {
					System.out.println(type + " type is verified ====>PASS");
				}else {
					System.out.println(type + " type is not verified ====>FAIL");

				}
				// step 5:logout
				driver.quit();

	}

}
