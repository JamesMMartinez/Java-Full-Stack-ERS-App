package com.revature.services.impl;

import java.util.List;

import com.revature.daos.ReimbursementDAO;
import com.revature.daos.impl.ReimbursementDAOImpl;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;

public class ReimbursementServiceImpl implements ReimbursementService{

	ReimbursementDAO reimbDAO = new ReimbursementDAOImpl();
	
	@Override
	public int createNewReimbursement(Reimbursement reimb, int typeId) {
		// TODO Auto-generated method stub
		return reimbDAO.createNewReimbursement(reimb, typeId);
	}

	@Override
	public int updateReimbursementStatus(int reimbId, int statusId, int resoId) {
		// TODO Auto-generated method stub
		return reimbDAO.updateReimbursementStatus(reimbId, statusId, resoId);
	}

	@Override
	public List<Reimbursement> listReimbByIdandStatus(int id, String status) {
		// TODO Auto-generated method stub
		return reimbDAO.listReimbByIdandStatus(id, status);
	}

	@Override
	public List<Reimbursement> listAll(String status) {
		// TODO Auto-generated method stub
		return reimbDAO.listAll(status);
	}

}
