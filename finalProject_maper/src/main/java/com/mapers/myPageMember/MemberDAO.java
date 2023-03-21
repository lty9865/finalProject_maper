package com.mapers.myPageMember;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mapers.common.ConnectionPool;


public class MemberDAO {
	private ConnectionPool cp;
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	// 커넥션 풀 사용법
	public MemberDAO() throws SQLException {
		// oracle url, oracle userId, oracle userPassword, 초기 커넥션 수, 최대 커넥션 수
		cp = ConnectionPool.getInstance("jdbc:oracle:thin:@localhost:1521:xe", "c##mapers", "mapers1234", 5, 10);
		
		conn = cp.getConnection();
		
		psmt = null;
		rs = null;
	}
	
	// 회원가입 처리 - 박강필
	public int addMember(MemberDTO dto) {
		int result = -1;
		
		String sql = "insert into account (userid, password, useremail, birth) values (?, ?, ?, ?)";

		try {

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getUserId());
			psmt.setString(2, dto.getPassword());
			psmt.setString(3, dto.getEmail());
			psmt.setString(4, dto.getBirth());
			
			result = psmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}
	
	// 마이 페이지, 계정정보 확인 처리 - 박강필
	public int checkAccount(String userid, String password) {
		// 경우의 수 3가지= 1 인증 성공/ 0 비밀번호 틀림/ -1 아이디 틀림(없음)
		int answer = -1;
		
		String sql = "select id from account where password=?";
		
		try {
			
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
		
		} finally {
			close();
		}
		
		return answer;
	}
	
	// 아이디 중복 확인 - 박강필
	public int confirmId(String id) {
		// 경우의 수 2가지: 1 중복 있음 / -1 중복 없음(회원가입 가능)
		int duplicate = 1;
		
		String sql = "select id from account where id=?";
		
		try {
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			
			rs = psmt.executeQuery();
			if (rs.next()) {
				String userid = rs.getString("userid");
				if (id.contentEquals(userid)) {
					duplicate = 1;
				}
			} else {
				duplicate = -1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			close();
		}
		
		return duplicate;
	}
	
	// 회원정보 수정을 위한 정보 가져오기 - 박강필
	public MemberDTO getMember(String id, String password) {
		MemberDTO dto = null;
		String sql = "select * from account where id=? and password=?";
		
		try {
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, password);
			
			rs = psmt.executeQuery();
			if (rs.next()) {
				dto = new MemberDTO();
				
				dto.setUserId(rs.getString("userid"));
				dto.setPassword(rs.getString("password"));
				dto.setEmail(rs.getString("usermail"));
				dto.setBirth(rs.getString("birth"));
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			close();
		}
		
		return dto;
	}
	
	// 실질적인 회원정보 수정 form - 박강필
	public int updateMember(MemberDTO dto) {
		// 회원정보 수정: 성공 1 / 실패 0
		int answer = -1;
		
		String sql1 = "select id from account where password=?";
		
		try {
			
			psmt = conn.prepareStatement(sql1);
			psmt.setString(1, dto.getPassword()); // 입력한 비밀번호 가져오기
			
			rs = psmt.executeQuery();
			if (rs.next()) {
				String id = rs.getString("userid");
				
				if (dto.getUserId().equals(id)) { // session에 저장된 아이디 가져와 비교
					String sql2 = "update account set usermail=?, birth=? where id=?";
					
					psmt = conn.prepareStatement(sql2);
					psmt.setString(1, dto.getEmail());
					psmt.setString(2, dto.getBirth());
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
			
		} finally {
			close();
		}
		
		return answer;
	}
	
	// 회원 탈퇴 처리 - 박강필
	public int deleteMember(String id, String password) {
		// 회원탈퇴: 성공 1/ 실패 0
		int answer = -1;
		
		String sql1 = "select id from account where password=?";
		
		try {
			
			psmt = conn.prepareStatement(sql1);
			psmt.setString(1, password);
			
			rs = psmt.executeQuery();
			if (rs.next()) {
				String existedId = rs.getString("userid");
				
				if (existedId.equals(id)) {
					String sql2 = "delete from account where id=?";
					
					psmt = conn.prepareStatement(sql2);
					psmt.setString(1, id);
					psmt.executeUpdate();
					
					answer = 1;
				} else {
					answer = 0;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			close();
		}
		
		return answer;
	}
	
	// 회원 리스트 조회 - 박강필
	public ResultSet registerList() {

		String sql = "select * from account";
		
		try {
			
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			close();
		}
		
		return rs;
	}
	
	// 마이 페이지, 문의사항 리스트 조회 - 박강필
	public ResultSet requestList() {
		
		String sql = "select * from request";
		
		try {
			
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			close();
		}
		
		return rs;
	}
	
	// 커넥션 풀 반환 및 종료
	private void close() {
		
		try {
			
			if(rs != null) rs.close();
			if(psmt !=null) psmt.close();
			if(conn !=null) cp.releaseConnection(conn);
			
			cp.closeAll();
			
			System.out.println("DB Connection Pool Resource Dismissed!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
