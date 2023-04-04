package com.mapers.book.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapers.book.model.BookDAO;
import com.mapers.book.model.BookDTO;
import com.mapers.common.Controller;

public class BookViewController1 implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BookDAO dao = BookDAO.getInstance();

		String idx = request.getParameter("idx");
		dao.updateBookVisitCount(idx);
		BookDTO dto = dao.selectBook(idx);
		dao.close();

		request.setAttribute("dto", dto);
		request.setAttribute("url", "/Book/book.do?command=bookView&idx=" + idx);
		
		request.getSession().setAttribute("bookNum", idx);
		request.getSession().setAttribute("title", dto.getTitle());
		
		return "/Book/bookView.jsp";
	}
}
