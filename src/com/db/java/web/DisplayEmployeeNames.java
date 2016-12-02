/**
	 *Class: DisplayEmployeeNames
	 *
	 *@author King Oruga
	 *@version 1.0 Course Written November 28, 2016
	 *
	 *This class displays employee names in a company department stored in a MySql database.
	 *
	 */
package com.db.java.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DisplayEmployeeNames
 */
@WebServlet("/DisplayEmployeeNames")
public class DisplayEmployeeNames extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DisplayEmployeeNames() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtain the department number from the HttpServletRequest object using
		// the variable dept_num submitted from the HTML form
		String deptNum = request.getParameter("dept_num");

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP
																					// 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		try {
			DBConnector connect = new DBConnector();
			Connection connection = connect.getConnection();
			PreparedStatement st = connection.prepareStatement("Select Fname, Lname, Dno From Employee Where Dno=?");

			st.setString(1, deptNum);

			ResultSet rs = st.executeQuery();
			int dno = 0;
			while (rs.next()) {
				String firstName = rs.getString("Fname");
				String lastName = rs.getString("Lname");
				dno = rs.getInt("Dno");

				printWriter.println(
						"<br><br><table bgcolor='white' border='5' style=\"margin: 0px auto;font-size:1.5em;\">");
				printWriter.println("<tr><td>First name: " + firstName + "</td></tr>");
				printWriter.println("<tr><td>Last name: " + lastName + "</td></tr>");
				printWriter.println("</table>");

			}
			int intDno = Integer.valueOf(deptNum); //convert the string value of deptNum to int for boolean check below
			//display the following on page if user enters wrong department number
			if (intDno != dno) {
				printWriter.println("<h1>You have entered an invalid department Number.</h1>");
				printWriter.println("<h1>Please enter one of the following options: 1, 4, and 5.</h1>");
				request.getRequestDispatcher("department_num.html").include(request, response); //redisplay entry form

			} else {
				request.getRequestDispatcher("menu.html").include(request, response);
			}
		} catch (Exception e) {
			// Error in obtaining active connection on Web page refresh since
			// user
			// has signed out previously
			printWriter.println("<h1>Error:" + e.getMessage() + "</h1>");
			printWriter.println("<h1>Signed Out</h1>");

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
