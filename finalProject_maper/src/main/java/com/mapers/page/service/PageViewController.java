package com.mapers.page.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapers.common.Controller;
import com.mapers.page.model.PageDAO;
import com.mapers.page.model.PageDTO;

public class PageViewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PageDAO dao = PageDAO.getInstance();

		int idx = Integer.parseInt(request.getParameter("idx"));

		List<PageDTO> pageList = dao.pageViewList(idx);

		request.setAttribute("pageList", pageList);
		request.setAttribute("url", "/Page/page.do?command=pageView&idx=" + idx);
		
		dao.close();

		return "/Page/pageView.jsp";
	}
}
