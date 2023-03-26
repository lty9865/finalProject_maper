package com.mapers.myPageRequest.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.myPageRequest.controller.Controller;
import com.mapers.myPageRequest.model.RequestDAO;

public class RemovePostController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
//		if (session == null || session.getAttribute("memberVO") == null) {
//			return "redirect:index.jsp";
//		}
		
		String requestNum = request.getParameter("no");
		RequestDAO.getInstance().deleteRequest(Integer.parseInt(requestNum));
		
		return "redirect:/front/MyRequest?command=home";
	}

}
