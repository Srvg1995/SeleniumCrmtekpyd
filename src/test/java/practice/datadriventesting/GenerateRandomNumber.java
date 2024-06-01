package practice.datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class GenerateRandomNumber {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		Random ran = new Random();
		int randomint = ran.nextInt(1000); // Set upper limit

		FileInputStream fis = new FileInputStream("C:\\Users\\Tushar Gavare\\Desktop\\data\\testScriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet("Sheet1").getRow(0).getCell(0).toString() + randomint;
		System.out.println(data);

	}

}
