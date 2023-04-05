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
		String userId = (String)session.getAttribute("userId");
		
		// 게시글 상세보기
		RequestDTO rDTO = RequestDAO.getInstance().viewRequest(requestNum, userId);
		
		// 상세보기 정보 공유
		request.setAttribute("requestNum", requestNum);
        request.setAttribute("rDTO", rDTO);
		session.setAttribute("userId", userId);
		// url에 상세보기 페이지로 이동
		request.setAttribute("url", "${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyRequest.requestPostView");
		
		return "/MyPage/Request/requestPostView.jsp";
	}
}
