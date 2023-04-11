package com.mapers.myPage.Request.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;
import com.mapers.myPage.Request.model.RequestDAO;
import com.mapers.myPage.Request.model.RequestDTO;

public class RequestPostProcess implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);

        String sessionUserId = (String) session.getAttribute("userId");
        session.setAttribute("userId", sessionUserId);
		
		/** 게시글 등록 **/
		// 1. requestPost.jsp에서 title, userId, content 받아옴
		String title = request.getParameter("title");
		String userId = request.getParameter("userId");
		String content = request.getParameter("content");
		
		System.out.println("Title: " + title + ", UserId: " + userId + ", Content: " + content);
		
		Date postDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String postDateString = dateFormat.format(postDate);
		
		// 2. RequestDTO에 새로 입력받은 글로 새로운 RequestDTO 만듦
		RequestDTO rDTO = new RequestDTO();
		rDTO.setTitle(title);
		rDTO.setContent(content);
		rDTO.setUserId(userId);
		rDTO.setPostDate(postDateString);
		
		// 3. 로그인 정보와 글 정보를 담은 requestDTO를 작성
		// 이 과정에서 자동으로 no와 date가 requestDTO에 입력됨
		RequestDAO.getInstance().insertRequest(rDTO);
		
		// 4. 문의 글 작성 완료 후, 마이 페이지의 문의 게시판으로 돌아가기
		String url = "${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyRequest";
		request.setAttribute("url", url);
		
		return "/MyPage/Request/requestList.jsp";
	}

}
