package practice.datadriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.mysql.jdbc.Driver;

public class CreateProjectAndVerifyDataInDBWithGUI {

	public static void main(String[] args) throws SQLException, InterruptedException {
   
		//Create Project in Gui Using Selenium Code
		String projectName = "Instagram_65";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		driver.get("http://106.51.90.215:8084/");
		
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		
		driver.findElement(By.linkText("Projects")).click();
		
		driver.findElement(By.xpath("//span[text()='Create Project']")).click();
		
		driver.findElement(By.name("projectName")).sendKeys(projectName);
		driver.findElement(By.name("createdBy")).sendKeys("Tushar");
		Thread.sleep(2000);
		WebElement ele = driver.findElement(By.xpath("//label[.='Project Status ']/following-sibling::select"));
		Select sel = new Select(ele);
		sel.selectByVisibleText("Created");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();
		//driver.close();
		
		//Verify the project in Backend DB using JDBC
		boolean flag = false;
		Driver driverref = new Driver();
		DriverManager.registerDriver(driverref);
		Connection conn = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects", "root@%", "root");
		System.out.println("======Connection Established=====");

		Statement stat = conn.createStatement();

		ResultSet resultset = stat.executeQuery("select * from project");
		while (resultset.next()) {
			String actprojname = resultset.getString(4);
			if (projectName.equals(actprojname)) {
				flag = true;
				System.out.println(projectName + " is available in DB===>Pass");
			}
		}
		if (flag == false) {
			System.out.println(projectName + " is not available in DB =====>Fail");	
	}conn.close();
}
	}
