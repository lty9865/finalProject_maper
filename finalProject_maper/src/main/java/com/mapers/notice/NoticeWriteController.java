package com.mapers.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Notice/noticewrite.do")
public class NoticeWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		NoticeDTO dto = new NoticeDTO();
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("contents"));
		
		NoticeDAO dao = NoticeDAO.getInstance();
		int result = dao.insertNotice(dto);
		
		if(result == 1) {
			System.out.println("공지사항을 성공적으로 등록하였습니다.");
			response.sendRedirect("../Notice/notice.do");
		}else {
			System.out.println("공지사항에 실패하였습니다.");
			response.sendRedirect("../Notice/noticewrite.do");
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/Notice/noticewrite.jsp").forward(request, response);
	}
	
}