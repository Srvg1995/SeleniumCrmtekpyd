package practice.datadriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class ExecuteSelectQueryTest {

	public static void main(String[] args) throws SQLException {

		Connection conn = null;

		try {
			// Step 1: load /register the database driver
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);

			// step 2:connect to database
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");

			System.out.println("======Connection Established=====");

			// step 3 : Create Sql statement
			Statement stat = conn.createStatement();

			// step 4: Execute select query & get result
			ResultSet resultset = stat.executeQuery("select * from project");
			while (resultset.next()) {
				System.out.println(
						resultset.getString(1) + "\t" + resultset.getString(2) + "\t" + resultset.getString(3) + "\t"
								+ resultset.getString(4) + "\t" + resultset.getString(5) + "\t" + resultset.getInt(6));
			}
		} catch (Exception e) {
			System.out.println("handle Exception");
		} finally {
			// step 5: close the connection
			conn.close();
			System.out.println("=======close the connetion=====");

		}

	}

}
