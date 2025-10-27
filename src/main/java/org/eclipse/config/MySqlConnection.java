package org.eclipse.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnection {
	private static Connection connection = null;

	static {
		String username = "root";
		String password = "password";
		String url = "jdbc:mysql://localhost:3306/bdd_projet_fil_rouge_room_1";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.err.println("Problème de connexion avec MySQL");
		}
	}

	private MySqlConnection() {
	}

	public static Connection getConnection() {
		return connection;
	}
}
