package com.mapers.myPage.Request.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;
import com.mapers.myPage.Request.model.RequestDAO;
import com.mapers.myPage.Request.model.RequestDTO;

public class RequestPostViewHandler implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		int requestNum = Integer.parseInt(request.getParameter("requestNum"));
		
		String userId = (String) session.getAttribute("userId");
		String[] userIdPart = userId.split("_");
//		int adminCon = Integer.parseInt(userState[1]);
		// 0 : 일반 회원, 1: 관리자
		
		session.setAttribute("userId", userId);
		if (userId == null || userId.isEmpty()) {
			userId = "not admins";
		}
        System.out.println(userId);
        
		
		// 게시글 상세보기
		RequestDTO rDTO = RequestDAO.getInstance().viewRequest(requestNum, userIdPart[0]);
		
		// 상세보기 정보 공유
		request.setAttribute("requestNum", requestNum);
        request.setAttribute("rDTO", rDTO);
		session.setAttribute("userId", userId);
		// url에 상세보기 페이지로 이동
		request.setAttribute("url", "${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyRequest.requestPostView");
		request.setAttribute("selectedMenuItem", "MyRequest");
		
		return "/MyPage/Request/requestPostView.jsp";
	}
}
