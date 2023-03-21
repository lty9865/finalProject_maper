package com.mapers.notice;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/noticewrite.do")
public class NoticeWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		NoticeDTO dto = new NoticeDTO();
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("contents"));
		
		NoticeDAO dao = null;
		try {
			dao = new NoticeDAO();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int result = dao.insertNotice(dto);
		
		if(result == 1) {
			System.out.println("공지사항을 성공적으로 등록하였습니다.");
		}else {
			System.out.println("공지사항에 실패하였습니다.");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("./Boardlist/notice.jsp");
		dispatcher.forward(request, response);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/Boardwrite/noticewrite.jsp").forward(request, response);
	}
	
}
