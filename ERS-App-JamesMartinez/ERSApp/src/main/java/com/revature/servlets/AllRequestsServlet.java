package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

public class AllRequestsServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserService uServ = new UserServiceImpl();
	ReimbursementService reimbServ = new ReimbursementServiceImpl();
	ObjectMapper om = new ObjectMapper();
	Logger log=Logger.getLogger(AllRequestsServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession(false);
		int userId = (int)session.getAttribute("id");
		log.info(userId +"inALLREQ");
		
		User user = uServ.getUserById(userId);
		
		String status = request.getParameter("status");
		if (user.getRole().equals("manager")) {

			List<Reimbursement> reqList = new ArrayList<>();
			reqList = reimbServ.listAll(status);
			pw.write(om.writeValueAsString(reqList));

		} else {

			List<Reimbursement> reqList = new ArrayList<>();
			reqList = reimbServ.listReimbByIdandStatus(userId, status);
			pw.write(om.writeValueAsString(reqList));
		}
	}

}
