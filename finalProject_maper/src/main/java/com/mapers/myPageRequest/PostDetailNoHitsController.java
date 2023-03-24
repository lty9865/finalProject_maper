package com.mapers.myPageRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PostDetailNoHitsController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		if(session==null||session.getAttribute("ProfileDTO")==null){
			return "redirect:index.jsp";
		}
		
		// WritePostController에서 보낸 no 받음
		int no = Integer.parseInt(request.getParameter("no"));
		String userId = request.getParameter("userId");
		// 조회수 증가
		
		// 게시글 상세보기
		RequestDTO rDTO = RequestDAO.getInstance().viewRequest(no, userId);
		
		// 상세보기 정보 공유
		request.setAttribute("rDTO", rDTO);
		// url에 상세보기 페이지로 이동
		request.setAttribute("url", "/MyPage.Request/requestView-detail.jsp");
		
		return "/MyPage.Common/layout.jsp";
		
	}
	
}
