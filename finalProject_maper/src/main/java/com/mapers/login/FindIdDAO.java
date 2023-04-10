package com.mapers.login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mapers.SignUp.MemberDAO;
import com.mapers.SignUp.MemberDTO;
import com.mapers.common.DataSourceManager;

public class FindIdDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// singleton pattern
	private static FindIdDAO instance = new FindIdDAO();
	private DataSource dataSource;

	private FindIdDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}

	public static FindIdDAO getInstance() {
		return instance;
	}

	// Connection을 dbcp에 반납
	public void closeAll() {

		try {

			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();

			System.out.println("DB Connection Pool Resource Dismissed!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	   
	   public String FindId(String licenseKey) {
		   String userId = null;
		   String query = "SELECT USERID FROM ACCOUNT WHERE LICENSEKEY = ?";
		   
		   try {
			   conn = dataSource.getConnection();
			   
			   pstmt = conn.prepareStatement(query);
			   pstmt.setString(1, licenseKey);
			   
			   rs = pstmt.executeQuery();
			   
			   if(rs.next()) {
				   userId = rs.getString("userId");
				   System.out.println(userId);
			   }
		   }catch(Exception e) {
			   System.out.println(userId);
			   e.printStackTrace();
		   }finally {
			   try {
				   if(rs != null) rs.close();
				   if(pstmt != null) pstmt.close();
			   }catch(Exception e) {
				   e.printStackTrace();
			   }
		   }
		   return userId;
	   }
	   }
