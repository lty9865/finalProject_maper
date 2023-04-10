package com.mapers.myPage.Admins.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;
import com.mapers.myPage.Request.model.RequestDAO;

public class AdminsRequestReplyDeleteHandler implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    request.setCharacterEncoding("UTF-8");

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
        
        if (adminCon == 1 && userIdFront.equals("admins")) {
        
        	String requestNum = request.getParameter("requestNum");
        	RequestDAO.getInstance().deleteRequest(Integer.parseInt(requestNum));
        	
        	response.setContentType("text/plain");
        	response.setCharacterEncoding("UTF-8");
        	response.getWriter().write("success");
        	
        	return "/MyPage/Request/requestList.jsp";
        
        }
        
        return "/Member/Login/login.jsp";
	}
}
