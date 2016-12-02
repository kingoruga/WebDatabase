/**
	 *Class: UpdateDepartmentManager
	 *
	 *@author King Oruga
	 *@version 1.0 Course Written November 28, 2016
	 *
	 *This class updates the department manager's info in a company department stored in a MySql database.
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
 * Servlet implementation class UpdateDepartmentManager
 */
@WebServlet("/UpdateDepartmentManager")
public class UpdateDepartmentManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateDepartmentManager() {
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
		String mgrSsn = request.getParameter("Mgr_ssn");
		String mgrStart = request.getParameter("Mgr_start_date");
		try {
			DBConnector connect = new DBConnector("comapny1", "user1", "user1_pass");
			Connection conn = connect.getConnection();

			PreparedStatement stmt = conn.prepareStatement("Update Department Set Mgr_ssn=? where Dnumber=?");

			// set value to parameter "?"
			stmt.setString(1, mgrSsn + "");
			stmt.setString(2, mgrStart + "");
			stmt.executeUpdate();
			request.getRequestDispatcher("department_manager_info.html").include(request, response);
			printWriter.println("Department Mgr. Updated!");
		} catch (Exception e) {
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
