package com.mapers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mapers.dto.MemberDTO;

public class MemberDAO {
	private Connection conn;
	private PreparedStatement psmt;
	private Context initContext;
	private Context envContext;

	private MemberDAO() {
	}

	// 싱글턴 패턴으로 인스턴스 생성 제한
	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {
		return instance;
	}

	// 커넥트 풀 생성
	public Connection getConnection() throws Exception {
		conn = null;
		initContext = new InitialContext();
		envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
		conn = ds.getConnection();
		
		return conn;
	}

	// 백엔드 코드 작성 예시
	public int exampleCode(MemberDTO dto) {
		// 변수 초기화
		int result = -1;
		String sql = "";
		conn = null;
		psmt = null;

		//sql 쿼리 실행
		try {

			result = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		// 커넥트 풀 자원 반납
			try {
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
