package practice.homepagetest;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;



public class HomePageSampleTest {
	
	@Test
	public void homePageTest(Method mtd) {
		
		Reporter.log(mtd.getName() + " Test Start");
		SoftAssert assertObj = new SoftAssert();
	     Reporter.log("step 1",true);
	     Reporter.log("step 2",false);
	     Assert.assertEquals("Home", "Home");
	     Reporter.log("step 3",true);
	     assertObj.assertEquals("Title", "Title");
	     Reporter.log("step 4");
     assertObj.assertAll();//print the exception 
		Reporter.log(mtd.getName() + " Test End");			
		}


	@Test
	public void verifyLogoHomePageTest(Method mtd) {
		
		Reporter.log(mtd.getName() + " Test Start");
		SoftAssert assertObj = new SoftAssert();
		
	     Reporter.log("step 1",true);
	     Reporter.log("step 2",true);
	     Reporter.log("step 3",true);
	     Reporter.log("step 4",true);
	     assertObj.assertAll();//print the exception 
		Reporter.log(mtd.getName() + " Test End");			
		}

	
}