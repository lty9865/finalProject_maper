package com.mapers.myPage.Request.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.mapers.common.DataSourceManager;

public class RequestDAO {
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;

	// singleton pattern
	private static RequestDAO instance = new RequestDAO();
	private DataSource dataSource;
	
	private RequestDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}
	
	public static RequestDAO getInstance() {
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
			System.out.println("DB Connection Pool 자원 반납 Error");
		}
	}
	
	// 마이 페이지, 문의사항 리스트 조회(검색 기능 추가) - 박강필
	public List<RequestDTO> requestList(Map<String, Object> map, String userId) {
		List<RequestDTO> rList = new ArrayList<RequestDTO>();
		
		try {

			if (conn != null) {
				conn.close();
			}
			
			conn = dataSource.getConnection();
			
			String sql;
			if (map.get("searchWord") != null) {
				sql = "SELECT * FROM ( "
				    + "		SELECT a.*, ROWNUM rnum FROM ("
				    + "			SELECT * FROM request "
				    + "			WHERE userid = ? AND ? LIKE ? ORDER BY reqnum)"
				    + "		 a WHERE ROWNUM <= ?) "
				    + "WHERE rnum > ?";
				
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, userId);
				psmt.setString(2, map.get("searchField").toString());
				psmt.setString(3, "%" + map.get("searchWord") + "%");
				psmt.setString(4, map.get("start").toString());
				psmt.setString(5, map.get("end").toString());
				
			} else {
				sql = "SELECT * FROM ("
					+ "		SELECT a.*, ROWNUM rnum FROM ("
					+ "			SELECT * FROM request "
					+ "			WHERE userid = ? "
					+ "			ORDER BY reqnum) "
					+ "		a WHERE ROWNUM <= ?) "
					+ "WHERE rnum > ?";
				
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, userId);
				psmt.setString(2, map.get("start").toString());
				psmt.setString(3, map.get("end").toString());
				
			}
			
			rs = psmt.executeQuery();
			while(rs.next()) {
				RequestDTO dto = new RequestDTO();
				
				dto.setRequestNum(rs.getInt("reqnum"));
				dto.setTitle(rs.getString("title"));
				dto.setUserId(rs.getString("userid"));
				dto.setStatus(rs.getInt("status"));
				dto.setPostDate(rs.getString("postdate"));
				
				rList.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("문의 리스트 조회 중 Error");
		} 
		
		return rList;
	}
	
	// 마이 페이지, 문의 글 전체 포스팅 개수 리턴 - 박강필
	public int getTotalPostCount() {
		int totalPostCount = 0;

		String sql = " select count(*) from  request ";
		
		try {
			
			if (conn != null) {
				conn.close();
			}
			
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			
			rs = psmt.executeQuery();
			if (rs.next()) {
				totalPostCount = rs.getInt(1);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error for Searching of MyPage RequestList Total Posting");
		} 
		
		return totalPostCount;
	}
	
	// 마이 페이지, 문의글 개수 세기(검색 기능 포함) - 박강필
	public int countTotalRequest(Map<String, Object> map) {
		int totalRequestCount = 0;
		
		String sql = " select count(*) from request";
		
		if(map.get("searchWord") != null) {
			sql += " where " + map.get("searchField") + " like '%" + map.get("searchWord") + "%' ";
		}
		
		try {

			if (conn != null) {
				conn.close();
			}
			
			conn = dataSource.getConnection();
			
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if (rs.next()) {
				totalRequestCount = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("문의 글 불러오기 중 에러 발생");
		} 
		
		return totalRequestCount;
	}
	
	// 마이 페이지, 문의 글 상세보기 - 박강필
	public RequestDTO viewRequest(int requestNum, String userId) {
		RequestDTO dto = new RequestDTO();
		
		String sql = "select * from request where requestnum=? and userid=? ";
		
		try {

			if (conn != null) {
				conn.close();
			}
			
			conn = dataSource.getConnection();
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, requestNum);
			psmt.setString(2, userId);
			
			rs = psmt.executeQuery();
			while (rs.next()) {
				dto.setRequestNum(rs.getInt("requestnum"));
				dto.setUserId(rs.getString("userid"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostDate(rs.getString("postdate"));
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("문의사항 글 상세보기 중 Error");
		} 
		
		return dto;
	}

	
	// 마이 페이지, 문의 글 작성 - 박강필
	public int insertRequest(RequestDTO dto) {
	    int result = 0;

	    // requestNunm = request_seq로 처리
	    String sql = "INSERT INTO request(requestnum, userid, title, content, postdate)"
	            + " VALUES (c##mapers.request_seq.nextval, ?, ?, ?, ?)";

	    try {

	    	conn = dataSource.getConnection();

	        psmt = conn.prepareStatement(sql);
	        psmt.setString(1, dto.getUserId());
	        psmt.setString(2, dto.getTitle());
	        psmt.setString(3, dto.getContent());
	        psmt.setString(4, dto.getPostDate());

	        result = psmt.executeUpdate();
	        System.out.println("문의 글 저장 완료");

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("문의 글 작성 중 에러");
	    } finally {
	    	closeAll();
	    }

	    return result;
	}

	
	// 마이 페이지, 문의 글 수정 - 박강필
	public int editRequest(RequestDTO dto) {
		int result = 0;
		
		String sql = " update request set title = ?, content = ? where userid = ? and requestnum = ? ";
		
		try {

			if (conn != null) {
				conn.close();
			}
			
			conn = dataSource.getConnection();
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getUserId());
			psmt.setInt(4, dto.getRequestNum());
			
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("문의 글 수정 중 Error");
		} 
		
		return result;
	}
	
	// 마이 페이지, 문의 글 삭제 - 박강필
	public int deleteRequest(int requestNum) {
		int result = 0;
		
		String sql = "delete from request where requestnum=?";
		
		try {

			if (conn != null) {
				conn.close();
			}
			
			conn = dataSource.getConnection();
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, requestNum);
			
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("문의 글 삭제 중 Error");
		} 
		
		return result;
	}
	
	// 게시글 조회 수 증가(hits)
	public void addHits(String no) {
		
		String sql = "update request set hits = 1 where no=?";
		
		try {

			if (conn != null) {
				conn.close();
			}
			
			conn = dataSource.getConnection();

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, no);
			
			psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("게시글 조회 수 증가 중 Error");
		} 
	}
	
	// 마이 페이지, 문의사항 페이지 번호에 해당하는 게시물 리스트 조회 - 박강필
	public ArrayList<RequestDTO> getAllList(int pageNo, int postsPerPage, String userId) {
	    ArrayList<RequestDTO> rList = new ArrayList<RequestDTO>();

	    String sql = "SELECT * FROM ("
	               + " SELECT a.*, ROWNUM rnum FROM ("
	               + " 	SELECT * FROM request "
	               + " 	WHERE userid = ? "
	               + " 	ORDER BY requestnum DESC) a "
	               + " WHERE ROWNUM <= ?) "
	               + "WHERE rnum > ?";

	    try {
	    	
	    	if (conn != null) {
	    		conn.close();
	    	}
	    	
	        conn = dataSource.getConnection();

	        psmt = conn.prepareStatement(sql);
	        psmt.setString(1, userId);
	        psmt.setInt(2, pageNo * postsPerPage);
	        psmt.setInt(3, (pageNo - 1) * postsPerPage);

	        rs = psmt.executeQuery();
	        while (rs.next()) {
	            RequestDTO rDTO = new RequestDTO();
	            
	            rDTO.setRequestNum(rs.getInt("requestnum"));
	            rDTO.setTitle(rs.getString("title"));
	            rDTO.setStatus(rs.getInt("status"));
	            rDTO.setPostDate(rs.getString("postdate"));

	            rList.add(rDTO);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("문의 리스트 조회 중 Error(페이징 처리됨)");
	    }

	    return rList;
	}
}
