package com.mapers.myPageRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.mapers.common.ConnectionPool;

public class RequestBoardDAO {
	private ConnectionPool cp;
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	// 커넥션 풀 사용법
	public RequestBoardDAO() throws SQLException {
		// oracle url, oracle userId, oracle userPassword, 초기 커넥션 수, 최대 커넥션 수
		cp = ConnectionPool.getInstance("jdbc:oracle:thin:@localhost:1521:xe", "c##mapers", "mapers1234", 5, 10);
		
		conn = cp.getConnection();
		
		psmt = null;
		rs = null;
	}	
	
	// 마이 페이지, 문의사항 리스트 조회 - 박강필
	public List<RequestBoardDTO> requestList() {
		List<RequestBoardDTO> requestBoard = new Vector<RequestBoardDTO>();
		
		String sql = " select * from ( "
				   + " 		select Tb.*, rownum from ( "
				   + " 			select * from requestboard ";	
		
//		String sql = "select * from request";
		
		try {
			
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			close();
		}
		
		return requestBoard;
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
