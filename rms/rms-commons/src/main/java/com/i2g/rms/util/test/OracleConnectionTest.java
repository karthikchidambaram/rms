package com.i2g.rms.util.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class OracleConnectionTest {

	public static void main(String args[]) throws Exception {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "scott", "tiger");
		System.out.println("Connection established..");

		if (conn != null) {
			conn.close();
			System.out.println("Connection closed..");
		}

	}
}
