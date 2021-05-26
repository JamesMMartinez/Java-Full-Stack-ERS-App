package com.revature.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;
import com.revature.services.impl.ReimbursementServiceImpl;
import com.revature.services.impl.UserServiceImpl;

public class TestDriver {

	public static void main(String[] args) {
		
		UserService uServ = new UserServiceImpl();
		ReimbursementService reimbServ = new ReimbursementServiceImpl();
		Logger log=Logger.getLogger(TestDriver.class);
		
		//User TESTS
		
		String username = "jmike2198";
		String password = "Password1";
		
		int checkId = uServ.userLogin(username, password);
		log.info(checkId);
		
		
		//int updateId = 104;
		//int choice = 5; // 1=firstname 2=lastname 3=username 4=password 5=email
		//String update = "usoppsoge@gmail.com";
		
		// TESTED uServ.updateEmployeeById(updateId, choice, update);
		
		List<User> userList = new ArrayList<>();
		userList = uServ.viewAllEmployees(); //TESTED
		log.info(userList);  
		
		int employeeId = 110;
		User empUser = new User();
		empUser = uServ.getEmployeeById(employeeId); //TESTED
		log.info(empUser);
		
		int userId = 102;
		User user = new User();
		user = uServ.getUserById(userId);
		log.info(user);
		
		//Reimbursement TESTS
		
		Reimbursement reimb = new Reimbursement();
		reimb.setAmount(1000);
		reimb.setDescription("Ship Repairs");
		reimb.setAuthorId(107);
	//	int typeId = 4; // 1 = lodging 2 = travel 3 = food 4 = other
		
		//reimbServ.createNewReimbursement(reimb, typeId); //TESTED

		int reimbId = 1001;
		int statusId = 2; //1 = pending, 2 = accepted, 3 = declined
		int resoId = 102;
		
		reimbServ.updateReimbursementStatus(reimbId, statusId, resoId); //TESTED
		
		List<Reimbursement> idAndStatusListAll = new ArrayList<>();
		List<Reimbursement> idAndStatusListPend = new ArrayList<>();
		List<Reimbursement> idAndStatusListReso = new ArrayList<>();
		
		int empId = 101;
		
		System.out.println("\n");
		idAndStatusListAll = reimbServ.listReimbByIdandStatus(empId, "all"); //TESTED
		log.info(idAndStatusListAll);
		idAndStatusListPend = reimbServ.listReimbByIdandStatus(empId, "pending"); //TESTED
		log.info(idAndStatusListPend);
		idAndStatusListReso = reimbServ.listReimbByIdandStatus(empId, "resolved"); //TESTED
		log.info(idAndStatusListReso);
		System.out.println("\n");
		
		List<Reimbursement> pendingList = new ArrayList<>();
		List<Reimbursement> resoList = new ArrayList<>();
		List<Reimbursement> allList = new ArrayList<>();
		
		System.out.println("\n");
		pendingList = reimbServ.listAll("pending"); //TESTED
		log.info(pendingList);
		resoList = reimbServ.listAll("resolved");
		log.info(resoList);
		allList = reimbServ.listAll("all");
		log.info(allList);
		
		
//        List<Reimbursement> resolvedList = new ArrayList<>();
//		
//		resolvedList = reimbServ.ListAllResolved(); //TESTED
//		System.out.println(resolvedList);
		
	}
	

}
