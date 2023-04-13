package com.mapers.page.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.sql.DataSource;

import com.mapers.common.DataSourceManager;

public class PageDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// singleton pattern
	private static PageDAO instance = new PageDAO();
	private DataSource dataSource;

	private PageDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}

	public static PageDAO getInstance() {
		return instance;
	}

	// 자원 반납
	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 페이지 리스트 개수 세기 - 김연호
	public int pageCount(Map<String, Object> map) {
		int totalCount = 0;
		String query = "SELECT COUNT(*) FROM PAGE WHERE BOOKNUM=" + map.get("idx");

		if (map.get("searchWord") != null) {
			query += " AND " + map.get("searchField") + " " + " LIKE '%" + map.get("searchWord") + "%'";
		}

		try {
			if (conn != null) {
				conn.close();
			}
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalCount = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("페이지 목록 조회 중 예외 발생");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return totalCount;
	}

	// 검색 조건에 맞는 게시물 목록 조회 - 김연호
	public List<PageDTO> selectPageList(Map<String, Object> map) {
		List<PageDTO> pageList = new Vector<PageDTO>();

		String query = " " + "SELECT * FROM ( " + " SELECT Tb.*, ROWNUM rNUM FROM ( " + "  SELECT * FROM PAGE ";

		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%' ";
			query += " AND BOOKNUM=" + map.get("idx");
		} else {
			query += " WHERE BOOKNUM=" + map.get("idx");
		}

		query += "   ORDER BY PAGENUM DESC " + "  ) Tb " + " ) " + " WHERE rNUM BETWEEN ? AND ?";

		try {
			if (conn != null) {
				conn.close();
			}
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, map.get("start").toString());
			pstmt.setString(2, map.get("end").toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PageDTO dto = new PageDTO();

				dto.setPageNum(rs.getInt("PAGENUM"));
				dto.setBookNum(rs.getInt("BOOKNUM"));
				dto.setSubTitle(rs.getString("TITLE"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setPostDate(rs.getString("PAGEDATE"));
				dto.setRate(rs.getInt("RATE"));
				dto.setPercent(rs.getInt("RATE"));
				dto.setSfile(rs.getString("SFILE"));
				dto.setOfile(rs.getString("OFILE"));

				pageList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("선택 게시물 조회 중 예외 발생");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return pageList;
	}

	// 페이지 게시글 insert 등록 - 김연호
	public int insertPage(PageDTO dto) {
		int result = 0;
		try {
			if (conn != null) {
				conn.close();
			}
			String query = "INSERT INTO PAGE(PAGENUM,BOOKNUM,TITLE,CONTENT,RATE,SFILE,OFILE,PAGEDATE)"
					+ " VALUES(C##MAPERS.PAGE_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, dto.getBookNum());
			pstmt.setString(2, dto.getSubTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setDouble(4, dto.getRate());
			pstmt.setString(5, dto.getSfile());
			pstmt.setString(6, dto.getOfile());
			pstmt.setString(7, dto.getPostDate());
			result = pstmt.executeUpdate();

			updateRate(dto.getBookNum());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("페이지 입력 중 예외 발생");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// 주어진 일련번호에 해당하는 게시물을 DTO에 담아 반환 - 김연호
	public PageDTO selectPageView(int idx) {
		PageDTO dto = new PageDTO();
		String query = "SELECT * FROM PAGE WHERE PAGENUM=?";
		try {
			if (conn != null) {
				conn.close();
			}
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setPageNum(rs.getInt("PAGENUM"));
				dto.setBookNum(rs.getInt("BOOKNUM"));
				dto.setSubTitle(rs.getString("TITLE"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setPostDate(rs.getString("PAGEDATE"));
				dto.setRate(rs.getInt("RATE"));
				dto.setPercent(rs.getInt("RATE"));
				dto.setOfile(rs.getString("OFILE"));
				dto.setSfile(rs.getString("SFILE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("페이지 게시물 조회 중 예외 발생");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return dto;
	}

	// 지정한 일련번호의 게시물을 삭제합니다.
	public int deletePage(int idx) {
		int result = 0;
		String query = "DELETE FROM PAGE WHERE PAGENUM=?";
		try {
			if (conn != null) {
				conn.close();
			}
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("페이지 삭제 중 예외 발생");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int deleteAllPage(int bookNum) {
		int result = 0;
		int totalCount = 0;
		String query = "SELECT COUNT(*) FROM PAGE WHERE BOOKNUM=?";
		try {
			if (conn != null) {
				conn.close();
			}
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookNum);
			rs = pstmt.executeQuery();
			rs.next();
			totalCount = rs.getInt(1);

			query = "DELETE FROM PAGE WHERE BOOKNUM=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookNum);
			result = pstmt.executeUpdate();
			if (totalCount == result) {
				System.out.println("북 삭제를 위해 모든 페이지를 삭제했습니다.");
				result = 1;
			} else {
				System.out.println("북 삭제를 위한 모든 페이지 삭제에 실패하였습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// 지정한 페이지 게시물 수정 - 김연호
	public int updatePage(PageDTO dto) {
		int result = 0;
		String query = "UPDATE PAGE" + " SET title=?, content=?, rate=?, ofile=?, sfile=?, PAGEDATE=?" + " WHERE PAGENUM=?";
		try {
			if (conn != null) {
				conn.close();
			}

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getSubTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getRate());
			pstmt.setString(4, dto.getOfile());
			pstmt.setString(5, dto.getSfile());
			pstmt.setString(6, dto.getPostDate());
			pstmt.setInt(7, dto.getPageNum());

			result = pstmt.executeUpdate();
			int bookNum = dto.getBookNum();
			updateRate(bookNum);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("페이지 게시물 수정 중 예외 발생");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// 페이지 출력 시 리스트 업 - 김연호
	public List<PageDTO> pageViewList(int idx) {
		List<PageDTO> pageList = new Vector<PageDTO>();
		String query = "SELECT * FROM PAGE WHERE BOOKNUM=? ORDER BY PAGENUM ASC";
		try {
			if (conn != null) {
				conn.close();
			}

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PageDTO dto = new PageDTO();

				dto.setPageNum(rs.getInt("PAGENUM"));
				dto.setBookNum(rs.getInt("BOOKNUM"));
				dto.setSubTitle(rs.getString("TITLE"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setPostDate(rs.getString("PAGEDATE"));
				dto.setRate(rs.getInt("RATE"));
				dto.setPercent(rs.getInt("RATE"));
				dto.setSfile(rs.getString("SFILE"));
				dto.setOfile(rs.getString("OFILE"));

				pageList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("페이지 책 출력 중 예외 발생");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return pageList;
	}

	// 북 만족도 업데이트 - 김연호
	public int updateRate(int bookNum) {
		int result = 0;
		String query = "UPDATE BOOK SET RATE=" + "(SELECT AVG(RATE) FROM PAGE WHERE BOOKNUM=?) WHERE BOOKNUM=?";
		try {
			if (conn != null) {
				conn.close();
			}

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookNum);
			pstmt.setInt(2, bookNum);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("북 만족도 업데이트 중 예외 발생");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

}
