package practice.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateContact_DP_Test2 {
	@Test(dataProvider="getData")
	public void createContactTest(String FirstName,String LastName,long phoneNumber) {
		System.out.println("FIRSTNAME :"+FirstName+",LASTNAME:"+LastName+",phoneNumber:"+phoneNumber);
		
	}
@DataProvider
public Object[][] getData(){
	Object[][] objArr = new Object[3][3];
	objArr[0][0]="deepak";
	objArr[0][1]="trainer";
	objArr[0][2]=5431753444L;
	
	objArr[1][0]="sam";
	objArr[1][1]="sh";
	objArr[1][2]=431447244L;


	objArr[2][0]="john";
	objArr[2][1]="smith";
	objArr[2][2]=965513444L;

	
	return objArr;

	
	

}
}
