package com.mapers.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mapers.SignUp.MemberVO;
//비밀번호 찾기
public class FindPasswordDAO{

	   private Connection conn = null;
	   private PreparedStatement pstmt = null;
	   private ResultSet rs = null;

	  public FindPasswordDAO() {
	   }

	   private static FindPasswordDAO instance = new FindPasswordDAO();

	   public static FindPasswordDAO getInstance() {
	      return instance;
	   }

	   public Connection getConnection() throws Exception {
	      Context initContext = new InitialContext();
	      Context envContext = (Context) initContext.lookup("java:/comp/env");
	      DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
	      conn = ds.getConnection();
	      return conn;
	   }
	   
	   // 자원 반납
	   public void close() {
	      try {
	         if (rs != null)
	            rs.close();
	         if (pstmt != null) 
	            pstmt.close();
	         if (conn != null)
	            conn.close();
	      
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	   
	   public String FindPassword(MemberVO mVo) {
		   String password = null;
		   String query = "SELECT PASSWORD FROM ACCOUNT WHERE USERID=? and LICENSEKEY=?";
		   
		   try {
			   conn = getConnection();
			   
			   pstmt = conn.prepareStatement(query);
			   pstmt.setString(1, mVo.getUserid());	
			   pstmt.setString(2, mVo.getLicenseKey());
			   rs = pstmt.executeQuery();
			   
			   System.out.println(mVo.getUserid());
			   System.out.println(mVo.getLicenseKey());
			   
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