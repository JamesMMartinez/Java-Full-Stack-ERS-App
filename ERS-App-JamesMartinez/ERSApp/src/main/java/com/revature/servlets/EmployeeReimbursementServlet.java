package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;
import com.revature.services.impl.ReimbursementServiceImpl;

public class EmployeeReimbursementServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ReimbursementService reimbServ = new ReimbursementServiceImpl();
	Logger log=Logger.getLogger(EmployeeReimbursementServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
	    PrintWriter pw = response.getWriter();
	    int userId = (int) session.getAttribute("id");
	    log.info(userId);
	    
	    Float amount = Float.parseFloat(request.getParameter("amount"));
	    log.info(amount);
	    String description = request.getParameter("description");
	    log.info(description);
	    String reimbType = request.getParameter("reimbtype");
	    log.info(reimbType);
	    
	    int typeId = 0;
	    
	    if(reimbType.equals("lodging")) {
	    	typeId = 1;
	    }else if(reimbType.equals("travel")) {
	    	typeId = 2;
	    }else if(reimbType.equals("food")) {
	    	typeId = 3;
	    }else if(reimbType.equals("other")) {
	    	typeId = 4;
	    }else {
	    	response.sendError(404);
	    }
	    System.out.println(typeId);
	    
	    Reimbursement reimb = new Reimbursement();
	    reimb.setAmount(amount);
	    reimb.setAuthorId(userId);
	    reimb.setDescription(description);
	    
	    int check = reimbServ.createNewReimbursement(reimb, typeId);
	    
	    if(check == 0) {
			pw.write("<div style='color:red;'> Unable Resolve Request </div>");
		}else {
			pw.write("<div style='color:green;'> Request Resolved Successfully </div>");
		}
	   
	}
}
