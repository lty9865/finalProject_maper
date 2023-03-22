package com.mapers.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Notice/noticeedit.do")
public class NoticeEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idx = request.getParameter("idx");
		NoticeDAO dao = NoticeDAO.getInstance();
		NoticeDTO dto = dao.viewNotice(idx);
		request.setAttribute("dto", dto);
		request.getRequestDispatcher("/Notice/noticeedit.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String idx = request.getParameter("idx");
		String title = request.getParameter("title");
		String content = request.getParameter("contents");
		
		NoticeDTO dto = new NoticeDTO();
		dto.setIdx(Integer.parseInt(idx));
		dto.setTitle(title);
		dto.setContent(content);
		
		NoticeDAO dao = NoticeDAO.getInstance();
		
		int result = dao.updateNotice(dto);
		dao.close();
		
		if(result == 1) {
			System.out.println("공지사항 수정에 성공하였습니다.");
			response.sendRedirect("../Notice/notice.do");
		}else {
			System.out.println("공지사항 수정에 실패하였습니다.");
		}
	}

}
