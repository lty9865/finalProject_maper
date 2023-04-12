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

		String userId = (String) session.getAttribute("userId");
		String password = request.getParameter("password");
		
		if (userId == null || userId.isEmpty()) {
		    userId = "test1";
		}
		if (password == null || password.isEmpty()) {
		    password = "pass";
		}
		
		ProfileDAO.getInstance().deleteMember(userId, password);
		
		request.setAttribute("url", "${pageContext.request.contextPath}/Main/MyPageFront?command=home");
		request.setAttribute("selectedMenuItem", "MyProfile");
		
		return "redirect:/Webmain/mainpage.jsp";
	}

}
