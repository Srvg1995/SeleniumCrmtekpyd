package practice.datadriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

public class SampleUnitTestCheckProjectBackend {
	@Test
	public void projectCheckTest() throws SQLException {

		String expectedProjectName = "FACEBOOK";
		boolean flag = false;
		// Step 1: load /register the database driver
		Driver driver = new Driver();

		DriverManager.registerDriver(driver);

		// step 2:connect to database
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		System.out.println("======Connection Established=====");

		// step 3 : Create Sql statement
		Statement stat = conn.createStatement();

		// step 4: Execute select query & get result
		ResultSet resultset = stat.executeQuery("select * from project");
		while (resultset.next()) {
			String actprojname = resultset.getString(4);
			if (expectedProjectName.equals(actprojname)) {
				flag = true;
				System.out.println(expectedProjectName + "is available ===>pass");
			}
		}
		if (flag == false) {
			System.out.println(expectedProjectName + "is not available=====>Fail");
			Assert.fail();

			// step 5: close the connection
			conn.close();

		}

	}
}
