package com.db.java.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
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
			//Obtain the active connection and close it since user has signed out
			DBConnector connect = new DBConnector();
			Connection connection = connect.getConnection();
			connection.close();
		}
		catch(Exception e){
			//Error in obtaining active connection on Web page refresh since user 
			//has signed out previously
			printWriter.println("<h1>Error:"+e.getMessage()+"</h1>");
			printWriter.println("<h1>Signed Out</h1>");
		}

		//Database connection was closed successfully. Inform the user that he/she
		//has signed out
		printWriter.println("<table bgcolor='white' border='5' style=\"margin: 0px auto;font-size:1.5em;\">");
		
		printWriter.println("<tr><td>Signed Out</td></tr><br><br>");
		printWriter.println("</table><br><br>");
		
		//Allow user the option to sign in again
		request.getRequestDispatcher("index.html").include(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
