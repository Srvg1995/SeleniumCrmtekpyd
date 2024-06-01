package practice.datadriventesting;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataBackToExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		FileInputStream fis = new FileInputStream("C:\\Users\\Tushar Gavare\\Desktop\\data\\testScriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Sheet1");
		Row rw = sh.getRow(7);
		Cell cl = rw.createCell(3);
		cl.setCellValue("Fail");

		FileOutputStream fos = new FileOutputStream("C:\\Users\\Tushar Gavare\\Desktop\\data\\testScriptdata.xlsx");
		wb.write(fos);
		wb.close();
		System.out.println("===Executed====");
	}

}
