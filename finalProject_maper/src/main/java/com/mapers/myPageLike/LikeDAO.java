package com.mapers.myPageLike;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.sql.DataSource;

import com.mapers.common.DataSourceManager;
import com.mapers.myPageMember.ProfileDTO;
import com.mapers.myPageModel.PagingBean;

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
	public List<LikeDTO> requestList(Map<String, Object> map) {
		List<LikeDTO> rList = new Vector<LikeDTO>();
		
		String sql = " "
				   + "select * from ( "
				   + "	select Tb.* rownum rNum from ( "
				   + "		select * from request ";
		
		// 검색 조건
		if (map.get("searchWord") != null) {
			sql += " where " + map.get("searchField")
				 + " like '%" + map.get("searchWorld") + "%' ";
		}
		
		sql += "		order by requestNum desc "
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
				
				dto.setRequestNum(rs.getInt("requestNum"));
				dto.setUserId(rs.getString("userId"));
				dto.setTitle(rs.getString("title"));
				dto.setStatus(rs.getInt("status"));
				dto.setPostDate(rs.getString("postDate"));
				
				rList.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("문의 리스트 조회 중 Error");
		}
		
		return rList;
	}
	
	// 마이 페이지, 문의 글 전체 포스팅 개수 리턴
	public int getTotalPostCount() {
		int totalPostCount = 0;

		String sql = " select count(*) from  request ";
		
		try {
			
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
	public LikeDTO viewRequest(int requestNum, String userId) {
		LikeDTO dto = new LikeDTO();
		
		String sql = "select * from request where requestnum=? and userid=? ";
		
		try {
			
			conn = dataSource.getConnection();
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, requestNum);
			psmt.setString(2, userId);
			
			rs = psmt.executeQuery();
			while (rs.next()) {
				dto.setRequestNum(rs.getInt("requestNum"));
				dto.setUserId(rs.getString("userId"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostDate(rs.getString("postDate"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("문의사항 글 상세보기 중 Error");
		}
		
		return dto;
	}

	
	// 마이 페이지, 문의 글 작성 - 박강필
	public int insertRequest(LikeDTO dto) {
		int result = 0;
		
		// requestNunm = request_seq로 처리, postDate = sysdate로 처리
		String sql = " insert into request(requestNum, userId, title, content, postDate "
				   + " values (c##mapers.request_seq.nextval, ?, ?, ?, sysdate)";
		
		try {
			
			conn = dataSource.getConnection();
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getUserId());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			// postDate는 DB sysdate로 처리
			
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("문의 글 작성 중 에러");
		}
		
		return result;
	}
	
	// 마이 페이지, 문의 글 수정 - 박강필
	public int editRequest(LikeDTO dto) {
		int result = 0;
		
		String sql = "update request set title=?, content=?, where requestNum=?";
		
		try {
			
			conn = dataSource.getConnection();
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setInt(3, dto.getRequestNum());
			
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
	public ArrayList<LikeDTO> getAllList(PagingBean pagingBean) {
		ArrayList<LikeDTO> rList = new ArrayList<LikeDTO>();
		
		String sql = " select r.*, p.userId "
				   + " from ( "
				   + " 		 select row_number() over(order by requestNum desc) as rnum, "
				   + " 		 requestNum, title, status, to_char(postDate, 'YYYY-MM-DD') as postDate, userId "
				   + " 		 from request "
				   + " 		) request r, profile p "
				   + " 		where r.userId = p.userId and rnum between ? and ? 		";
		
		try {
			
			conn = dataSource.getConnection();
			
			psmt = conn.prepareStatement(sql);
			// start, endRowNumber 할당함
			psmt.setInt(1, pagingBean.getStartRowNumber());
			psmt.setInt(2, pagingBean.getEndRowNumber());
			
			rs = psmt.executeQuery();
			while(rs.next()) {
				ProfileDTO pDTO = new ProfileDTO();
				pDTO.setUserId(rs.getString("userId"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("문의사항 리스트 페이징 처리 중 Error");
		}
		
		return rList;
	}
	
	
	
	// 마이 페이지(관리자 모드), 문의사항 리스트 조회 - 박강필
	
	
	// 마이 페이지(관리자 모드), 문의 글 작성(문의 상태 변경 기능 추가) - 박강필
	
	
	// 마이 페이지(관리자 모드), 문의 글 수정(문의 상태 변경 기능 추가) - 박강필
	
	
	// 마이 페이지(관리자 모드), 문의 글 삭제(문의 상태 변경 기능 추가) - 박강필
	
	
	// 마이 페이지(관리자 모드), 신고글 개수 세기 - 박강필
	
	
	// 마이 페이지(관리자 모드), 신고 리스트 조회 - 박강필
	
	
	// 마이 페이지(관리자 모드), 신고 글 상태 변경 - 박강필
}
