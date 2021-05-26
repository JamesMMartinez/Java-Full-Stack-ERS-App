package com.revature.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection {
	
	private static Connection connection;
	
	private PostgresConnection() {
		
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		
		String url = System.getenv("DB_URL_AWS");
		String username = System.getenv("DB_USERNAME_AWS");
		String password = System.getenv("DB_PASSWORD_AWS");
		
		try{ Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
		e.printStackTrace(); }
		
		if(connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, username, password);
		}
		
		return connection;
	}

}


