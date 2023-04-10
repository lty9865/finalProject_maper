package com.mapers.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mapers.SignUp.MemberDTO;
import com.mapers.common.DataSourceManager;
//비밀번호 찾기
public class FindPasswordDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// singleton pattern
	private static FindPasswordDAO instance = new FindPasswordDAO();
	private DataSource dataSource;

	private FindPasswordDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}

	public static FindPasswordDAO getInstance() {
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
	   
	   
	   public String FindPassword(MemberDTO mDto) {
		   String password = null;
		   String query = "SELECT PASSWORD FROM ACCOUNT WHERE USERID=? and LICENSEKEY=?";
		   
		   try {
			   conn = dataSource.getConnection();
			   
			   pstmt = conn.prepareStatement(query);
			   pstmt.setString(1, mDto.getUserId());	
			   pstmt.setString(2, mDto.getLicenseKey());
			   rs = pstmt.executeQuery();
			   
			   System.out.println(mDto.getUserId());
			   System.out.println(mDto.getLicenseKey());
			   
			   if(rs.next()) {
				   password = rs.getString("password");
			   }
		   }catch(Exception e) {
			   e.printStackTrace();
		   }finally {
			   try {
				   if(rs != null) rs.close();
				   if(pstmt != null) pstmt.close();
				   
			   }catch(Exception e) {
				   e.printStackTrace();
			   }
		   }
		   return password;
	   }
	   }