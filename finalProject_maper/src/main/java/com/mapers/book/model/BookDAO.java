package com.mapers.book.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.sql.DataSource;

import com.mapers.common.DataSourceManager;
import com.mapers.page.model.PageDAO;

public class BookDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// singleton pattern
	private static BookDAO instance = new BookDAO();
	private DataSource dataSource;

	private BookDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}

	public static BookDAO getInstance() {
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
		}
	}

	// 북리스트 개수 세기 - 김연호
	public int countBook(Map<String, Object> map, String userId) {
		int totalCount = 0;

		String query = "SELECT COUNT(*) FROM";
		if (userId != null) {
			query += " (SELECT * FROM BOOK WHERE USERID LIKE '" + userId + "') BOOK";
		} else {
			query += " BOOK";
		}
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%'";
		}

		try {
			if (conn != null) {
				conn.close();
			}
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			rs.next();
			totalCount = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("북리스트 개수 세기 중 예외 발생");
		}
		return totalCount;
	}

	// 검색조건에 맞는 게시물 목록 반환(페이징)
	public List<BookDTO> selectBookList(Map<String, Object> map, String userId) {
		List<BookDTO> bookList = new Vector<BookDTO>();

		String query = " " + "SELECT * FROM ( " + "	SELECT Tb.*, ROWNUM rNum FROM ( " + "  SELECT * FROM";
		if (userId != null) {
			query += " (SELECT * FROM BOOK WHERE USERID LIKE '" + userId + "') BOOK";
		} else {
			query += " BOOK";
		}
		// 검색 조건이 있다면 where 조건 추가
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%' ";
		}

		query += "   ORDER BY BOOKNUM DESC" + "  ) Tb " + " ) " + " WHERE rNum BETWEEN ? AND ?";

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
				BookDTO dto = new BookDTO();

				dto.setBookNum(rs.getInt("BOOKNUM"));
				dto.setUserId(rs.getString("USERID"));
				String[] place = rs.getString("PLACE").split("/");
				dto.setCountry(place[0]);
				dto.setCity(place[1]);
				dto.setBookDate(rs.getString("BOOKDATE"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setBlock(rs.getInt("BLOCKS"));
				dto.setRate(rs.getFloat("RATE"));
				dto.setVisitCount(rs.getInt("VISITCOUNT"));
				dto.setLikesCount(rs.getInt("LIKESCOUNT"));
				dto.setOfile(rs.getString("OFILE"));
				dto.setSfile(rs.getString("SFILE"));

				bookList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("북리스트 게시물 조회 중 예외 발생");
		}
		return bookList;
	}

	// 북리스트 작성 DB Insert - 김연호
	public int insertBook(BookDTO dto) {
		int result = 0;

//		// 테스트 용 반복문
//		for(int i=1; i<=20; i++) {
//			String query = "INSERT INTO BOOK(BOOKNUM,USERID,PLACE,BOOKDATE,TITLE,SFILE,OFILE) "
//					+ "VALUES(C##MAPERS.BOOK_SEQ.NEXTVAL,?,?,?,?,?,?)";
//			
//			try {
//				if (conn != null) {
//					conn.close();
//				}
//				conn = dataSource.getConnection();
//				pstmt = conn.prepareStatement(query);
//				pstmt.setString(1, dto.getUserId());
//				pstmt.setString(2, dto.getCountry() + "/" + dto.getCity());
//				pstmt.setString(3, dto.getBookDate());
//				pstmt.setString(4, dto.getTitle() + i);
//				pstmt.setString(5, dto.getSfile());
//				pstmt.setString(6, dto.getOfile());
//				result = pstmt.executeUpdate();
//			} catch (Exception e) {
//				e.printStackTrace();
//				System.out.println("북 작성 중 예외 발생");
//			}
//		}

		String query = "INSERT INTO BOOK(BOOKNUM,USERID,PLACE,BOOKDATE,TITLE,SFILE,OFILE) "
				+ "VALUES(C##MAPERS.BOOK_SEQ.NEXTVAL,?,?,?,?,?,?)";

		try {
			if (conn != null) {
				conn.close();
			}
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getUserId());
			pstmt.setString(2, dto.getCountry() + "/" + dto.getCity());
			pstmt.setString(3, dto.getBookDate());
			pstmt.setString(4, dto.getTitle());
			pstmt.setString(5, dto.getSfile());
			pstmt.setString(6, dto.getOfile());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("북 작성 중 예외 발생");
		}
		return result;
	}

	// 북리스트 중 원하는 북 선택 - 김연호
	public BookDTO selectBook(int idx) {
		BookDTO dto = new BookDTO();
		String query = "SELECT * FROM BOOK WHERE BOOKNUM=?";
		try {
			if (conn != null) {
				conn.close();
			}
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setBookNum(rs.getInt("BOOKNUM"));
				dto.setUserId(rs.getString("USERID"));
				String[] place = rs.getString("PLACE").split("/");
				dto.setCountry(place[0]);
				dto.setCity(place[1]);
				dto.setBookDate(rs.getString("BOOKDATE"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setBlock(rs.getInt("BLOCKS"));
				dto.setRate(rs.getFloat("RATE"));
				dto.setVisitCount(rs.getInt("VISITCOUNT"));
				dto.setLikesCount(rs.getInt("LIKESCOUNT"));
				dto.setSfile(rs.getString("SFILE"));
				dto.setOfile(rs.getString("OFILE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("북 상세보기 중 예외 발생");
		}

		return dto;
	}

	public int updateBook(BookDTO dto) {
		int result = 0;
		try {
			if (conn != null) {
				conn.close();
			}
			String query = "UPDATE BOOK SET TITLE=?, PLACE=?, BOOKDATE=?, OFILE=?, SFILE=? WHERE BOOKNUM=?";

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, (dto.getCountry() + "/" + dto.getCity()));
			pstmt.setString(3, dto.getBookDate());
			pstmt.setString(4, dto.getOfile());
			pstmt.setString(5, dto.getSfile());
			pstmt.setInt(6, dto.getBookNum());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("북 수정 중 예외 발생");
		}

		return result;
	}

	// 북 조회수 + 1
	public void updateBookVisitCount(int idx) {
		String query = "UPDATE BOOK SET " + " VISITCOUNT = VISITCOUNT+1 " + " WHERE BOOKNUM=?";

		try {
			if (conn != null) {
				conn.close();
			}
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
			pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("북 조회수 1증가 도중 예외 발생");
		}
	}

	// 북 좋아요 + 1 및 사용자 저장
	public void updateLikesCount(int idx) {
		String query = "UPDATE BOOK SET LIKESCOUNT = LIKESCOUNT + 1 WHERE BOOKNUM=?";

		try {
			if (conn != null) {
				conn.close();
			}
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
			pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("북 좋아요 1증가 도중 예외 발생");
		}
	}

	// 북 좋아요 누른 사용자 DB 저장
	public int insertLikeUser(int bookNum, String userId) {
		int result = 0;
		String query = "INSERT INTO LIKELIST VALUES(C##MAPERS.LIKE_SEQ.NEXTVAL,?,?)";
		try {
			if (conn != null) {
				conn.close();
			}
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setInt(2, bookNum);
			result = pstmt.executeUpdate();
			updateLikesCount(bookNum);	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("좋아요 사용자 저장 중 예외 발생");
		}
		return result;
	}

	// 북리스트 삭제
	public int deleteBook(int idx) {
		int result = 0;
		PageDAO pageDao = PageDAO.getInstance();
		int deleteAllPage = pageDao.deleteAllPage(idx);
		if (deleteAllPage >= 1) {
			System.out.println("북 삭제 전 페이지 삭제 성공");
			pageDao.close();
		}
		String query = "DELETE FROM BOOK WHERE BOOKNUM=?";
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
			System.out.println("북 삭제 중 예외 발생");
		}

		return result;
	}

	// 만족도 최신화;
	public void rateRefresh(int bookNum) {
		try {
			if (conn != null) {
				conn.close();
			}
			String query = "UPDATE BOOK SET RATE=(SELECT AVG(RATE) FROM PAGE WHERE BOOKNUM=" + bookNum
					+ ") WHERE BOOKNUM=" + bookNum;

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("만족도 최신화 도중 예외 발생");
		}
	}
}
