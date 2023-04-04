package com.mapers.page.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapers.common.Controller;
import com.mapers.page.model.PageDAO;
import com.mapers.page.model.PageDTO;

public class PageEditViewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idx = Integer.parseInt(request.getParameter("idx"));
		String title = request.getParameter("title");
		PageDAO dao = PageDAO.getInstance();
		PageDTO dto = dao.selectPageView(idx);
		request.setAttribute("dto", dto);
		request.setAttribute("url", "/Page/page.do?command=pageEditView&idx=" + idx + "&title=" + title);
		return "/Page/pageEdit.jsp";
	}
}
