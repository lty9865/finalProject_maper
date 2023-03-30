package com.mapers.book.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapers.book.model.BookDAO;
import com.mapers.book.model.BookDTO;

@WebServlet("/Book/bookView.do")
public class BookViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BookDAO dao = BookDAO.getInstance();
		
		String idx = req.getParameter("idx");
		dao.updateBookVisitCount(idx);
		BookDTO dto = dao.selectBook(idx);
		dao.close();
		
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/Book/bookView.jsp").forward(req, resp);
	}
}
