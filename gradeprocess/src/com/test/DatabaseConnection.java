package com.test;

import java.sql.*;

//데이터베이스 커넥션 객체 관리 클래스
public class DatabaseConnection {
	
	private static Connection conn = null;
	
	public static Connection connect() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//데이터베이스 연결에 필요한 인증 정보 등록
		conn = DriverManager.getConnection("jdbc:oracle:thin:test/0000@211.63.89.71:1521:xe");
		
		return conn;
	}
	
	public static void close() throws SQLException {
		if (conn != null)
			conn.close();
		conn = null;
	}
	
}
