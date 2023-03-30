package com.mapers.logIn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mapers.signUp.MemberVO;

public class FindIdDAO{

	   private Connection conn = null;
	   private PreparedStatement pstmt = null;
	   private ResultSet rs = null;

	  public FindIdDAO() {
	   }

	   private static FindIdDAO instance = new FindIdDAO();

	   public static FindIdDAO getInstance() {
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
	   
	   public String FindId(MemberVO mVo) {
		   String userid = null;
		   String query = "SELECT userid FROM ACCOUNT WHERE LICENSEKEY = ?";
		   
		   try {
			   conn = getConnection();
			   
			   pstmt = conn.prepareStatement(query);
			   pstmt.setString(1, mVo.getLicenseKey());
			   
			   rs = pstmt.executeQuery();
			   
			   if(rs.next()) {
				   userid = rs.getString("userid");
				   System.out.println(userid);
			   }
		   }catch(Exception e) {
			   System.out.println(userid);
			   e.printStackTrace();
		   }finally {
			   try {
				   if(rs != null) rs.close();
				   if(pstmt != null) pstmt.close();
			   }catch(Exception e) {
				   e.printStackTrace();
			   }
		   }
		   return userid;
	   }
	   }
