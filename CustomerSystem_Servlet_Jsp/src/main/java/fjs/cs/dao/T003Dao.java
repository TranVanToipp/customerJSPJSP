package fjs.cs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import fjs.cs.common.DbConnection;
import fjs.cs.dto.T002Dto;
import fjs.cs.dto.T003Dto;

public class T003Dao {
	public int update(T003Dto u, HttpSession session) throws SQLException {
	    int status = 0;
	    Connection con = null;
	    try{  
	        con = new DbConnection().getConnection(); 
	        String query = "UPDATE mstcustomer SET CUSTOMER_NAME = ?, SEX = ?, BIRTHDAY = ?, EMAIL = ?, ADDRESS = ?, DELETE_YMD = NULL, UPDATE_YMD = CURRENT_TIMESTAMP, UPDATE_PSN_CD = (SELECT PSN_CD FROM mstuser WHERE USERID = ?) WHERE CUSTOMER_ID = ?"; // câu truy vấn
	        PreparedStatement ps = con.prepareStatement(query);  
	        ps.setString(1, u.getCUSTOMER_NAME());  
	        ps.setString(2, u.getSEX());  
	        ps.setString(3, u.getBIRTHDAY());  
	        ps.setString(4, u.getEMAIL());  
	        ps.setString(5, u.getADDRESS());  
	        ps.setString(6, (String) session.getAttribute("psnCd")); // giả sử PSN_CD của user đăng nhập là 1
	        ps.setString(7, u.getCUSTOMER_ID());  
	        status = ps.executeUpdate();  
	    } catch(Exception e) {
	        System.out.println(e);
	    } finally {
	        // đóng kết nối sau khi thực hiện xong
	        try {
	            con.close();
	        } catch(Exception e) {
	            System.out.println(e);
	        }
	    }
	    return status;
	  
	}
	
	public static T003Dto getCustomerById(int customerId) {
	    T003Dto dto = new T003Dto();
	    Connection con = null;
	    try{  
	        con = new DbConnection().getConnection();  
	        String query = "SELECT * FROM mstcustomer WHERE CUSTOMER_ID = ?"; // câu truy vấn
	        PreparedStatement ps = con.prepareStatement(query);  
	        ps.setInt(1, customerId);  
	        ResultSet rs = ps.executeQuery();  
	        if(rs.next()) {
	            dto.setCUSTOMER_ID(rs.getInt("CUSTOMER_ID"));
	            dto.setCUSTOMER_NAME(rs.getString("CUSTOMER_NAME"));
	            dto.setSEX(rs.getString("SEX"));
	            dto.setBIRTHDAY(rs.getString("BIRTHDAY"));
	            dto.setEMAIL(rs.getString("EMAIL"));
	            dto.setADDRESS(rs.getString("ADDRESS"));
	            dto.setCREATE_YMD(rs.getString("CREATE_YMD"));
	            dto.setCREATE_PSN_CD(rs.getString("CREATE_PSN_CD"));
	            dto.setUPDATE_YMD(rs.getString("UPDATE_YMD"));
	            dto.setUPDATE_PSN_CD(rs.getString("UPDATE_PSN_CD"));
	            dto.setDELETE_YMD(rs.getString("DELETE_YMD"));
	        }
	    } catch(Exception e) {
	        System.out.println(e);
	    } finally {
	        // đóng kết nối sau khi thực hiện xong
	        try {
	            con.close();
	        } catch(Exception e) {
	            System.out.println(e);
	        }
	    }
	    return dto;
	}


}
