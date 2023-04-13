package com.mapers.SignUp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mapers.SignUp.SignUpController;
import com.mapers.common.DataSourceManager;

public class MemberDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// singleton pattern
	private static MemberDAO instance = new MemberDAO();
	private DataSource dataSource;

	private MemberDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}

	public static MemberDAO getInstance() {
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

	// 회원가입
	public int join(MemberDTO mDto) {

		int result = -1;

		String query = "INSERT INTO ACCOUNT (userId, password, usermail, licensekey) VALUES (?, ?, ?, ?)";

		try {

			conn = dataSource.getConnection();

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mDto.getUserId());
			pstmt.setString(2, mDto.getPassword());
			pstmt.setString(3, mDto.getEmail());
			pstmt.setString(4, mDto.getLicenseKey());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("회원가입 정보 db저장 중 오류");
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
		return result; // db 오류 일 때 -1을 return 해준다.
	}

	// 아이디있는지 체크
	public int confirmID(String userId) {
		int resultId = -1;
		String sql = "select userId from ACCOUNT where userId=?";

		try {
			conn = dataSource.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				resultId = 1; // 아이디 사용 가능(db에 없는 아이디)
			} else {
				resultId = -1; // 아이디 사용 불가능(db에 저장되어있는 아이디)
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
		return resultId;
	}

	public int userCheck(String userId, String password) {
		int result = -1;
		String sql = "select password from ACCOUNT where userId=?";

		try {
			conn = dataSource.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getString("password") != null && rs.getString("password").equals(password)) {
					result = 1;

					System.out.println("로그인 성공");
				} else {
					result = 0;

					System.out.println("아이디 및 비밀번호가 틀립니다.");
				}
			} else {
				result = -1;

				System.out.println("없는 아이디입니다.");
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

	public MemberDTO getMember(String userId) {
		MemberDTO mDto = null;
		String sql = "select * from ACCOUNT where userId=?";

		try {
			conn = dataSource.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				mDto = new MemberDTO();
				mDto.setUserId(rs.getString("userId"));
				mDto.setPassword(rs.getString("password"));
				mDto.setEmail(rs.getString("email"));
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
		return mDto;
	}
}