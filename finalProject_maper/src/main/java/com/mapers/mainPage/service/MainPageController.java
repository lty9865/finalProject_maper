package com.mapers.mainPage.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.book.model.BookDTO;
import com.mapers.common.Controller;
import com.mapers.mainPage.model.MainPageDAO;
import com.mapers.notice.model.NoticeDTO;

public class MainPageController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		if (session.getAttribute("my") != null) {
			session.removeAttribute("my");
		}

		MainPageDAO dao = MainPageDAO.getInstance();
		List<BookDTO> popularBook = dao.likeListBook();
		List<BookDTO> searchBook = dao.lastListBook();
		List<NoticeDTO> mainNoticeList = dao.mainNotice();
		
		dao.close();

		request.setAttribute("popularBook", popularBook);
		request.setAttribute("searchBook", searchBook);
		request.setAttribute("mainNoticeList", mainNoticeList);
		request.setAttribute("url", "/Webmain/mainPage.do?command=main");

		return "/Webmain/mainPage.jsp";
	}

}
