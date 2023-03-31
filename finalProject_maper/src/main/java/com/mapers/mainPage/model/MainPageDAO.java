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

	// 만족도 값 조회 - 김연호
	public double bookRate(int bookNum) {
		double result = 0.0;
		try {
			String query = "SELECT AVG(RATE) AS RATE , BOOKNUM FROM PAGE WHERE BOOKNUM=" + bookNum + "GROUP BY BOOKNUM";
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getDouble("RATE");
			}

			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("만족도 평균 값 조회 중 예외 발생");
		}
		return result;
	}

	// 메인페이지 출력할 게시물 조회 쿼리 5개 - 김연호
	public List<BookDTO> searchBook(String div) {
		List<BookDTO> searchBook = new Vector<BookDTO>();

		String query = "SELECT * FROM ( " + " SELECT * FROM BOOK WHERE BLOCKS != 1 )" + " WHERE ROWNUM <= 5 ORDER BY "
				+ div + " DESC, BOOKNUM DESC";
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

				// 만족도
				dto.setRate(bookRate(rs.getInt("BOOKNUM")));

				dto.setVisitCount(rs.getInt("VISITCOUNT"));
				dto.setLikesCount(rs.getInt("LIKESCOUNT"));
				dto.setOfile(rs.getString("OFILE"));
				dto.setSfile(rs.getString("SFILE"));

				searchBook.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("메인페이지 리스트 조회 시 예외 발생");
		}
		return searchBook;
	}

	// 메인페이지 공지사항 최신 글 5개 조회 - 김연호
	public List<NoticeDTO> mainNotice() {
		List<NoticeDTO> mainNoticeList = new Vector<NoticeDTO>();

		String query = "SELECT * FROM (SELECT * FROM NOTICE ORDER BY POSTDATE DESC) WHERE ROWNUM <= 5 ORDER BY NOTICENUM DESC";

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
