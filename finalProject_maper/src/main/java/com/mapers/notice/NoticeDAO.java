package com.mapers.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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

	// 공지사항 글 개수 세기 - 김연호
	public int countNotice(Map<String, Object>map) {
		int totalCount = 0;
		
		String query = "SELECT COUNT(*) FROM NOTICE";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		if(map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%'";
		}
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			rs.next();
			totalCount = rs.getInt(1);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("공지사항 개수 세기 중 예외 발생");
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return totalCount;
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
			pstmt.setString(2, dto.getContent());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("공지사항 insert 중 예외 발생");
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
	
	// 공지사항 DB SELECT DAO - 김연호
	public List<NoticeDTO> noticeList(Map<String, Object> map){
		List<NoticeDTO> nl = new Vector<NoticeDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String query = " "
				+ "SELECT * FROM ( "
				+ " 	SELECT Tb.*, ROWNUM rNum FROM ( "
				+ " 		SELECT * FROM notice ";
		// 검색 조건
		if(map.get("searchWord")!=null) {
			query += " WHERE " + map.get("searchField")
			+ " LIKE '%" + map.get("searchWord") + "%'";
		}
		
		query += " 			ORDER BY noticenum DESC "
				+ " 	) Tb "
				+ " ) "
				+ " WHERE rNum BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, map.get("start").toString());
			pstmt.setString(2, map.get("end").toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				NoticeDTO dto = new NoticeDTO();
				
				dto.setIdx(rs.getInt("noticenum"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setVisitCount(rs.getInt("visitCount"));
				dto.setPostdate(rs.getString("postdate"));
				
				nl.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("공지사항 게시물 조회 중 예외 발생");
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return nl;
	}
	
}
