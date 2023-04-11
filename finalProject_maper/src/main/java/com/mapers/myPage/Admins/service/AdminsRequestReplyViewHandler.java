package com.mapers.myPage.Admins.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;
import com.mapers.myPage.Request.model.RequestDAO;
import com.mapers.myPage.Request.model.RequestDTO;

public class AdminsRequestReplyViewHandler implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		request.setAttribute("selectedMenuItem", "AdminsRequestBoard");
		
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
        String[] userIdPart = userId.split("_");
        String userIdFront = userIdPart[0];
        if (userIdFront == null || userIdFront.isEmpty()) {
        	userIdFront = "admins";
        }
        int adminCon = Integer.parseInt(userIdPart[1]);
        if (adminCon != 1) {
        	adminCon = 1;
        }
        session.setAttribute("userId", userId);
        
		int requestNum = Integer.parseInt(request.getParameter("requestNum"));
		
		// 게시글 상세보기
		RequestDTO rDTO = RequestDAO.getInstance().viewRequest(requestNum, userIdFront);
		
		// 상세보기 정보 공유
		request.setAttribute("requestNum", requestNum);
        request.setAttribute("rDTO", rDTO);
		// url에 상세보기 페이지로 이동
		request.setAttribute("url", "${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyRequest.requestPostView");
		
		return "/MyPage/Request/requestPostView.jsp";
	}
}
