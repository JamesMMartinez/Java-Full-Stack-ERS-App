package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;
import com.revature.services.impl.ReimbursementServiceImpl;
import com.revature.services.impl.UserServiceImpl;

public class ManagerReimbursementServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserService uServ = new UserServiceImpl();
	ReimbursementService reimbServ = new ReimbursementServiceImpl();
	ObjectMapper om = new ObjectMapper();
	Logger log=Logger.getLogger(ManagerReimbursementServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
	    PrintWriter pw = response.getWriter();
		int empId = Integer.parseInt(request.getParameter("userId"));
		log.info(empId);
	
		User user = uServ.getEmployeeById(empId);
		String status = "pending";

		if (user != null) {
			List<Reimbursement> reimbList = reimbServ.listReimbByIdandStatus(empId, status);
			pw.write(om.writeValueAsString(reimbList));
		} else {
			response.sendError(404, "Request not found");
		}
		}catch(NumberFormatException e) {
			System.out.println(e);
			doPut(request, response);
		}

	}
  
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession(false);
	    PrintWriter pw = response.getWriter();
	    
	    int resoId = (int)session.getAttribute("id");
	    log.info(resoId);
		int reimbId = Integer.parseInt(request.getParameter("reimbursementId"));
		log.info(reimbId);
		String status = request.getParameter("reimbStatus");
		log.info(status);
		int statusId = 0;
		
		if(status.equals("approved")){
			statusId = 2;
		}else if(status.equals("declined")) {
			statusId = 3;
		}
		log.info(statusId);
		
		int check = reimbServ.updateReimbursementStatus(reimbId, statusId, resoId);
		
		if(check == 0) {
			pw.write("<div style='color:red;'> Unable Resolve Request </div>");
		}else {
			pw.write("<div style='color:green;'> Request Resolved Successfully </div>");
		}
	}
	
}
