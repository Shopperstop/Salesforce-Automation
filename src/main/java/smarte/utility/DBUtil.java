package smarte.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.testng.Reporter;

public class DBUtil {

	static PreparedStatement stmt = null;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection dbConn;

	public static Connection createConnection() {
		try {

			String environment = "DG_QC";
			String connStr = "jdbc:mysql://192.168.15.6:3306/dg_platform_qc";
			String userName = "root";
			String password = "Data@G456!";
			dbConn = DriverManager.getConnection(connStr, userName, password);
			return dbConn;
		} catch (Exception e) {
			System.out.println("Error : ");
			e.printStackTrace();
			Reporter.log("ERROR while connecting to Database");
			return null;
		}
	}

	public static void closeDBConnection() {
		try {
			if (dbConn != null)
				dbConn.close();
			if (stmt != null)
				stmt.close();
		} catch (Exception e) {

		}
	}

	public static ResultSet executeQuery(String sqlQuery) {
		try {
			stmt = DBUtil.dbConn.prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();
			return rs;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Reporter.log(e.getMessage());
			return null;
		}
	}
	
	public static void deleteQuery(String sqlQuery) {
		try {
			stmt = DBUtil.dbConn.prepareStatement(sqlQuery);
			stmt.execute();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Reporter.log(e.getMessage());
		}
	}

	public static int executeUpdate(String sqlQuery) {
		try {
			stmt = DBUtil.dbConn.prepareStatement(sqlQuery);
			return stmt.executeUpdate();
		} catch (Exception e) {
			Reporter.log(e.getMessage());
			System.out.println(e);
			return 0;
		}
	}

	public static String[] getColumnValue(ResultSet rs, String columnName) {
		try {
			ArrayList<String> result = new ArrayList<String>();

			while (rs.next()) {
				result.add(rs.getString(columnName));
			}
			rs.beforeFirst();
			if (result.size() == 0) {
				Reporter.log("No values retrieved for column " + columnName + " as ResultSet was empty", true);
			}
			return result.toArray(new String[result.size()]);
		} catch (Exception e) {
			Reporter.log(e.getMessage());
			return null;
		}
	}
}
