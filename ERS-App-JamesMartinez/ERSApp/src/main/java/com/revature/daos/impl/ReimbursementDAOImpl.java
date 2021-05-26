package com.revature.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.daos.ReimbursementDAO;
import com.revature.dbutil.PostgresConnection;
import com.revature.models.Reimbursement;

public class ReimbursementDAOImpl implements ReimbursementDAO{

	Logger log=Logger.getLogger(ReimbursementDAOImpl.class);
	
	@Override
	public int createNewReimbursement(Reimbursement reimb, int typeId) {
		int c = 0;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "insert into ers_schema.reimbursement(reimb_amount, reimb_desc, reimb_auth_id, reimb_type_id) values(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setFloat(1, reimb.getAmount());
			preparedStatement.setString(2, reimb.getDescription());
			preparedStatement.setInt(3, reimb.getAuthorId());
			preparedStatement.setInt(4, typeId);
			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e);
			c = 1;
		}
		return c;
	}

	@Override
	public int updateReimbursementStatus(int reimbId, int statusId, int resoId) { 
		int c = 0;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "update ers_schema.reimbursement set reimb_status_id = ?, reimb_resolved = current_timestamp, reimb_reso_id = ? where reimb_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, statusId);
			preparedStatement.setInt(2, resoId);
			preparedStatement.setInt(3, reimbId);
			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e);
			c = 1;
		}
		return c;
	}

	@Override
	public List<Reimbursement> listReimbByIdandStatus(int id, String status) { //untested
		List<Reimbursement> reimbList = new ArrayList<>();
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = null;
			if (status.equals("all")) {
				sql = "select r.reimb_id, r.reimb_amount, r.reimb_submitted, r.reimb_resolved, r.reimb_desc,\r\n"
						+ "r.reimb_receipt,r.reimb_auth_id, rs.reimb_status, u.first_name, u.last_name, rt.reimb_type\r\n"
						+ "from ers_schema.reimbursement r \r\n"
						+ "full join ers_schema.ers_users u on r.reimb_reso_id = u.user_id \r\n"
						+ "join ers_schema.reimbursement_status rs on r.reimb_status_id = rs.reimb_status_id \r\n"
						+ "join ers_schema.reimbursement_types rt on r.reimb_type_id = rt.reimb_type_id \r\n"
						+ "where r.reimb_auth_id = ? \r\n" + "order by r.reimb_id";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, id);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Reimbursement reimb = new Reimbursement();
					reimb.setReimbursementId(resultSet.getInt("reimb_id"));
					reimb.setAuthorId(resultSet.getInt("reimb_auth_id"));
					reimb.setAmount(resultSet.getFloat("reimb_amount"));
					reimb.setTimeSubmitted(resultSet.getDate("reimb_submitted"));
					reimb.setTimeResolved(resultSet.getDate("reimb_resolved"));
					reimb.setDescription(resultSet.getString("reimb_desc"));
					reimb.setReceipt(resultSet.getBlob("reimb_receipt"));
					reimb.setReimbStatus(resultSet.getString("reimb_status"));
					reimb.setReimbType(resultSet.getString("reimb_type"));
					reimb.setResolverIdAndName("Resolved by: " + resultSet.getString("first_name") + " "
							+ resultSet.getString("last_name"));
					reimbList.add(reimb);
				}
			} else if (status.equals("pending")) {
				sql = "select r.reimb_id, r.reimb_amount, r.reimb_submitted, r.reimb_resolved, r.reimb_desc,\r\n"
						+ "r.reimb_receipt,r.reimb_auth_id, rs.reimb_status, u.first_name, u.last_name, rt.reimb_type\r\n"
						+ "from ers_schema.reimbursement r \r\n"
						+ "full join ers_schema.ers_users u on r.reimb_reso_id = u.user_id \r\n"
						+ "join ers_schema.reimbursement_status rs on r.reimb_status_id = rs.reimb_status_id \r\n"
						+ "join ers_schema.reimbursement_types rt on r.reimb_type_id = rt.reimb_type_id \r\n"
						+ "where r.reimb_auth_id = ? and rs.reimb_status = ? \r\n" + "order by r.reimb_id";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, id);
				preparedStatement.setString(2, status);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Reimbursement reimb = new Reimbursement();
					reimb.setReimbursementId(resultSet.getInt("reimb_id"));
					reimb.setAuthorId(resultSet.getInt("reimb_auth_id"));
					reimb.setAmount(resultSet.getFloat("reimb_amount"));
					reimb.setTimeSubmitted(resultSet.getDate("reimb_submitted"));
					reimb.setTimeResolved(resultSet.getDate("reimb_resolved"));
					reimb.setDescription(resultSet.getString("reimb_desc"));
					reimb.setReceipt(resultSet.getBlob("reimb_receipt"));
					reimb.setReimbStatus(resultSet.getString("reimb_status"));
					reimb.setReimbType(resultSet.getString("reimb_type"));
					reimb.setResolverIdAndName("Resolved by: " + resultSet.getString("first_name") + " "
							+ resultSet.getString("last_name"));
					reimbList.add(reimb);
				}
			} else if (status.equals("resolved")) {
				sql = "select r.reimb_id, r.reimb_amount, r.reimb_submitted, r.reimb_resolved, r.reimb_desc,\r\n"
						+ "r.reimb_receipt,r.reimb_auth_id, rs.reimb_status, u.first_name, u.last_name, rt.reimb_type\r\n"
						+ "from ers_schema.reimbursement r \r\n"
						+ "full join ers_schema.ers_users u on r.reimb_reso_id = u.user_id \r\n"
						+ "join ers_schema.reimbursement_status rs on r.reimb_status_id = rs.reimb_status_id \r\n"
						+ "join ers_schema.reimbursement_types rt on r.reimb_type_id = rt.reimb_type_id \r\n"
						+ "where r.reimb_auth_id = ? and rs.reimb_status = 'approved' or rs.reimb_status = 'declined' \r\n" + "order by r.reimb_id";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, id);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Reimbursement reimb = new Reimbursement();
					reimb.setReimbursementId(resultSet.getInt("reimb_id"));
					reimb.setAuthorId(resultSet.getInt("reimb_auth_id"));
					reimb.setAmount(resultSet.getFloat("reimb_amount"));
					reimb.setTimeSubmitted(resultSet.getDate("reimb_submitted"));
					reimb.setTimeResolved(resultSet.getDate("reimb_resolved"));
					reimb.setDescription(resultSet.getString("reimb_desc"));
					reimb.setReceipt(resultSet.getBlob("reimb_receipt"));
					reimb.setReimbStatus(resultSet.getString("reimb_status"));
					reimb.setReimbType(resultSet.getString("reimb_type"));
					reimb.setResolverIdAndName("Resolved by: " + resultSet.getString("first_name") + " "
							+ resultSet.getString("last_name"));
					reimbList.add(reimb);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e);
		}
		return reimbList;
	}

	@Override
	public List<Reimbursement> listAll(String status) {
		List<Reimbursement> reimbList = new ArrayList<>();
		try (Connection connection = PostgresConnection.getConnection()) { 
			String sql = null;
			if(status.equals("all")) {
			sql = "select r.reimb_id, r.reimb_amount, r.reimb_submitted, r.reimb_resolved, r.reimb_desc,\r\n"
					+ "r.reimb_receipt,r.reimb_auth_id, rs.reimb_status, u.first_name, u.last_name, rt.reimb_type\r\n"
					+ "from ers_schema.reimbursement r \r\n"
					+ "full join ers_schema.ers_users u on r.reimb_reso_id = u.user_id \r\n"
					+ "join ers_schema.reimbursement_status rs on r.reimb_status_id = rs.reimb_status_id \r\n"
					+ "join ers_schema.reimbursement_types rt on r.reimb_type_id = rt.reimb_type_id \r\n"
					+ "order by r.reimb_id";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Reimbursement reimb = new Reimbursement();
				reimb.setReimbursementId(resultSet.getInt("reimb_id"));
				reimb.setAuthorId(resultSet.getInt("reimb_auth_id"));
				reimb.setAmount(resultSet.getFloat("reimb_amount"));
				reimb.setTimeSubmitted(resultSet.getDate("reimb_submitted"));
				reimb.setTimeResolved(resultSet.getDate("reimb_resolved"));
				reimb.setDescription(resultSet.getString("reimb_desc"));
				reimb.setReceipt(resultSet.getBlob("reimb_receipt"));
				reimb.setReimbStatus(resultSet.getString("reimb_status"));
				reimb.setReimbType(resultSet.getString("reimb_type"));
				reimb.setResolverIdAndName(resultSet.getString("first_name") + " " +resultSet.getString("last_name"));
				reimbList.add(reimb);
			}
			        
			}else if(status.equals("pending")){
				sql = "select r.reimb_id, r.reimb_amount, r.reimb_submitted, r.reimb_resolved, r.reimb_desc,\r\n"
						+ "r.reimb_receipt,r.reimb_auth_id, rs.reimb_status, u.first_name, u.last_name, rt.reimb_type\r\n"
						+ "from ers_schema.reimbursement r \r\n"
						+ "full join ers_schema.ers_users u on r.reimb_reso_id = u.user_id \r\n"
						+ "join ers_schema.reimbursement_status rs on r.reimb_status_id = rs.reimb_status_id \r\n"
						+ "join ers_schema.reimbursement_types rt on r.reimb_type_id = rt.reimb_type_id \r\n"
						+ "where rs.reimb_status = 'pending' \r\n"
						+ "order by r.reimb_id";
				
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet=preparedStatement.executeQuery();
				while(resultSet.next()) {
					Reimbursement reimb = new Reimbursement();
					reimb.setReimbursementId(resultSet.getInt("reimb_id"));
					reimb.setAuthorId(resultSet.getInt("reimb_auth_id"));
					reimb.setAmount(resultSet.getFloat("reimb_amount"));
					reimb.setTimeSubmitted(resultSet.getDate("reimb_submitted"));
					reimb.setTimeResolved(resultSet.getDate("reimb_resolved"));
					reimb.setDescription(resultSet.getString("reimb_desc"));
					reimb.setReceipt(resultSet.getBlob("reimb_receipt"));
					reimb.setReimbStatus(resultSet.getString("reimb_status"));
					reimb.setReimbType(resultSet.getString("reimb_type"));
					reimb.setResolverIdAndName(resultSet.getString("first_name") + " " +resultSet.getString("last_name"));
					reimbList.add(reimb);
				}
			}else if(status.equals("resolved")){
				sql = "select r.reimb_id, r.reimb_amount, r.reimb_submitted, r.reimb_resolved, r.reimb_desc,\r\n"
						+ "r.reimb_receipt,r.reimb_auth_id, rs.reimb_status, u.first_name, u.last_name, rt.reimb_type\r\n"
						+ "from ers_schema.reimbursement r \r\n"
						+ "full join ers_schema.ers_users u on r.reimb_reso_id = u.user_id \r\n"
						+ "join ers_schema.reimbursement_status rs on r.reimb_status_id = rs.reimb_status_id \r\n"
						+ "join ers_schema.reimbursement_types rt on r.reimb_type_id = rt.reimb_type_id \r\n"
						+ "where rs.reimb_status = 'approved' or rs.reimb_status = 'declined' \r\n"
						+ "order by r.reimb_id";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet=preparedStatement.executeQuery();
				while(resultSet.next()) {
					Reimbursement reimb = new Reimbursement();
					reimb.setReimbursementId(resultSet.getInt("reimb_id"));
					reimb.setAuthorId(resultSet.getInt("reimb_auth_id"));
					reimb.setAmount(resultSet.getFloat("reimb_amount"));
					reimb.setTimeSubmitted(resultSet.getDate("reimb_submitted"));
					reimb.setTimeResolved(resultSet.getDate("reimb_resolved"));
					reimb.setDescription(resultSet.getString("reimb_desc"));
					reimb.setReceipt(resultSet.getBlob("reimb_receipt"));
					reimb.setReimbStatus(resultSet.getString("reimb_status"));
					reimb.setReimbType(resultSet.getString("reimb_type"));
					reimb.setResolverIdAndName(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
					reimbList.add(reimb);
			}
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.debug(e);
		}
		return reimbList;
	}	

}
