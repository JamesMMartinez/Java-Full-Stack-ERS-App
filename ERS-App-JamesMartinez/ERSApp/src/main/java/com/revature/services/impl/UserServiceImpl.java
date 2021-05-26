package com.revature.services.impl;

import java.util.List;

import com.revature.daos.UserDAO;
import com.revature.daos.impl.UserDAOImpl;
import com.revature.models.User;
import com.revature.services.UserService;

public class UserServiceImpl implements UserService{
 
    private UserDAO uDAO = new UserDAOImpl();
	
    @Override
	public int userLogin(String username, String password) {
		// TODO Auto-generated method stub
		return uDAO.userLogin(username, password);
	}
    
    @Override
	public int updateEmployeeById(int id, int choice, String update) {
		// TODO Auto-generated method stub
		return uDAO.updateEmployeeById(id, choice, update);
	}

	@Override
	public List<User> viewAllEmployees() {
		// TODO Auto-generated method stub
		return uDAO.viewAllEmployees();
	}

	@Override
	public User getEmployeeById(int id) {
		// TODO Auto-generated method stub
		return uDAO.getEmployeeById(id);
	}

	@Override
	public User getUserById(int id) {
		
		return uDAO.getUserById(id);
	}


}
