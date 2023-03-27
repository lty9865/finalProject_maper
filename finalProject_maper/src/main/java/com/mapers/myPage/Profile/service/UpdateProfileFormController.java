package com.mapers.myPage.Profile.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.myPage.Profile.controller.Controller;
import com.mapers.myPage.Profile.model.ProfileDAO;
import com.mapers.myPage.Profile.model.ProfileDTO;


public class UpdateProfileFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
//		if (session == null || session.getAttribute("ProfileDTO") == null) {
//			return "redirect:index.jsp";
//		}
		
		String userId = (String) session.getAttribute("userId");
		String password = request.getParameter("password");
		ProfileDTO pDTO = ProfileDAO.getInstance().getMember(userId, password);
		
		request.setAttribute("rDTO", pDTO);
		request.setAttribute("url", "/MyPage.Request/requestEdit.jsp");
		
		return "/MyPage.Common/layout.jsp";
	}

}

