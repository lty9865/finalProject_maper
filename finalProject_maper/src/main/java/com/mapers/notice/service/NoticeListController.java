package com.mapers.notice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapers.common.Controller;
import com.mapers.notice.model.NoticeDAO;
import com.mapers.notice.model.NoticeDTO;
import com.mapers.util.ListPage;

public class NoticeListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		NoticeDAO dao = NoticeDAO.getInstance();

		Map<String, Object> map = new HashMap<String, Object>();
		String searchField = request.getParameter("searchField");
		String searchWord = request.getParameter("searchWord");
		if (searchWord != null) {
			map.put("searchField", searchField);
			map.put("searchWord", searchWord);
		}

		int totalCount = dao.countNotice(map);

		// 페이지 처리
		ServletContext application = request.getSession().getServletContext();
		int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
		int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));

		// 현재 페이지 확인
		int pageNum = 1;
		String pageTemp = request.getParameter("pageNum");
		if (pageTemp != null && !pageTemp.equals(""))
			pageNum = Integer.parseInt(pageTemp);// 요청받은 페이지로 수정

		int start = (pageNum - 1) * pageSize + 1;
		int end = pageNum * pageSize;
		map.put("start", start);
		map.put("end", end);

		List<NoticeDTO> noticeLists = dao.noticeList(map);

		String pagingImg = ListPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "/Notice/notice.do");
		map.put("pagingImg", pagingImg);
		map.put("totalCount", totalCount);
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);

		dao.close();

		request.setAttribute("noticeLists", noticeLists);
		request.setAttribute("map", map);
		request.setAttribute("url", "/Notice/notice.do?command=list");

		return "/Notice/notice.jsp";
	}
}
