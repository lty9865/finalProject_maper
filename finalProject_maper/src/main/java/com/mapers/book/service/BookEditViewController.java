package com.mapers.book.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapers.book.model.BookDAO;
import com.mapers.book.model.BookDTO;
import com.mapers.common.Controller;

public class BookEditViewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String idx = (String)request.getSession().getAttribute("bookNum");
		BookDAO dao = BookDAO.getInstance();
		BookDTO dto = dao.selectBook(idx);
		request.setAttribute("dto", dto);
		request.setAttribute("url", "/Book/book.do?command=bookEditView");

		return "/Book/bookEdit.jsp";
	}

}
