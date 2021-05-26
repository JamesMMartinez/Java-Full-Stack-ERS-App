package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.services.impl.UserServiceImpl;

public class LoggedInServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserService uServ = new UserServiceImpl();
	Logger log=Logger.getLogger(LoggedInServlet.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
    
	HttpSession session = request.getSession(false);
		
    PrintWriter pw = response.getWriter();
    
    int id = (int) session.getAttribute("id");
    log.info(id + "in loggedin servlet");
    
    User user = uServ.getUserById(id); 
    
    if(user != null && session != null) {
	if (user.getRole().equals("manager")) {
		request.getRequestDispatcher("static/manager-home.html").forward(request, response);
		pw.write("<div style='color:red;'> Welcome!"+user.getFirstName()+user.getLastName()+" </div>");
	}else {
		request.getRequestDispatcher("static/employee-home.html").forward(request, response);
		pw.write("<div style='color:red;'> Welcome!" + user.getFirstName() + user.getLastName() + " </div>");
	}
    }else {
    	pw.write("<div style='color:red;'> You are not logged in! </div>");
    }
}
}
