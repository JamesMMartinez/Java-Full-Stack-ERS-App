package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.services.impl.UserServiceImpl;

public class UserServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserService uServ = new UserServiceImpl();
	Logger log=Logger.getLogger(UserServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession(false);
	    
	    int id = (int) session.getAttribute("id");
	    log.info(id + "in UserServlet");
	    
	    User user = uServ.getUserById(id);
	    
	    if(user.getRole().equals("manager")) {
	    	request.getRequestDispatcher("manager-user");
	    }else if(user.getRole().equals("employee")) {
	    	request.getRequestDispatcher("employee-user");
	    }
	
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null) {
		session.invalidate();
	    }
	}
}
