package practice.datadriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class ExecuteNonSelectQuery {

	public static void main(String[] args) throws SQLException {

		// Step 1: load /register the database driver
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);

		// step 2:connect to database
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		System.out.println("======Connection Established=====");

		// step 3 : Create Sql statement
		Statement stat = conn.createStatement();

		// step 4: Execute select query & get result
		int result = stat
				.executeUpdate(" insert  into project values('TY_PROJ_2001','DEEPAK','3/4/24','MYSQL','ONGOING',105);");
		System.out.println(result);

		// step 5: close the connection
		conn.close();

	}
}
