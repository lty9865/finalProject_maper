package com.mapers.myPage.Profile.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import com.mapers.common.DataSourceManager;

public class ProfileDAO {
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;

	// singleton pattern
	private static ProfileDAO instance = new ProfileDAO();
	private DataSource dataSource;
	
	private ProfileDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}
	
	public static ProfileDAO getInstance() {
		return instance;
	}
	
	// Connection을 dbcp에 반납
	public void closeAll() {
		
		try {
			
			if(rs != null) rs.close();
			if(psmt !=null) psmt.close();
			if(conn !=null) conn.close();
			
			System.out.println("DB Connection Pool Resource Dismissed!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 마이 페이지, 계정정보 확인 처리 - 박강필
	public int checkAccount(String userid, String password) {
		// 경우의 수 3가지= 1 인증 성공/ 0 비밀번호 틀림/ -1 아이디 틀림(없음)
		int answer = -1;
		
		String sql = "select userid from account where password=?";
		
		try {
			
			if (conn != null) {
				conn.close();
			}
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, password);
			
			rs = psmt.executeQuery();
			if (rs.next()) {
				String db_id = rs.getString("userid");
				if (userid.contentEquals(db_id)) {
					answer = 1;
				} else {
					answer = 0;
				}
			} else {
				answer = -1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		
		} 
		
		return answer;
	}
	
	// 아이디 중복 확인 - 박강필
	public int confirmId(String userId) {
		// 경우의 수 2가지: 1 중복 있음 / -1 중복 없음(회원가입 가능)
		int duplicate = 1;
		
		String sql = "select userid from account where userid=?";
		
		try {

			if (conn != null) {
				conn.close();
			}
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			
			rs = psmt.executeQuery();
			if (rs.next()) {
				String userid = rs.getString("userid");
				if (userid.contentEquals(userId)) {
					duplicate = 1;
				}
			} else {
				duplicate = -1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} 
		
		return duplicate;
	}
	
	// 마이 페이지로 이동 시, 디폴트 페이지로 보여주는 내 정보 탭 정보 가져오기 - 박강필
	public ProfileDTO getProfile(String userId) {
		ProfileDTO dto = null;
		
		String sql = "select * from account where userid=? ";
		
		try {

			if (conn != null) {
				conn.close();
			}
			
			conn = dataSource.getConnection();
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			
			rs = psmt.executeQuery();
			if (rs.next()) {
				dto = new ProfileDTO();
				
				dto.setUserId(rs.getString("userid"));
				dto.setPassword(rs.getString("password"));
				dto.setEmail(rs.getString("usermail"));
				dto.setOfile(rs.getString("ofile"));
				dto.setSfile(rs.getString("sfile"));
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("내 프로필 불러오기 Error");
		} 
		
		return dto;
	}
	
	// 회원정보 수정을 위한 정보 가져오기 - 박강필
	public ProfileDTO getProfile(String userId, String password) {
		ProfileDTO dto = null;
		
		String sql = "select * from account where userid=? and password=?";
		
		try {

			if (conn != null) {
				conn.close();
			}
			
			conn = dataSource.getConnection();
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			psmt.setString(2, password);
			
			rs = psmt.executeQuery();
			if (rs.next()) {
				dto = new ProfileDTO();
				
				dto.setUserId(rs.getString("userid"));
				dto.setPassword(rs.getString("password"));
				dto.setEmail(rs.getString("usermail"));
				dto.setOfile(rs.getString("ofile"));
				dto.setSfile(rs.getString("sfile"));
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("내 프로필 불러오기 Error");
		} 
		
		return dto;
	}
	
	// 프로필 이미지 수정 - 박강필
	
	// 실질적인 회원정보 수정 form - 박강필
	public int updateProfile(ProfileDTO dto) {
		// 회원정보 수정: 성공 1 / 실패 0
		int answer = -1;
		
		String sql1 = "select userid from account where password=?";
		
		try {

			if (conn != null) {
				conn.close();
			}
			
			psmt = conn.prepareStatement(sql1);
			psmt.setString(1, dto.getPassword()); // 입력한 비밀번호 가져오기
			
			rs = psmt.executeQuery();
			if (rs.next()) {
				String userId = rs.getString("userid");
				
				if (dto.getUserId().equals(userId)) { // session에 저장된 아이디 가져와 비교
					String sql2 = "update account set usermail=?, birth=? where userid=?";
					
					psmt = conn.prepareStatement(sql2);
					psmt.setString(1, dto.getEmail());
					psmt.setString(3, dto.getUserId());
					
					// update 쿼리문 실행
					psmt.executeUpdate();
					
					answer = 1;
				} else {
					answer = 0;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} 
		
		return answer;
	}
	
	// 회원 탈퇴 처리 - 박강필
	public int deleteMember(String userId, String password) {
		// 회원탈퇴: 성공 1/ 실패 0
		int answer = -1;
		
		String sql1 = "delete from account where userid=? and password=?";
		
		try {

			if (conn != null) {
				conn.close();
			}
			
			conn = dataSource.getConnection();
			
			psmt = conn.prepareStatement(sql1);
			psmt.setString(1, userId);
			psmt.setString(2, password);
			
			answer = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		
		} 
		
		return answer;
	}
	
	// 회원 리스트 조회 - 박강필
	public ResultSet registerList() {

		String sql = "select * from account";
		
		try {

			if (conn != null) {
				conn.close();
			}
			
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} 
		
		return rs;
	}
}
