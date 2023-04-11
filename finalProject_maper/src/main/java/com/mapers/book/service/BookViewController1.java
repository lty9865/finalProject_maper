package com.mapers.book.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.book.model.BookDAO;
import com.mapers.book.model.BookDTO;
import com.mapers.common.Controller;

public class BookViewController1 implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BookDAO dao = BookDAO.getInstance();

		HttpSession session = request.getSession();

		int idx = Integer.parseInt(request.getParameter("idx"));
		dao.updateBookVisitCount(idx);
		BookDTO dto = dao.selectBook(idx);

		String userId = (String) session.getAttribute("userId");
		if (userId != null) {
			if (userId.equals(dto.getUserId())) {
				session.setAttribute("allow", 1);
			}
		}

		int likeCheck = dao.likeCheck(idx, userId);

		request.getSession().setAttribute("bookDTO", dto);
		request.getSession().setAttribute("likeCheck", likeCheck);
		request.setAttribute("url", "/Book/book.do?command=bookView&idx=" + idx);
		
		dao.close();

		return "/Book/bookView.jsp";
	}
}
