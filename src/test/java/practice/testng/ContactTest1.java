package practice.testng;

import org.testng.annotations.Test;

public class ContactTest1 {
	
	@Test()
	public void createContact() {
		System.out.println("Execute create contact with ====>HDFC");
	}
	
	@Test(dependsOnMethods="createContact")
	public void modifyContactTest() {
		System.out.println("Execute modify contact");
	}
	

	@Test(dependsOnMethods="modifyContactTest")
	public void DeleteContactTest() {
		System.out.println("Execute Delete ICICI");
	}
	
}
