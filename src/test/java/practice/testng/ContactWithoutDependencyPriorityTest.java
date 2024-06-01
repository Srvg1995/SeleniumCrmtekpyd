package practice.testng;

import org.testng.annotations.Test;

public class ContactWithoutDependencyPriorityTest {

	@Test
	public void createContactTest()
	{
		System.out.println("Execute createContactTest with -->HDFC");
	}
	@Test
	public void modifyContactTest()
	{
		System.out.println("Execute query insert contact in DB===>ICICI");
		System.out.println("execute modify contact test--->ICICI==>ICICI_1");
	}
	
	@Test
	public void DeleteContactTest()
	{
		System.out.println("Execute query insert contact in DB===>UPI");
		System.out.println("execute delete contact test UPI");
	}
}
