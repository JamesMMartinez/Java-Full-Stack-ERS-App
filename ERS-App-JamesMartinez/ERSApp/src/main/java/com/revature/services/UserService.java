package com.revature.services;

import java.util.List;

import com.revature.models.User;

public interface UserService {
	
	public int userLogin(String username, String password);
	
	public int updateEmployeeById(int id, int choice, String update);
	
	public List<User> viewAllEmployees();
	
	public User getEmployeeById(int id);
	
	public User getUserById(int id);
	
}
