package com.mapers.myPage.Like.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.sql.DataSource;

import com.mapers.common.DataSourceManager;

public class LikeDAO {
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;

	// singleton pattern
	private static LikeDAO instance = new LikeDAO();
	private DataSource dataSource;
	
	private LikeDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}
	
	public static LikeDAO getInstance() {
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
	
	// 마이 페이지, 문의사항 리스트 조회(검색 기능 추가) - 박강필
	public List<LikeDTO> liketList(Map<String, Object> map) {
		List<LikeDTO> kList = new Vector<LikeDTO>();
		
		String sql = " "
				   + "select * from ( "
				   + "	select Tb.* rownum rNum from ( "
				   + "		select * from likes ";
		
		// 검색 조건
		if (map.get("searchWord") != null) {
			sql += " where " + map.get("searchField")
				 + " like '%" + map.get("searchWorld") + "%' ";
		}
		
		sql += "		order by booknum desc "
			 + "		) Tb " 
			 + "	) " 
			 + " WHERE rNum BETWEEN ? AND ?";
		
		try {
			
			conn = dataSource.getConnection();
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			
			rs = psmt.executeQuery();
			while(rs.next()) {
				LikeDTO dto = new LikeDTO();
				dto.setBookNum(rs.getInt("booknum"));
				dto.setUserId(rs.getString("userid"));
				dto.setTitle(rs.getString("title"));
				dto.setPostDate(rs.getString("postdate"));
				
				kList.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("문의 리스트 조회 중 Error");
		}
		
		return kList;
	}
	
	// 페이징 처리가 된 좋아요한 책들 조회 - 박강필
	public ArrayList<LikeDTO> getAllList(int pageNo, int postsPerPage, String userId) {
	    ArrayList<LikeDTO> kList = new ArrayList<LikeDTO>();

	    String sql = "SELECT * FROM ("
	               + " SELECT a.*, ROWNUM rnum FROM ("
	               + " 	SELECT * FROM likes "
	               + " 	WHERE userid = ? "
	               + " 	ORDER BY booknum DESC) a "
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
	            LikeDTO kDTO = new LikeDTO();
	            
	            kDTO.setBookNum(rs.getInt("booknum"));
	            kDTO.setBookImg(rs.getString("bookimg"));
	            kDTO.setTitle(rs.getString("title"));
	            kDTO.setUserId(rs.getString("userid"));
	            kDTO.setPostDate(rs.getString("postdate"));

	            kList.add(kDTO);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("문의 리스트 조회 중 Error(페이징 처리됨)");
	    }

	    return kList;
	}
	
	// 마이 페이지, 좋아요한 글 전체 포스팅 개수 리턴
	public int getTotalPostCount(String userId) {
		int totalPostCount = 0;

		String sql = " select count(*) from likes where userid=? ";
		
		try {
			
			conn = dataSource.getConnection();
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			
			rs = psmt.executeQuery();
			if (rs.next()) {
				totalPostCount = rs.getInt(1);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error for Searching of MyPage likeList Total Posting");
		}
		
		return totalPostCount;
	}
	
	// 마이 페이지, 좋아요한 책 개수 세기(검색 기능 포함) - 박강필
	public int countTotalLike(Map<String, Object> map) {
		int totalLikeCount = 0;
		
		String sql = " select count(*) from likes";
		
		if(map.get("searchWord") != null) {
			sql += " where " + map.get("searchField") + " like '%" + map.get("searchWord") + "%' ";
		}
		
		try {
			
			conn = dataSource.getConnection();
			
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if (rs.next()) {
				totalLikeCount = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("문의 글 불러오기 중 에러 발생");
		}
		
		return totalLikeCount;
	}
	
	// 마이 페이지, 좋아요한 책 상세보기 - 박강필
	public LikeDTO viewRequest(int bookNum, String userId) {
		LikeDTO dto = new LikeDTO();
		
		String sql = "select * from likes where booknum=? and userid=? ";
		
		try {
			
			conn = dataSource.getConnection();
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bookNum);
			psmt.setString(2, userId);
			
			rs = psmt.executeQuery();
			while (rs.next()) {
				dto.setBookNum(rs.getInt("bookNum"));
				dto.setUserId(rs.getString("userId"));
				dto.setTitle(rs.getString("title"));
				dto.setPostDate(rs.getString("postDate"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("좋아하는 책 상세보기 중 Error");
		}
		
		return dto;
	}

	// 마이 페이지, 좋아요 삭제 - 박강필
	public int deleteRequest(int requestNum) {
		int result = 0;
		
		String sql = "delete from likes where requestnum=?";
		
		try {
			
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
}
