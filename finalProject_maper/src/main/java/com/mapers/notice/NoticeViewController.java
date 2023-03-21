package com.mapers.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Notice/noticeview.do")
public class NoticeViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeDAO dao = NoticeDAO.getInstance();
		
		String idx = request.getParameter("idx");
		dao.updateVisitCountNotice(idx);
		NoticeDTO dto = dao.viewNotice(idx);
		
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));
		
		request.setAttribute("dto", dto);
		request.getRequestDispatcher("/Notice/noticeview.jsp").forward(request, response);
	}

}
