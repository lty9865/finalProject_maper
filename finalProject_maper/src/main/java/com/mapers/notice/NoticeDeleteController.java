package com.mapers.notice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Notice/noticedelete.do")
public class NoticeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idx = request.getParameter("idx");
		String mode = request.getParameter("mode");
		
		if(mode.equals("delete")) {
			NoticeDAO dao = NoticeDAO.getInstance();
			int result = dao.deleteNotice(idx);
			
			if(result == 1) {
				System.out.println("공지사항 삭제를 성공하였습니다.");
				response.sendRedirect("../Notice/notice.do");
			}
		}
		
	}

}
