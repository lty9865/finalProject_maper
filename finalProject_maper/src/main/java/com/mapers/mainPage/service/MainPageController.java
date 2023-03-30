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
		MainPageDAO dao = MainPageDAO.getInstance();
		
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("userId"));

		String like = "LIKESCOUNT";
		String last = "BOOKDATE";
		List<BookDTO> popularBook = dao.searchBook(like);
		List<BookDTO> searchBook = dao.searchBook(last);
		List<NoticeDTO> mainNoticeList = dao.mainNotice();

		dao.close();

		request.setAttribute("popularBook", popularBook);
		request.setAttribute("searchBook", searchBook);
		request.setAttribute("mainNoticeList", mainNoticeList);
		request.setAttribute("url", "/Webmain/mainPage.do?command=main");

		return "/Webmain/mainPage.jsp";
	}

}
