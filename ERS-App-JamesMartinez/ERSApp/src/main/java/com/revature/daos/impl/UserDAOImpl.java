package com.revature.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.daos.UserDAO;
import com.revature.dbutil.PostgresConnection;
import com.revature.models.User;

public class UserDAOImpl implements UserDAO{
	
	Logger log=Logger.getLogger(UserDAOImpl.class);

	@Override
	public int userLogin(String username, String password) {
		int id = 0;
		int check = 0;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql ="select user_id, username, password from ers_schema.ers_users";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next() && check == 0) {
				String realUsername = resultSet.getString("username");
				String realPassword = resultSet.getString("password");
			     if(username.equals(realUsername) && password.equals(realPassword)) {
			    	 check = 1;
			    	 id = resultSet.getInt("user_id");
					}
			}
			} catch (ClassNotFoundException | SQLException e) {
				log.debug(e);
			}
				return id;
	}

	@Override
	public int updateEmployeeById(int id, int choice, String update) {
		int c = 0;
        try (Connection connection = PostgresConnection.getConnection()) {
			String sqlfn = "update ers_schema.ers_users set first_name = ? where user_id= ?";
			String sqlln = "update ers_schema.ers_users set last_name = ? where user_id= ?";
			String sqlun = "update ers_schema.ers_users set username = ? where user_id= ?";
			String sqlpw = "update ers_schema.ers_users set password = ? where user_id= ?";
			String sqlem = "update ers_schema.ers_users set email = ? where user_id= ?";
			String sql =null;
			if(choice == 1) {
				sql = sqlfn;
			}else if(choice ==2) {
				sql = sqlln;
			}else if(choice ==3) {
				sql = sqlun;
			}else if(choice ==4) {
				sql = sqlpw;
			}else if(choice ==5) {
				sql = sqlem;
			}
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(2, id);
			preparedStatement.setString(1, update);
			c = preparedStatement.executeUpdate();
	
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e);	
			c = 1;
		}
		return c;
	}

	@Override
	public List<User> viewAllEmployees() { 
		List<User> userList = new ArrayList<>();
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "select u.user_id, u.username, u.password, u.first_name, u.last_name, u.email, r.user_role\r\n"
					+ "from ers_schema.ers_users u join ers_schema.user_roles r on u.role_id = r.role_id where user_role = 'employee' order by user_id";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				User user = new User();
				user.setUserId(resultSet.getInt("user_id"));
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
				user.setEmail(resultSet.getString("email"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setRole(resultSet.getString("user_role"));
				userList.add(user);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e);
		}
		return userList;
	}

	@Override
	public User getEmployeeById(int id) { 
		User user = null;
		try (Connection connection = PostgresConnection.getConnection()) { 
			String sql = "select u.user_id, u.username, u.password, u.first_name, u.last_name, u.email, r.user_role\r\n"
					+ "from ers_schema.ers_users u join ers_schema.user_roles r on u.role_id = r.role_id where user_id = ? and user_role = 'employee' order by user_id";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				user =new User();
				user.setUserId(id);
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
				user.setEmail(resultSet.getString("email"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setRole(resultSet.getString("user_role"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e);
		}
		return user;
	}

	@Override
	public User getUserById(int id) {
		User user = null;
		try (Connection connection = PostgresConnection.getConnection()) { 
			String sql = "select u.user_id, u.username, u.password, u.first_name, u.last_name, u.email, r.user_role\r\n"
					+ "from ers_schema.ers_users u join ers_schema.user_roles r on u.role_id = r.role_id where user_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				user =new User();
				user.setUserId(id);
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
				user.setEmail(resultSet.getString("email"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setRole(resultSet.getString("user_role"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e);
		}
		return user;
	}

}
