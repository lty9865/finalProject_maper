package com.mapers.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import com.mapers.common.DataSourceManager;

public class ReportDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private static ReportDAO instance = new ReportDAO();
	private DataSource dataSource;

	private ReportDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}

	public static ReportDAO getInstance() {
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

	// 해당 북 신고 리스트 개수 세기 - 김연호
	public int countReport(int bookNum) {
		int totalCount = 0;

		String query = "SELECT COUNT(*) FROM REPORT WHERE BOOKNUM=?";

		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookNum);
			rs = pstmt.executeQuery();
			rs.next();
			totalCount = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("신고리스트 조회 중 예외 발생");
		}
		return totalCount;
	}

	// 신고 기능 중복조회 - 김연호
	public int searchReport(int bookNum, String userId) {
		String query = "SELECT COUNT(*) FROM REPORT WHERE BOOKNUM=? AND USERID=?";
		int result = 0;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookNum);
			pstmt.setString(2, userId);
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("중복 신고 게시물 조회 중 예외 발생");
		}

		return result;
	}

	// 신고 기능 update - 김연호
	public int updateReport(int bookNum, String userId) {
		int result = 0;
		String query = "UPDATE REPORT SET REPORTCOUNT=REPORTCOUNT+1 WHERE BOOKNUM=? AND USERID=?";

		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("신고 카운트 +1 시 예외 발생");
		}

		return result;
	}

	// 신고 기능 insert - 김연호
	public int insertReport(int bookNum, String userId) {
		int result = 0;
		String query = "INSERT INTO REPORT(REPORTNUM,USERID,BOOKNUM)" + " VALUES(C##MAPERS.REPORT_SEQ.NEXTVAL,?,?)";

		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setInt(2, bookNum);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("신고 추가 중 예외 발생");
		}
		return result;
	}

}
