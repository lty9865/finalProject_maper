package com.mapers.myPage.Request.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;
import com.mapers.myPage.Request.model.RequestDAO;

public class RequestDeleteHandler implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    request.setCharacterEncoding("UTF-8");

	    HttpSession session = request.getSession();
	    String userId = (String) session.getAttribute("userId");
	    session.setAttribute("userId", userId);

	    String requestNum = request.getParameter("requestNum");
	    RequestDAO.getInstance().deleteRequest(Integer.parseInt(requestNum));

	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write("success");
	    request.setAttribute("selectedMenuItem", "MyRequest");
	    
	    return "/MyPage/Request/requestList.jsp";
	}
}
