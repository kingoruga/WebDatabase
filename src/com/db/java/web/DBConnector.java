/**
	 *Class: DBConnector
	 *
	 *@author Dr. Anil Pereira
	 *This class creates a connection to a MySql database using the jdbc driver.
	 *
	 */
package com.db.java.web;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
	//Create URI to connect to the mysql database
		private final static String URI = "jdbc:mysql://localhost/";
		
		//Create a variable to hold the connection
		static Connection conn;
		
		//no-arg constructor
		public DBConnector() throws Exception{}
		
		//Create constructor to load the jdbc:mysql driver and make the conection
		public DBConnector (String dbName, String username, String password) throws Exception{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(URI + dbName + "?useSSL=False", username, password);
			//System.out.println("Connection Established");
		}
		
		public Connection getConnection(){
			return conn;
		}
		

}
