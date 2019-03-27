package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static DBConnection dc = null;
	private static Connection conn = null;

	final String DRIVER = "com.mysql.jdbc.Driver";
	final String URL = "jdbc:mysql://192.168.0.25:3306/softDB?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
	
	final String USER = "softUser";
	final String PASSWORD = "1234";
	
	private DBConnection() {}
	
	public static DBConnection getInstance() {
		if(dc == null) dc = new DBConnection();
		
		return dc;
	}
	
	public Connection getConnection() {
		if(conn == null) {
			try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("conn: " + conn);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return conn;
	}
}
