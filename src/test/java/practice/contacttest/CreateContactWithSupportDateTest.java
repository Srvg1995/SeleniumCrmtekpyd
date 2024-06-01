package practice.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreateContactWithSupportDateTest {

	public static void main(String[] args) throws InterruptedException, IOException {
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
				Sheet sh = wb.getSheet("contact");
				Row row = sh.getRow(4);
				String lastName = row.getCell(2).toString() + randomint;
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
				
				driver.findElement(By.linkText("Contacts")).click();
				
				// step 3: click on "create Organizations Button"
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				
				// step4:enter all the details and create new organization

				Date dateObj = new Date();
				SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
				String startDate = sim.format(dateObj);				
				
				Calendar cal = sim.getCalendar();
				cal.add(Calendar.DAY_OF_MONTH,30);
				String endDate = sim.format(cal.getTime());

				driver.findElement(By.name("lastname")).sendKeys(lastName);					
				driver.findElement(By.name("support_start_date")).clear();
				driver.findElement(By.name("support_start_date")).sendKeys(startDate);

				driver.findElement(By.name("support_end_date")).clear();
				driver.findElement(By.name("support_end_date")).sendKeys(endDate);

				
				
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				Thread.sleep(1000);
				
				//verify the Start Date and End Date
				
				String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
				if(actStartDate.equals(startDate)) {
					System.out.println(startDate + " Information is verified ====>PASS");
				}else {
					System.out.println(startDate + " Information is not verified ====>FAIL");

				}
				String actEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
				if(actEndDate.equals(endDate)) {
					System.out.println(endDate + " Information is verified ====>PASS");
				}else {
					System.out.println(endDate + " Information is not verified ====>FAIL");

				}
				// step 5:logout
				driver.quit();
	}

}
