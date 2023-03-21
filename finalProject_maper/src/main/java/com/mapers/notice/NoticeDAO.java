package com.mapers.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.mapers.common.ConnectionPool;

public class NoticeDAO {
	private ConnectionPool cp;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// 커넥션 풀 사용법
	public NoticeDAO() throws SQLException {
		// oracle url, oracle userId, oracle userPassword, 초기 커넥션 수, 최대 커넥션 수
		cp = ConnectionPool.getInstance("jdbc:oracle:thin:@localhost:1521:xe", "c##mapers", "mapers1234", 5, 10);
		
		conn = cp.getConnection();
		
		pstmt = null;
		rs = null;
	}

	// 공지사항 글 개수 세기 - 김연호
	public int countNotice(Map<String, Object>map) {
		int totalCount = 0;
		
		String query = "SELECT COUNT(*) FROM NOTICE";
		if(map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%'";
		}
		
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			rs.next();
			totalCount = rs.getInt(1);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("공지사항 개수 세기 중 예외 발생");
//		}finally {
//			try {
//				if(rs != null)
//					rs.close();
//				if(pstmt != null)
//					pstmt.close();
//				if(conn != null)
//					cp.releaseConnection(conn);
//					cp.closeAll();
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
		}
		return totalCount;
	}
	
	// 공지사항 DB INSERT DAO - 김연호
	public int insertNotice(NoticeDTO dto) {
		int result = 0;
		String query = "INSERT INTO NOTICE (noticenum, title, content) VALUES (C##MAPERS.NOTICE_SEQ.NEXTVAL,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("공지사항 insert 중 예외 발생");
//		}finally {
//			try {
//				if(pstmt != null)
//					pstmt.close();
//				if(conn != null)
//					cp.releaseConnection(conn);
//					cp.closeAll();
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
		}
		return result;
	}
	
	// 공지사항 DB SELECT DAO - 김연호
	public List<NoticeDTO> noticeList(Map<String, Object> map){
		List<NoticeDTO> nl = new Vector<NoticeDTO>();
		
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
//		}finally {
//			try {
//				if(rs != null)
//					rs.close();
//				if(pstmt != null)
//					pstmt.close();
//				if(conn != null)
//					cp.releaseConnection(conn);
//					cp.closeAll();
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
		}
		return nl;
	}
	
	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null) 
				pstmt.close();
			if (conn != null)
				cp.releaseConnection(conn);
				cp.closeAll();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
