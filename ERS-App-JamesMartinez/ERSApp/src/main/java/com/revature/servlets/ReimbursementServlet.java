package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;
import com.revature.services.impl.ReimbursementServiceImpl;
import com.revature.services.impl.UserServiceImpl;

public class ReimbursementServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ReimbursementService reimbServ = new ReimbursementServiceImpl();
	UserService uServ = new UserServiceImpl();
	ObjectMapper om = new ObjectMapper();
	Logger log=Logger.getLogger(ReimbursementServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
	    
	    int id = (int) session.getAttribute("id");
	    log.info(id);
		
	    User user = uServ.getUserById(id);
	    
	    if(user.getRole().equals("manager")) {
	    	request.getRequestDispatcher("manager-reimbursement");
	    }else {
	    	request.getRequestDispatcher("employee-reimbursement");
	    }
	    
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
