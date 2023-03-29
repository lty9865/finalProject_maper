package com.mapers.myPage.Profile.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;
import com.mapers.myPage.Profile.model.ProfileDAO;


public class ProfileDeleteHandler implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
//		if (session == null || session.getAttribute("memberVO") == null) {
//			return "redirect:index.jsp";
//		}

		String userId = (String) session.getAttribute("userId");
		String password = request.getParameter("password");
		ProfileDAO.getInstance().deleteMember(userId, password);
		
		request.setAttribute("url", "/MyPageFront?command=home");
		
		return "redirect:/index.jsp";
	}

}
