package com.db.java.web;

import java.io.IOException;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignIn
 */
@WebServlet("/SignIn")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//Obtain the username and password from the HttpServletRequest object using
				//the variables user and pass submitted from the HTML form 
				String user = request.getParameter("user");
				String pass = request.getParameter("pass");

				//Ensure that this page is not cached by the Web browser
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
				response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
				response.setDateHeader("Expires", 0);

				//Set the HttpServletResponse object to communicate text or HTML
				response.setContentType("text/html");

				//Printwriter is used to write information to the Web browser
				PrintWriter printWriter  = response.getWriter();
				
				//Execute the code that may cause errors
				try{
					//Connect to the Company database using the username and password obtained
					//from the Web based form
					new DBConnector("comapny1",user,pass);	

					//If connection is successful, load the file menu.html and pass the request
					//and response objects
					request.getRequestDispatcher("menu.html").include(request, response); 
				}
				//If any of the above statements in the try block causes an error stop
				//execution and begin executing the catch block 
				catch(Exception e){
					//Username and password were incorrect. Inform the user and request re-login
					printWriter.println("<h1>Error:"+e.getMessage()+"</h1>");
					printWriter.println("<h1>Invalid Login. Try again</h1>");
					
					//Reload the file index.html
					request.getRequestDispatcher("index.html").include(request, response);
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
