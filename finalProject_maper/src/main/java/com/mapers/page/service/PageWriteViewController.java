package com.mapers.page.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapers.common.Controller;

public class PageWriteViewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("bookNum", request.getParameter("idx"));
		request.setAttribute("title", request.getParameter("title"));
		request.setAttribute("url", "/Page/page.do?command=pageWriteView&idx=${ dto.bookNum }&title=${ dto.title }");
		
		return "/Page/pageWrite.jsp";
	}

}
