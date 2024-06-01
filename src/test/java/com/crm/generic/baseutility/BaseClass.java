package com.crm.generic.baseutility;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {

	@BeforeSuite
	public void configBeforeSuite() {
		System.out.println("====Connect To Database,Report Config=====");
	}
	@BeforeClass
	public void configBeforeclass() {
		System.out.println("===Launch The Browser===");
	}
	@BeforeMethod
	public void configBeforeMethod() {
		System.out.println("===Login To Application===");
	}
	@AfterMethod
	public void configAfterMethod(){
		System.out.println("===Logout To Application===");	
	}
	@AfterClass
	public void configAfterclass() {
		System.out.println("==Close The Browser==");
	}
	
	@AfterSuite
	public void configAfterSuite() {
		System.out.println("=====Close Database,Report Backup======");
	}
	

	
}

