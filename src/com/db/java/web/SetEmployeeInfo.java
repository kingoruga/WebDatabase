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
 * Servlet implementation class SetEmployeeInfo
 */
@WebServlet("/SetEmployeeInfo")
public class SetEmployeeInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SetEmployeeInfo() {
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

		try {
			// Obtain the connection and test if it is closed, indicating user
			// signed out
			DBConnector connect = new DBConnector();
			Connection connection = connect.getConnection();
			if (connection.isClosed())
				throw new Exception("Connection closed");

			request.getRequestDispatcher("employee_info.html").include(request, response);
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
