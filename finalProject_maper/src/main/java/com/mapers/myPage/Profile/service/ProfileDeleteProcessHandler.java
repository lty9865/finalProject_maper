package com.mapers.myPage.Profile.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;
import com.mapers.myPage.Profile.model.ProfileDAO;

public class ProfileDeleteProcessHandler implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		int result = ProfileDAO.getInstance().deleteMember(userId, password);
		
		if (result > 0) {
			request.setAttribute("msg", "계정이 삭제되었습니다. 메인 페이지로 이동합니다.");
			
			HttpSession session = request.getSession();
			session.setAttribute("userId", null);
			
			return "/Common/logOutProcess.jsp";
		} else {
			request.setAttribute("msg", "계정 삭제 중 오류가 발생했습니다. 잠시 후 다시 시도해주십시오.");
			
			return "/MyPage/myPage.jsp";
		}
		
	}
}
