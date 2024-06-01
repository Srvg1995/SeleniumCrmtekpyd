package practice.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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
import org.openqa.selenium.interactions.Actions;

public class CreateConatctWithOrgTest {

	public static void main(String[] args) throws IOException, InterruptedException {

		// read common data from property file

		FileInputStream fis = new FileInputStream("C:\\Users\\Tushar Gavare\\Desktop\\data\\commondata.properties");
		Properties p = new Properties();
		p.load(fis);
		String BROWSER =  p.getProperty("browser");
		String URL =      p.getProperty("url");
		String USERNAME = p.getProperty("username");
		String PASSWORD = p.getProperty("password");

		// generate the random Number

		Random ran = new Random();
		int randomint = ran.nextInt(1000); // Set upper limit

		// read testscriptdata from excel

		FileInputStream fis1 = new FileInputStream("C:\\Users\\Tushar Gavare\\Desktop\\data\\testScriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("contact");
		Row row = sh.getRow(7);
		String orgName = row.getCell(2).toString() + randomint;
		String contactLastName = row.getCell(3).getStringCellValue();

		wb.close();

		WebDriver driver = null;

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
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
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		Thread.sleep(1000);

		// verify header message Expected Result
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + " header is created ====>PASS");
		} else {
			System.out.println(orgName + " is not created ====>FAIL");

		}

		// step 5:navigate to contact module
		driver.findElement(By.linkText("Contacts")).click();

		// step 6: click on "create Organizations Button"
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// step 7:enter all the details and create new organization
		driver.findElement(By.name("lastname")).sendKeys(contactLastName);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

		// switch to child window
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String windowID = it.next();
			driver.switchTo().window(windowID);

			String actUrl = driver.getCurrentUrl();
			if (actUrl.contains("module=Accounts")) {
				break;
			}
		}
		driver.findElement(By.name("search_text")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

		// switch to Parent Window
		Set<String> set1 = driver.getWindowHandles();
		Iterator<String> it1 = set1.iterator();
		while (it1.hasNext()) {
			String windowID = it1.next();
			driver.switchTo().window(windowID);

			String actUrl = driver.getCurrentUrl();
			if (actUrl.contains("Contacts&action")) {
				break;
			}
		}
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		Thread.sleep(1000);

		// verify the header of contact
		headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(contactLastName)) {
			System.out.println(contactLastName + " header is verified ====>PASS");
		} else {
			System.out.println(contactLastName + " header is not verified ====>FAIL");

		}

		// verify the head orgName
		String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		System.out.println(actOrgName);
		if (actOrgName.trim().equals(orgName)) {
			System.out.println(orgName + " Information is created ====>PASS");
		} else {
			System.out.println(orgName + " Information is not created ====>FAIL");

		}

		// step 5:logout
		Actions act = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		act.moveToElement(ele).perform();Thread.sleep(1000);
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();

	}

}
