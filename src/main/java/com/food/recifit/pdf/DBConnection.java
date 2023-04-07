package com.food.recifit.pdf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public Connection getConnection() {
		Connection connection=null;
		System.out.println("Connection called");
	try {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";
		
		Class.forName(driver);
		connection = DriverManager.getConnection(url, user, password);
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("드라이버를 찾지 못했습니다.");
	} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("DB 접속 오류");
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("알 수 없는 오류");
	}
	
	return connection;
	}
}
