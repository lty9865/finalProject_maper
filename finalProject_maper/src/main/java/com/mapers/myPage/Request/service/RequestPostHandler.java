package com.mapers.myPage.Request.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;

public class RequestPostHandler implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		String userId = (String) session.getAttribute("userId");
		session.setAttribute("userId", userId);
		
		request.setAttribute("selectedMenuItem", "MyRequest");
		
		return "/MyPage/Request/requestPost.jsp";
	}

}
