package com.mapers.myPageRequest.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.myPageRequest.controller.Controller;

public class WritePostFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		
//		if(session==null||session.getAttribute("ProfileDTO")==null){
//			return "redirect:index.jsp";
//		}
		
		request.setAttribute("url", "/MyPage.Request/requestPost.jsp");
		
		return "/MyPage.Common/layout.jsp";
	}

}
