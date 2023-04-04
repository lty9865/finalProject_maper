package com.mapers.page.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapers.book.model.BookDTO;
import com.mapers.common.Controller;
import com.mapers.page.model.PageDAO;
import com.mapers.page.model.PageDTO;
import com.mapers.util.ListPage;

public class PageListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PageDAO dao = PageDAO.getInstance();

		Map<String, Object> map = new HashMap<String, Object>();

		String searchField = request.getParameter("searchField");
		String searchWord = request.getParameter("searchWord");
		BookDTO dto = (BookDTO)request.getSession().getAttribute("bookDTO");
		int idx = dto.getBookNum();
		map.put("idx", idx);
		if (searchWord != null) {
			map.put("searchField", searchField);
			map.put("searchWord", searchWord);
		}
		int totalCount = dao.pageCount(map);

		ServletContext application = request.getSession().getServletContext();
		int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
		int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
		

		int pageNums = 1;
		String pageTemp = request.getParameter("pageNums");
		if (pageTemp != null && !pageTemp.equals("")) {
			pageNums = Integer.parseInt(pageTemp);
		}

		int start = (pageNums - 1) * pageSize + 1;
		int end = pageNums * pageSize;
		map.put("start", start);
		map.put("end", end);

		List<PageDTO> pageList = dao.selectPageList(map);
		
		String pagingImg = ListPage.pagingStr(totalCount, pageSize, blockPage, pageNums, "Page/page.do?command=pageList");
		map.put("pagingImg", pagingImg);
		map.put("totalCount", totalCount);
		map.put("pageSize", pageSize);
		map.put("pageNums", pageNums);

		dao.close();
		
		request.setAttribute("pageList", pageList);
		request.setAttribute("title", request.getAttribute("title"));
		request.setAttribute("map", map);
		request.setAttribute("url", "/Page/page.do?command=pageList");
		
		return "/Page/pageList.jsp";
	}

}
