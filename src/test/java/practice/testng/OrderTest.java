package practice.testng;

import org.testng.annotations.Test;

public class OrderTest  {
	
	@Test(priority=-1)
	public void createContact() {
		System.out.println("Execute create contact with ====>HDFC");
	}
	
	@Test(priority=0)
	public void modifyContactTest() {
		System.out.println("Execute modify contact");
	}
	

	@Test(priority=3)
	public void DeleteContactTest() {
		System.out.println("Execute Delete contact");
	}
	
}


