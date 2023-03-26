package com.mapers.myPageProfile.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.myPageProfile.controller.Controller;
import com.mapers.myPageProfile.model.ProfileDAO;


public class RemoveMemberController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
//		if (session == null || session.getAttribute("memberVO") == null) {
//			return "redirect:index.jsp";
//		}

		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		ProfileDAO.getInstance().deleteMember(userId, password);
		
		return "redirect:/front/MyRequest?command=home";
	}

}
