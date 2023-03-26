package com.mapers.myPageRequest.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.myPageRequest.controller.Controller;
import com.mapers.myPageRequest.model.RequestDAO;
import com.mapers.myPageRequest.model.RequestDTO;

public class UpdatePostController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
//		if (session == null || session.getAttribute("ProfileDTO") == null) {
//			return "redirect:index.jsp";
//		}
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		RequestDTO rDTO = new RequestDTO();
		rDTO.setTitle(title);
		rDTO.setContent(content);
		
		RequestDAO.getInstance().editRequest(rDTO);
		
		String path = "redirect:/front/MyRequest?command=PostDetailNoHits&requestNum=" + rDTO.getRequestNum();
		
		return path;
	}

}
