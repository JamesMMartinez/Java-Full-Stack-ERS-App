package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.services.UserService;
import com.revature.services.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	UserService uServ = new UserServiceImpl();
	Logger log=Logger.getLogger(LoginServlet.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("pass");
        log.info(username);
        log.info(password);
 
        
        int userId = uServ.userLogin(username, password);
        log.info(userId + "In login servlet");
        
        if(userId == 0)
        {
             request.getRequestDispatcher("static/login-wrong.html").include(request, response);
        }
        else if(userId >= 100)
        {
        	HttpSession session = request.getSession();
        	session.setAttribute("id", userId);
			request.getRequestDispatcher("loggedin").forward(request, response);;
        }
    } 
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null) {
		session.invalidate();
	    }
	}
	
}
