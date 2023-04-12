package com.mapers.myPage.Admins.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.mapers.SignUp.MemberDTO;
import com.mapers.common.DataSourceManager;
import com.mapers.myPage.Request.model.RequestDTO;
import com.mapers.report.ReportDTO;

public class AdminsDAO {
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;

	// singleton pattern
	private static AdminsDAO instance = new AdminsDAO();
	private DataSource dataSource;
	
	private AdminsDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}
	
	public static AdminsDAO getInstance() {
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
	
	// 마이 페이지(관리자 모드), 문의사항 리스트 조회(검색 기능 추가) - 박강필
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
	
	// 마이 페이지(관리자 모드), 가입한 회원 전체 인원 수 리턴 - 박강필
	public int getMemberTotalPostCount() {
		int totalPostCount = 0;

		String sql = " select count(*) from  account ";
		
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
			System.out.println("Error to get the number of whole Members");
		} 
		
		return totalPostCount;
	}
	
	// 마이 페이지(관리자 모드), 문의 글 전체 포스팅 개수 리턴 - 박강필
	public int getRequestTotalPostCount() {
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
	
	// 마이 페이지(관리자 모드), 신고 글 전체 포스팅 개수 리턴 - 박강필
	public int getReportTotalPostCount() {
		int totalPostCount = 0;

		String sql = " select count(*) from  report ";
		
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
			System.out.println("Error to get the number of Report Total Posting");
		} 
		
		return totalPostCount;
	}
	
	// 마이 페이지(관리자 모드), 문의글 개수 세기(검색 기능 포함) - 박강필
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
	
	// 마이 페이지(관리자 모드), 문의 글 상세보기 - 박강필
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
	
	// 마이 페이지(관리자 모드), 신고 글 상세보기 - 박강필
	public ReportDTO viewReport(int reportNum) {
		ReportDTO wDTO = new ReportDTO();
		
		String sql = "select r.*, b.title "
				   + "from report r, book b "
				   + "where r.booknum = b.booknum and r.reportnum=? ";
		
		try {

			if (conn != null) {
				conn.close();
			}
			
			conn = dataSource.getConnection();
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, reportNum);
			
			rs = psmt.executeQuery();
			while (rs.next()) {
				wDTO.setReportNum(rs.getInt("reportnum"));
				wDTO.setBookNum(rs.getInt("booknum"));
				wDTO.setBookTitle(rs.getString("title"));
				wDTO.setUserId(rs.getString("userid"));
				wDTO.setCount(rs.getInt("reportcount"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("문의사항 글 상세보기 중 Error");
		} 
		
		return wDTO;
	}
	
	// 마이 페이지(관리자 모드), 문의 글 작성 - 박강필
	public int insertRequest(RequestDTO dto) {
	    int result = 0;

	    // requestNunm = request_seq로 처리
	    String sql = "INSERT INTO request(requestnum, userid, title, content, postdate)"
	            + " VALUES (c##mapers.request_seq.nextval, ?, ?, ?, ?)";

	    try {

			if (conn != null) {
				conn.close();
			}
			
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

	
	// 마이 페이지(관리자 모드), 문의 글 수정 - 박강필
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
	
	// 마이 페이지(관리자 모드), 문의 글 답변 완료 시 상태 변경 - 박강필
	public int updateRequestStatus(int requestNum) {
	    int result = 0;
	    PreparedStatement psmt = null;

	    String sql = "UPDATE request SET status = 0 WHERE requestnum = ?";

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
	    } 

	    return result;
	}

	
	// 마이 페이지(관리자 모드), 문의 글 삭제 - 박강필
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
	
	// 마이 페이지, 처리 완료된 신고 글 삭제 - 박강필
	public int deleteReport(int reportNum) {
		int result = 0;
		
		String sql = "delete from report where reportnum=?";
		
		try {

			if (conn != null) {
				conn.close();
			}
			
			conn = dataSource.getConnection();
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, reportNum);
			
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("신고 글 삭제 중 Error");
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
	
	// 마이 페이지(관리자 모드), 문의사항 페이지 번호에 해당하는 게시물 리스트 조회 - 박강필
	public ArrayList<MemberDTO> getAllMemberList(int pageNo, int postsPerPage) {
	    ArrayList<MemberDTO> mList = new ArrayList<MemberDTO>();

	    String sql = "SELECT * FROM ("
	               + " SELECT a.*, ROWNUM rnum FROM ("
	               + " 	SELECT * FROM account "
	               + " 	ORDER BY userid DESC) a "
	               + " WHERE ROWNUM <= ?) "
	               + "WHERE rnum > ?";

	    try {
	    	
	    	if (conn != null) {
	    		conn.close();
	    	}
	    	
	        conn = dataSource.getConnection();

	        psmt = conn.prepareStatement(sql);
	        psmt.setInt(1, pageNo * postsPerPage);
	        psmt.setInt(2, (pageNo - 1) * postsPerPage);

	        rs = psmt.executeQuery();
	        while (rs.next()) {
	            MemberDTO mDTO = new MemberDTO();
	            
	            mDTO.setUserId(rs.getString("userid"));
	            mDTO.setEmail(rs.getString("usermail"));
	            mDTO.setState(rs.getInt("state"));
	            mDTO.setJoinDate(rs.getString("joindate"));
	            mDTO.setDeleteDate(rs.getString("deletedate"));
	            
	            mList.add(mDTO);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("문의 리스트 조회 중 Error(페이징 처리됨)");
	    }

	    return mList;
	}
	
	// 마이 페이지(관리자 모드), 문의사항 페이지 번호에 해당하는 게시물 리스트 조회 - 박강필
	public ArrayList<RequestDTO> getAllRequestList(int pageNo, int postsPerPage) {
	    ArrayList<RequestDTO> rList = new ArrayList<RequestDTO>();

	    String sql = "SELECT * FROM ("
	               + " SELECT a.*, ROWNUM rnum FROM ("
	               + " 	SELECT * FROM request "
	               + " 	ORDER BY requestnum DESC) a "
	               + " WHERE ROWNUM <= ?) "
	               + "WHERE rnum > ?";

	    try {
	    	
	    	if (conn != null) {
	    		conn.close();
	    	}
	    	
	        conn = dataSource.getConnection();

	        psmt = conn.prepareStatement(sql);
	        psmt.setInt(1, pageNo * postsPerPage);
	        psmt.setInt(2, (pageNo - 1) * postsPerPage);

	        rs = psmt.executeQuery();
	        while (rs.next()) {
	            RequestDTO rDTO = new RequestDTO();
	            
	            rDTO.setRequestNum(rs.getInt("requestnum"));
	            rDTO.setUserId(rs.getString("userid"));
	            rDTO.setTitle(rs.getString("title"));
	            rDTO.setContent(rs.getString("content"));
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
	
	// 마이 페이지(관리자 모드), 신고 글 리스트 조회 - 박강필
	public ArrayList<ReportDTO> getAllReportList(int pageNo, int postsPerPage) {
	    ArrayList<ReportDTO> wList = new ArrayList<ReportDTO>();

	    String sql = "SELECT * FROM ("
	               + " SELECT a.*, b.title, ROWNUM rnum FROM ("
	               + " SELECT r.* FROM report r"
	               + " JOIN book b ON r.BOOKNUM = b.BOOKNUM "
	               + " ORDER BY r.reportnum DESC) a, book b "
	               + " WHERE a.BOOKNUM = b.BOOKNUM AND ROWNUM <= ?) "
	               + "WHERE rnum > ?";

	    try {

			if (conn != null) {
				conn.close();
			}
			
	        conn = dataSource.getConnection();

	        psmt = conn.prepareStatement(sql);
	        psmt.setInt(1, pageNo * postsPerPage);
	        psmt.setInt(2, (pageNo - 1) * postsPerPage);

	        rs = psmt.executeQuery();
	        while (rs.next()) {
	            ReportDTO wDTO = new ReportDTO();

	            wDTO.setReportNum(rs.getInt("reportnum"));
	            wDTO.setBookTitle(rs.getString("title"));
	            wDTO.setUserId(rs.getString("userid"));
	            wDTO.setBookNum(rs.getInt("booknum"));
	            wDTO.setCount(rs.getInt("reportcount"));

	            wList.add(wDTO);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("신고 리스트 조회 중 Error(페이징 처리됨)");
	    }

	    return wList;
	}
}
