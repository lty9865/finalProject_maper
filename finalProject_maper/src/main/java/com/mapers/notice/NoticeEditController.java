package com.mapers.notice;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/NoticeEditController")
public class NoticeEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idx = request.getParameter("idx");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		NoticeDTO dto = new NoticeDTO();
		dto.setIdx(Integer.parseInt(idx));
		dto.setTitle(title);
		dto.setContent(content);
		
		NoticeDAO dao = null;
		try {
			dao = new NoticeDAO();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//		int result = dao.u
	}

}
