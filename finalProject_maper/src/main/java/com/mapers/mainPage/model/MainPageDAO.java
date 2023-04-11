package com.mapers.mainPage.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import javax.sql.DataSource;

import com.mapers.book.model.BookDTO;
import com.mapers.common.DataSourceManager;
import com.mapers.notice.model.NoticeDTO;

public class MainPageDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// singleton pattern
	private static MainPageDAO instance = new MainPageDAO();
	private DataSource dataSource;

	private MainPageDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}

	public static MainPageDAO getInstance() {
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

	// 메인페이지 출력할 게시물 조회수 순 조회 쿼리 3개 - 김연호
	public List<BookDTO> likeListBook() {
		List<BookDTO> likeListBook = new Vector<BookDTO>();

		String query = "SELECT * FROM ( " + " SELECT * FROM BOOK WHERE BLOCKS != 1 )" + " WHERE ROWNUM <= 3 ORDER BY "
				+ "VISITCOUNT DESC, BOOKNUM DESC";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BookDTO dto = new BookDTO();
				dto.setBookNum(rs.getInt("BOOKNUM"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setUserId(rs.getString("USERID"));
				String[] place = rs.getString("PLACE").split("/");
				dto.setCountry(place[0]);
				dto.setCity(place[1]);
				dto.setBookDate(rs.getString("BOOKDATE"));
				dto.setRate(rs.getDouble("RATE"));
				dto.setVisitCount(rs.getInt("VISITCOUNT"));
				dto.setLikesCount(rs.getInt("LIKESCOUNT"));
				dto.setOfile(rs.getString("OFILE"));
				dto.setSfile(rs.getString("SFILE"));

				likeListBook.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("메인페이지 리스트 조회 시 예외 발생");
		}
		return likeListBook;
	}

	// 메인페이지 출력할 게시물 최신작성 순 조회 쿼리 5개 - 김연호
	public List<BookDTO> lastListBook() {
		List<BookDTO> lastListBook = new Vector<BookDTO>();

		String query = "SELECT * FROM ( " + " SELECT * FROM BOOK WHERE BLOCKS != 1 )" + " WHERE ROWNUM <= 3 ORDER BY "
				+ "BOOKDATE DESC, BOOKNUM DESC";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BookDTO dto = new BookDTO();
				dto.setBookNum(rs.getInt("BOOKNUM"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setUserId(rs.getString("USERID"));
				String[] place = rs.getString("PLACE").split("/");
				dto.setCountry(place[0]);
				dto.setCity(place[1]);
				dto.setBookDate(rs.getString("BOOKDATE"));
				dto.setRate(rs.getDouble("RATE"));
				dto.setVisitCount(rs.getInt("VISITCOUNT"));
				dto.setLikesCount(rs.getInt("LIKESCOUNT"));
				dto.setOfile(rs.getString("OFILE"));
				dto.setSfile(rs.getString("SFILE"));

				lastListBook.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("메인페이지 리스트 조회 시 예외 발생");
		}
		return lastListBook;
	}

	// 메인페이지 공지사항 최신 글 5개 조회 - 김연호
	public List<NoticeDTO> mainNotice() {
		List<NoticeDTO> mainNoticeList = new Vector<NoticeDTO>();

		String query = "SELECT * FROM (SELECT * FROM NOTICE ORDER BY POSTDATE DESC) WHERE ROWNUM <= 3 ORDER BY NOTICENUM DESC";

		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				NoticeDTO dto = new NoticeDTO();

				dto.setIdx(rs.getInt("NOTICENUM"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setPostDate(rs.getString("POSTDATE"));
				dto.setVisitCount(rs.getInt("VISITCOUNT"));

				mainNoticeList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("메인페이지 공지사항 5개 조회 시 예외 발생");
		}
		return mainNoticeList;
	}
}
