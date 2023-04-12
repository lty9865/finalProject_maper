package com.mapers.myPage.Like.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	public List<LikeDTO> liketList(Map<String, Object> map, String userId) {
		List<LikeDTO> kList = new ArrayList<LikeDTO>();
		
		String sql = "SELECT * FROM ( "
		           + "    SELECT Tb.*, ROWNUM rNum FROM ( "
		           + "        SELECT k.*, b.title, b.ofile, b.sfile "
		           + "        FROM LIKELIST k, BOOK b "
		           + "        WHERE k.BOOKNUM = b.BOOKNUM AND k.USERID = ? ";

		// search requirement
		if (map.get("searchWord") != null) {
		    sql += " AND " + map.get("searchField")
		         + " LIKE '%" + map.get("searchWord") + "%' ";
		}

		sql += "        ORDER BY k.LISTNUM DESC "
		     + "    ) Tb "
		     + ") "
		     + "WHERE rNum BETWEEN ? AND ?";
		
		try {

			if (conn != null) {
				conn.close();
			}
			
			conn = dataSource.getConnection();
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			psmt.setString(2, map.get("start").toString());
			psmt.setString(3, map.get("end").toString());
			
			rs = psmt.executeQuery();
			while(rs.next()) {
				LikeDTO dto = new LikeDTO();
				
				dto.setListNum(rs.getInt("listnum"));
				dto.setUserId(rs.getString("userid"));
				dto.setBookNum(rs.getInt("booknum"));
				
				dto.setOfileBook(rs.getString("ofile"));
				dto.setSfileBook(rs.getString("sfile"));
				
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

	    String sql = "SELECT a.listnum, a.userid, b.title, b.booknum, b.bookdate, a.rnum FROM ("
	            + " SELECT k.*, ROWNUM rnum FROM ("
	            + "  	SELECT k.* FROM LIKELIST k, BOOK b "
	            + "  	WHERE k.booknum = b.booknum AND k.userid = ? "
	            + "  	ORDER BY k.listnum DESC) k "
	            + " WHERE ROWNUM <= ?) a "
	            + " INNER JOIN BOOK b ON a.booknum = b.booknum "
	            + "WHERE a.rnum > ?";

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
	            
	            kDTO.setListNum(rs.getInt("listnum"));
	            kDTO.setUserId(rs.getString("userid"));
	            kDTO.setTitle(rs.getString("title"));
				kDTO.setBookNum(rs.getInt("booknum"));
				kDTO.setPostDate(rs.getString("bookdate"));
				
	            kList.add(kDTO);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("문의 리스트 조회 중 Error(페이징 처리됨)");
	    }

	    return kList;
	}
	
	// 마이 페이지, 좋아요한 책 개수 리턴 - 박강필
	public int getTotalPostCount(String userId) {
		int totalPostCount = 0;

		String sql = " select count(*) from likelist where userid=? ";
		
		try {

			if (conn != null) {
				conn.close();
			}
			
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
	public int countTotalLike(Map<String, Object> map, String userId) {
		int totalLikeCount = 0;
		
		String sql = " select count(*) from likelist where userid = ?";
		
		if(map.get("searchWord") != null) {
			sql += " where " + map.get("searchField") + " like '%" + map.get("searchWord") + "%' ";
		}
		
		try {

			if (conn != null) {
				conn.close();
			}
			
			conn = dataSource.getConnection();
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			
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
	
	// 마이 페이지, 좋아요 누른 책 쪽수 구하기 - 박강필
	public int countBookPage(int bookNum) {
		int bookPage = 0;
		
		String sql = "select count(*) from book where booknum=? ";
		
		try {
			
			if (conn != null) {
				conn.close();
			}
			
			conn = dataSource.getConnection();
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bookNum);
			
			rs = psmt.executeQuery();
			if (rs.next()) {
				bookPage = rs.getInt(1);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("count Book Page Error");
		}
		
		return bookPage;
	}
	
	// 마이 페이지, 좋아요한 책 상세보기 - 박강필
	public LikeDTO viewLike(int bookNum, String userId) {
		LikeDTO dto = new LikeDTO();
		
		String sql = "select * from likelist where booknum=? and userid=? ";
		
		try {

			if (conn != null) {
				conn.close();
			}
			
			conn = dataSource.getConnection();
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bookNum);
			psmt.setString(2, userId);
			
			rs = psmt.executeQuery();
			while (rs.next()) {
				dto.setBookNum(rs.getInt("booknum"));
				dto.setUserId(rs.getString("userid"));
				dto.setOfileBook(rs.getString("ofile"));
				dto.setSfileBook(rs.getString("sfile"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("좋아하는 책 상세보기 중 Error");
		}
		
		return dto;
	}

	// 마이 페이지, 좋아요 삭제 - 박강필
	public int deleteLike(int bookNum, String userId) {
		int result = 0;
		
		String sql = "delete from likelist where booknum=? and userid = ?";
		
		try {
			
			if (conn != null) {
				conn.close();
			}
			
			conn = dataSource.getConnection();
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bookNum);
			psmt.setString(1, userId);
			
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("문의 글 삭제 중 Error");
		}
		
		return result;
	}
}
