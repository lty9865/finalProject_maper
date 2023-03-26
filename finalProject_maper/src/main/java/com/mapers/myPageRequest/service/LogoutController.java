package com.mapers.myPageRequest.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.myPageRequest.controller.Controller;

public class LogoutController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		
//		if (session != null && session.getAttribute("memberVO")!= null) {
//			session.invalidate();
//		}
		
		return "redirect:index.jsp";
	}

}
