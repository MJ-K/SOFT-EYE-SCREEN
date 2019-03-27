package setting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Setting {
	public static void main(String[] args) {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
		String user = "root";
		String password = "1234";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery("show databases like 'softDB'");
			if(rs.next()) {
				stmt.executeUpdate("drop database softDB");
			}
			
			stmt.executeUpdate("create database softDB");
			stmt.executeUpdate("use softDB");
			
			StringBuffer createTable = new StringBuffer();
			createTable.append("create table user ");
			createTable.append("(id int not null primary key, ");
			createTable.append("passwd varchar(30) not null, ");
			createTable.append("name varchar(20) not null, ");
			createTable.append("isStudent tinyint not null, ");
			createTable.append("isConnect tinyint not null) ");
			
			stmt.executeUpdate(createTable.toString());
			
			stmt.executeUpdate("alter table user convert to charset utf8");
			
			System.out.println("table create completed");
			
			StringBuffer insertData = new StringBuffer();
			insertData.append("insert into user values(?, ?, ?, ?, ?) ");
			PreparedStatement pstmt = conn.prepareStatement(insertData.toString());
			
			File file = new File("src/txt/user.txt");
			
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
				
				String line = "";
				int flag = 0;
				
				while((line = br.readLine()) != null) {
					if(flag == 0) {
						flag++;
						continue;
					}
					
					String strArray[] = line.split("\t");
					
					for(int j=0; j<strArray.length; j++){
						System.out.print(strArray[j] + "\t");
					}
					System.out.println();
					
					pstmt.setInt(1, Integer.parseInt(strArray[0]));
					pstmt.setString(2, strArray[1]);
					pstmt.setString(3, strArray[2]);
					pstmt.setInt(4, Integer.parseInt(strArray[3]));
					pstmt.setInt(5, Integer.parseInt(strArray[4]));
					
					pstmt.executeUpdate();
				}
				
				br.close();
				pstmt.close();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			stmt.executeQuery("use mysql");
			
			rs = stmt.executeQuery("select host, user, authentication_string from user where user = 'softUser'");
			while(rs.next()) {
				stmt.executeUpdate("drop user 'softUser'@'localhost'");
			}
			stmt.executeUpdate("create user 'softUser'@'localhost' identified by '1234'");
			stmt.executeUpdate("grant select, update on softDB.* to 'softUser'@'localhost'");
			
			System.out.println("권한 생성 완료");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				if(stmt != null) stmt.close();
				if(rs != null) rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
