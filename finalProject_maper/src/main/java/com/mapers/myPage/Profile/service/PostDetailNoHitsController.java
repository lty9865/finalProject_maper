package com.mapers.myPage.Profile.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.myPage.Profile.controller.Controller;
import com.mapers.myPage.Profile.model.ProfileDAO;
import com.mapers.myPage.Profile.model.ProfileDTO;


public class PostDetailNoHitsController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
//		if(session==null||session.getAttribute("ProfileDTO")==null){
//			return "redirect:index.jsp";
//		}
		
		// session에 저장된 아이디를 받아오고, 
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		// 조회수 증가
		
		// 프로필 상세보기
		ProfileDTO pDTO = ProfileDAO.getInstance().getMember(userId, password);
		
		// 프로필 정보 공유
		request.setAttribute("pDTO", pDTO);
		// url에 상세보기 페이지로 이동
		request.setAttribute("url", "/MyPage.Request/requestView-detail.jsp");
		
		return "/MyPage.Common/layout.jsp";
		
	}
	
}
