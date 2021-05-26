package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.services.impl.UserServiceImpl;

public class EmployeeUserServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserService uServ = new UserServiceImpl();
	ObjectMapper om = new ObjectMapper();
	Logger log=Logger.getLogger(EmployeeUserServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
	    PrintWriter pw = response.getWriter();
	    
	    int id = (int) session.getAttribute("id");
	    log.info(id + "in EmpUserServlet");
		
	    User user = uServ.getEmployeeById(id);
	    pw.write(om.writeValueAsString(user));
	    
		
	}
	

}
