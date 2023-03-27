package com.mapers.myPage.Request.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapers.myPage.Request.controller.Controller;
import com.mapers.myPage.Request.model.ListDTO;
import com.mapers.myPage.Request.model.PagingBean;
import com.mapers.myPage.Request.model.RequestDAO;
import com.mapers.myPage.Request.model.RequestDTO;

public class HomeController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		// 1. 전체 포스팅의 개수를 받아옴
		int totalPostCount = RequestDAO.getInstance().getTotalPostCount();
		
		// 2. 현재 페이지 번호를 받아옴
		String pageNo = request.getParameter("pageNo");
		PagingBean pagingBean = null;
		
		// 3. 만약 페이지 번호가 null이면 -> PagingBean(전체 포스팅 개수) 객체 생성
		if (pageNo == null) {
			pagingBean = new PagingBean(totalPostCount);
		} else { // null이 아니면 -> PagingBean(전체 포스팅 개수, 현재 페이지 번호) 객체 생성
			pagingBean = new PagingBean(totalPostCount, Integer.parseInt(pageNo));
		}
		
		// 4. getAllList(PagingBean)과 연동하여
		// 페이지에 맞는 포스팅 5개 리스트 리턴
		ArrayList<RequestDTO> rList = RequestDAO.getInstance().getAllList(pagingBean);
		
		// + ListDTO list와 PagingBean 넣어 객체 생성
		ListDTO listDTO = new ListDTO(rList, pagingBean);
		
		// 5. request에 담아 ListDTO 공유
		request.setAttribute("lvo", listDTO);
		
		// 6. request에 담아 url 공유(requestList.jsp) - layout으로 리턴
		request.setAttribute("url", "/MyPage.Request/requestList.jsp");
		
		return "/MyPageCommon/layout.jsp";
	}

}
