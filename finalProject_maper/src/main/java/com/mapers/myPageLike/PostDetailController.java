package com.mapers.myPageLike;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PostDetailController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		
		if(session==null||session.getAttribute("ProfileDTO")==null){
			return "redirect:index.jsp";
		}
		
		int no = Integer.parseInt(request.getParameter("no"));
		String userId = request.getParameter("userId");
		// 조회수 증가
//		RequestDAO.getInstance().addHits("no");
		// 게시글 상세정보
		LikeDTO rDTO = LikeDAO.getInstance().viewRequest(no, userId);
		
		// 상세 정보 공유
		request.setAttribute("rDTO", rDTO);
		// url에 상세보기 페이지로 이동
		request.setAttribute("url", "/MyPage.Request/requestView-detail.jsp");
		
		return "/MyPage.Common/layout.jsp";
	}

}
