package com.mapers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mapers.dto.NoticeDTO;

public class NoticeDAO {

	private NoticeDAO() {
	}

	// 싱글턴 패턴으로 인스턴스 생성 제한
	private static NoticeDAO instance = new NoticeDAO();

	public static NoticeDAO getInstance() {
		return instance;
	}

	// DB Connection Pool
	private Connection getConnection() throws Exception {
		Connection conn = null;
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/myoracle");
		conn = ds.getConnection();
		
		return conn;
	}

	// 공지사항 DB INSERT DAO - 김연호
	public int insertNotice(NoticeDTO dto) {
		int result = 0;
		String query = "INSERT INTO NOTICE (noticenum, title, content) VALUES (C##MAPERS.NOTICE_SEQ.NEXTVAL,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContents());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
