package com.mapers.signUp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

	public class MemberDAO {

		   private Connection conn = null;
		   private PreparedStatement pstmt = null;
		   private ResultSet rs = null;

		  public MemberDAO() {
		   }

		   private static MemberDAO instance = new MemberDAO();

		   public static MemberDAO getInstance() {
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
		   
		   // 회원가입
		   public int join(MemberVO mVo) { 
			   
			   int result = -1;
			   
			   String query = "INSERT INTO ACCOUNT (userid, password, usermail, birth, licensekey) VALUES (?, ?, ?, ?, ?)";
			   
			   try {
				   
				   conn = getConnection();
				   
				   pstmt = conn.prepareStatement(query);
				   pstmt.setString(1, mVo.getUserid());
				   pstmt.setString(2, mVo.getPassword());
				   pstmt.setString(3, mVo.getEmail());
				   pstmt.setString(4, mVo.getBirth());
				   pstmt.setString(5, mVo.getLicenseKey());
				   
				   result = pstmt.executeUpdate();

				   
			   }catch(Exception e) {
				   System.out.println(mVo.getUserid());
				   System.out.println(mVo.getConfirmPassword());
				   System.out.println(mVo.getEmail());
				   System.out.println(mVo.getEmail());
				   System.out.println(mVo.getBirth());
				   System.out.println(mVo.getLicenseKey());

				   System.out.println("회원가입 정보 db저장 중 오류");
			   }
			   return result;	//db 오류 일 때 -1을 return 해준다.
		   }
		   
		   //아이디있는지 체크
		   public int confirmID(String userid) {
				int result = -1;
				String sql = "select userid from ACCOUNT where userid=?";

				try {
					conn = getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, userid);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						result = 1;
					} else {
						result = -1;
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (rs != null)
							rs.close();
						if (pstmt != null)
							pstmt.close();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return result;
			}
		   
		   public int userCheck(String userid, String password) {
				int result = -1;
				String sql = "select password from ACCOUNT where userid=?";
			

				try {
					conn = getConnection();
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, userid);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						if (rs.getString("password") != null && rs.getString("password").equals(password)) {
							result = 1;
						} else {
							result = 0;
						}
					} else {
						result = -1;
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (rs != null)
							rs.close();
						if (pstmt != null)
							pstmt.close();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				return result;
			}
		   
		   public MemberVO getMember(String userid) {
				MemberVO mVo = null;
				String sql = "select * from ACCOUNT where userid=?";

				try {
					conn = getConnection();
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, userid);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						mVo = new MemberVO();
						mVo.setUserid(rs.getString("userid"));
						mVo.setPassword(rs.getString("password"));
						mVo.setEmail(rs.getString("email"));
						mVo.setBirth(rs.getString("birth"));
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (rs != null)
							rs.close();
						if (pstmt != null)
							pstmt.close();
				
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return mVo;
			}
	}