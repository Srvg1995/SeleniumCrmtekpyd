package practice.homepagetest;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;



public class HomePageVerification {
	
	@Test
	public void homePageTest(Method mtd) {
		
		System.out.println(mtd.getName() + " Test Start");
		String expectedPage="Home page";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://localhost:8888");

		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		String actTitle=driver.findElement(By.xpath("//a[contains(text(),'Home')]")).getText();
		//Hard Assert
		Assert.assertEquals(actTitle, expectedPage);
		
		/*if(actTitle.trim().equals(expectedPage)) {
			System.out.println(expectedPage+"Page is Verified==Pass");
		}else {
				System.out.println(expectedPage+"Page is not Verified==Fail");

			}*/
		driver.close();
		System.out.println(mtd.getName() + " Test End");			
		}


	@Test
	public void verifyLogoHomePageTest(Method mtd) {
		
		System.out.println(mtd.getName() + " Test Start");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://localhost:8888");

		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		boolean status =driver.findElement(By.xpath("//img[@title='vtiger-crm-logo.gif']")).isEnabled();
		//hard assert
		Assert.assertTrue(status);
		//Assert.assertEquals(true, status); one more way 
		
		/*if(status) {
			System.out.println("Logo is Verified==Pass");
		}else {
				System.out.println("Logo is not Verified==Fail");

			}*/
		driver.close();

		System.out.println(mtd.getName() + " Test End");			
		}

	
}