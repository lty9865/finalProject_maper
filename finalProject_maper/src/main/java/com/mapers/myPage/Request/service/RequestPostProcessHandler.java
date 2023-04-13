package com.mapers.myPage.Request.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;
import com.mapers.myPage.Request.model.RequestDAO;
import com.mapers.myPage.Request.model.RequestDTO;

public class RequestPostProcessHandler implements Controller {
	private static final int RECORDS_PER_PAGE = 10;
	
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
		RequestDAO rDAO = RequestDAO.getInstance();
		
		rDAO.insertRequest(rDTO);
		
		Integer totalPostCount = (Integer) session.getAttribute("totalPostCount");

        String pageStr = request.getParameter("page");
        int page = 1;

        if (pageStr != null && !pageStr.isEmpty()) {
            page = Integer.parseInt(pageStr);
        }

        int currentTotalCount = rDAO.getTotalPostCount();
        if (totalPostCount == null || totalPostCount != currentTotalCount) {
            session.setAttribute("totalPostCount", currentTotalCount);
        }

        int pageNo = page;
        int postsPerPage = RECORDS_PER_PAGE;

        List<RequestDTO> rList = rDAO.getAllList(pageNo, postsPerPage, userId);
        int totalPages = (int) Math.ceil((double) currentTotalCount / postsPerPage);
        
        request.setAttribute("requestList", rList);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("page", page);

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setHeader("Expires", "0"); // Proxies
		
		// 4. 문의 글 작성 완료 후, 마이 페이지의 문의 게시판으로 돌아가기
		String url = "${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyRequest";
		request.setAttribute("url", url);
		
		return "/MyPage/Request/requestList.jsp";
	}

}
