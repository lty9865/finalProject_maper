package com.mapers.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ReportDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private ReportDAO() {
	}

	private static ReportDAO instance = new ReportDAO();

	public static ReportDAO getInstance() {
		return instance;
	}

	public Connection getConnection() throws Exception {
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
		conn = ds.getConnection();
		return conn;
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

	// 신고 리스트 개수 세기 - 김연호
	public int countReport() {
		int totalCount = 0;

		String query = "SELECT COUNT(*) FROM REPORT";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
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
	public void searchReport(ReportDTO dto) {
		String query = "SELECT * FROM REPORT WHERE BOOKNUM=? AND USERID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, dto.getBookNum());
			pstmt.setString(2, dto.getUserId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				updateReport(dto);
			} else {
				insertReport(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("중복 신고 게시물 조회 중 예외 발생");
		}
	}

	// 신고 기능 update - 김연호
	public int updateReport(ReportDTO dto) {
		int result = 0;
		String query = "UPDATE REPORT SET REPORTCOUNT=REPORTCOUNT+1 WHERE BOOKNUM=? AND USERID=?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("신고 카운트 +1 시 예외 발생");
		}

		return result;
	}

	// 신고 기능 insert - 김연호
	public int insertReport(ReportDTO dto) {
		int result = 0;
		String query = "INSERT INTO REPORT(REPORTNUM,USERID,BOOKNUM)" + " VALUES(C##MAPERS.REPORT_SEQ.NEXTVAL,?,?)";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getUserId());
			pstmt.setInt(2, dto.getBookNum());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("신고 추가 중 예외 발생");
		}
		return result;
	}

}
