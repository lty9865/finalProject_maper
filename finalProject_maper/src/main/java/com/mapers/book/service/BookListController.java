package com.mapers.book.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.book.model.BookDAO;
import com.mapers.book.model.BookDTO;
import com.mapers.common.Controller;
import com.mapers.util.ListPage;

public class BookListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BookDAO dao = BookDAO.getInstance();

		Map<String, Object> map = new HashMap<String, Object>();

		String searchField = request.getParameter("searchField");
		String searchWord = request.getParameter("searchWord");
		if (searchWord != null) {
			map.put("searchField", searchField);
			map.put("searchWord", searchWord);
		}
		HttpSession session = request.getSession();

		String mode = request.getParameter("mode");
		String userId = null;
		if( mode != null) {
			if (mode.equals("my")) {
				userId = (String) session.getAttribute("userId");
				session.setAttribute("my", "my");
			}
		}

		int totalCount = dao.countBook(map, userId);

		// 페이지 처리
		ServletContext application = request.getSession().getServletContext();
		int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
		int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));

		// 현재 페이지 확인
		int pageNums = 1;
		String pageTemp = request.getParameter("pageNums");
		if (pageTemp != null && !pageTemp.equals(""))
			pageNums = Integer.parseInt(pageTemp);

		int start = (pageNums - 1) * pageSize + 1;
		int end = pageNums * pageSize;
		map.put("start", start);
		map.put("end", end);

		List<BookDTO> bookList = dao.selectBookList(map, userId);

		String pagingImg = ListPage.pagingStr(totalCount, pageSize, blockPage, pageNums,
				"Book/book.do?command=bookList");
		map.put("pagingImg", pagingImg);
		map.put("totalCount", totalCount);
		map.put("pageSize", pageSize);
		map.put("pageNums", pageNums);

		request.setAttribute("bookList", bookList);
		request.setAttribute("map", map);
		request.setAttribute("url", "/Book/book.do?command=bookList");

		if (request.getSession().getAttribute("bookDTO") != null) {
			request.getSession().removeAttribute("bookDTO");
		}
		if (request.getSession().getAttribute("allow") != null) {
			request.getSession().removeAttribute("allow");
		}

		return "/Book/book.jsp";
	}
}
