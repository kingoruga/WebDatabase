/**
	 *Class: InsertEmployee
	 *
	 *@author King Oruga
	 *@version 1.0 Course Written November 28, 2016
	 *
	 *This class inserts an employee in a company department stored in a MySql database.
	 *
	 */
package com.db.java.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertEmployee
 */
@WebServlet("/InsertEmployee")
public class InsertEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertEmployee() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP
																					// 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html");

		PrintWriter printWriter = response.getWriter();
		String fName = request.getParameter("Fname");
		String minit = request.getParameter("Minit");
		String lName = request.getParameter("Lname");
		String ssn = request.getParameter("Ssn");
		String bDate = request.getParameter("Bdate");
		String address = request.getParameter("Address");
		String gender = request.getParameter("Sex");
		String salary = request.getParameter("Salary");
		String supSsn = request.getParameter("Super_ssn");
		String dNo = request.getParameter("Dno");
		try {
			DBConnector connect = new DBConnector();
			Connection connection = connect.getConnection();
			PreparedStatement stmt = connection.prepareStatement("Insert into Employee Values(?,?,?,?,?,?,?,?,?,?)");

			// Set value to parameter "?" using variable deptNum set above
			// set value to parameter "?"
			stmt.setString(1, fName);
			stmt.setString(2, minit);
			stmt.setString(3, lName);
			stmt.setString(4, ssn + "");
			stmt.setString(5, bDate);
			stmt.setString(6, address);
			stmt.setString(7, gender);
			stmt.setString(8, salary + "");
			stmt.setString(9, supSsn + "");
			stmt.setString(10, dNo + "");

			printWriter
					.println("<br><br><table bgcolor='white' border='5' style=\"margin: 0px auto;font-size:1.5em;\">");
			printWriter.println("<tr><td>First name: " + fName + "</td></tr>");
			printWriter.println("<tr><td>Last name: " + minit + "</td></tr>");
			printWriter.println("<tr><td>First name: " + lName + "</td></tr>");
			printWriter.println("<tr><td>Last name: " + ssn + "</td></tr>");
			printWriter.println("<tr><td>First name: " + bDate + "</td></tr>");
			printWriter.println("<tr><td>Last name: " + address + "</td></tr>");
			printWriter.println("<tr><td>First name: " + gender + "</td></tr>");
			printWriter.println("<tr><td>Last name: " + salary + "</td></tr>");
			printWriter.println("<tr><td>Last name: " + supSsn + "</td></tr>");
			printWriter.println("<tr><td>Last name: " + dNo + "</td></tr>");
			printWriter.println("</table>");

			request.getRequestDispatcher("employee_info.html").include(request, response);
			stmt.executeUpdate();
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
