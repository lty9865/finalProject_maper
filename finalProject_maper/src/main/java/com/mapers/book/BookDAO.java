package com.mapers.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BookDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private BookDAO() {
	}

	private static BookDAO instance = new BookDAO();

	public static BookDAO getInstance() {
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
	
	// 북리스트 개수 세기 - 김연호
	public int countBook(Map<String, Object> map) {
		int totalCount = 0;
		
		String query = "SELECT COUNT(*) FROM BOOK";
		if(map.get("searchWord")!=null) {
			query += " WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%'";
		}
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			rs.next();
			totalCount = rs.getInt(1);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("북리스트 개수 세기 중 예외 발생");
		}
		return totalCount;
	}
	
	// 검색조건에 맞는 게시물 목록 반환(페이징)
	public List<BookDTO> selectBookList(Map<String, Object> map){
		List<BookDTO> bookList = new Vector<BookDTO>();
		
		String query = " "
				+ "SELECT * FROM ( "
				+ "	SELECT Tb.*, ROWNUM rNum FROM ( "
				+ "  SELECT * FROM BOOK";
		// 검색 조건이 있다면 where 조건 추가
		if(map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField")
				  + " LIKE '%" + map.get("searchWord") + "%' ";
		}
		
		query += "   ORDER BY BOOKNUM DESC"
			  +  "  ) Tb "
			  +  " ) "
			  +  " WHERE rNum BETWEEN ? AND ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, map.get("start").toString());
			pstmt.setString(2, map.get("end").toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BookDTO dto = new BookDTO();
				
				dto.setBookNum(rs.getInt("BOOKNUM"));
				dto.setUserId(rs.getString("USERID"));
				dto.setPlace(rs.getString("PLACE"));
				dto.setBookDate(rs.getString("BOOKDATE"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setBlock(rs.getInt("BLOCKS"));
				dto.setRate(rs.getDouble("RATE"));
				dto.setVisitCount(rs.getInt("VISITCOUNT"));
				dto.setLikesCount(rs.getInt("LIKESCOUNT"));
				dto.setReportCount(rs.getInt("REPORTCOUNT"));
				dto.setOfile(rs.getString("OFILE"));
				dto.setSfile(rs.getString("SFILE"));
				
				bookList.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("북리스트 게시물 조회 중 예외 발생");
		}
		return bookList;
	}
	
	// 북리스트 작성 DB Insert - 김연호
	public int insertBook(BookDTO dto) {
		int result = 0;
		String query = "INSERT INTO BOOK(BOOKNUM,USERID,PLACE,BOOKDATE,TITLE,SFILE,OFILE) "
				+ "VALUES(C##MAPERS.BOOK_SEQ.NEXTVAL,?,?,?,?,?,?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getUserId());
			pstmt.setString(2, dto.getPlace());
			pstmt.setString(3, dto.getBookDate());
			pstmt.setString(4, dto.getTitle());
			pstmt.setString(5, dto.getSfile());
			pstmt.setString(6, dto.getOfile());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("북 작성 중 예외 발생");
		}
		return result;
	}
	
	// 북리스트 중 원하는 북 선택 - 김연호
	public BookDTO selectBook(String idx) {
		BookDTO dto = new BookDTO();
		String query = "SELECT * FROM BOOK WHERE BOOKNUM=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, idx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setBookNum(rs.getInt("BOOKNUM"));
				dto.setUserId(rs.getString("USERID"));
				dto.setPlace(rs.getString("PLACE"));
				dto.setBookDate(rs.getString("BOOKDATE"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setBlock(rs.getInt("BLOCKS"));
				dto.setRate(rs.getDouble("RATE"));
				dto.setVisitCount(rs.getInt("VISITCOUNT"));
				dto.setLikesCount(rs.getInt("LIKESCOUNT"));
				dto.setReportCount(rs.getInt("REPORTCOUNT"));
				dto.setSfile(rs.getString("SFILE"));
				dto.setOfile(rs.getString("OFILE"));
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("북 상세보기 중 예외 발생");
		}
		
		return dto;
	}
	
	public void updateBookVisitCount(String idx) {
		String query = "UPDATE BOOK SET "
				+ " VISITCOUNT = VISITCOUNT+1 "
				+ " WHERE BOOKNUM=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, idx);
			pstmt.executeQuery();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("북 조회수 1증가 도중 예외 발생");
		}
	}
	
	// 북리스트 삭제
	public int deleteBook(String idx) {
		int result = 0;
		String query = "DELETE FROM BOOK WHERE BOOKNUM=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, idx);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("북 삭제 중 예외 발생");
		}
		return result;
	}
}
