/**
     *Class: DisplayDepartments
     *
	 *@author King Oruga
	 *@version 1.0 Course Written November 28, 2016
	 *
	 *This class displays department names, number, and manager info
	 *in a company department stored in a MySql database.
	 *
	 */
package com.db.java.web;

import java.io.IOException;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class DisplayDepartment
 */
@WebServlet("/DisplayDepartments")
public class DisplayDepartments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayDepartments() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); 
		response.setContentType("text/html");
		PrintWriter printWriter  = response.getWriter();
		try{
			//Use the default constructor since connection is already created
			DBConnector connect = new DBConnector();
			
			//Obtain the active connection by accessing the static Connection variable
			Connection connection = connect.getConnection();

			//Embbed SQL statement using class PreparedStatement
			PreparedStatement st = connection.prepareStatement("Select * From Department");

			//Execute the query and create a reference to the relation returned by the query
			ResultSet rs = st.executeQuery();
			//Loop until all rows are displayed
			while(rs.next()){
				int dnumber = rs.getInt("Dnumber");
				String dname = rs.getString("Dname");
				int mgr_ssn = rs.getInt("Mgr_ssn");
				String mgr_start_date = rs.getString("Mgr_start_date");
			
				//Write the HTML tags and database information to the Web browser
				//<br> breaks to a new line
				//<tr> </tr>specifies a table row
				//<td> </td> specifies a table column
				printWriter.println("<br><br><table bgcolor='white' border='5' style=\"margin: 0px auto;font-size:1.5em;\">");
				printWriter.println("<tr><td><Dept_num: " + dnumber +"</td></tr>");
				printWriter.println("<tr><td>Dept_name: " + dname +"</td></tr>");
				printWriter.println("<tr><td>Manager_ssn: " + mgr_ssn +"</td></tr>");
				printWriter.println("<tr><td>Manager_start_date: " + mgr_start_date +"</td></tr>");
				printWriter.println("</table>");
			}
		
			request.getRequestDispatcher("menu.html").include(request, response); 
		}
		catch(Exception e){
			//Error in obtaining active connection on Web page refresh since user 
			//has signed out previously
			printWriter.println("<h1>Error:"+e.getMessage()+"</h1>");
			printWriter.println("<h1>Signed Out</h1>");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
