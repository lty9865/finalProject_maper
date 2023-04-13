package com.mapers.myPage.Admins.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;
import com.mapers.myPage.Admins.model.AdminsDAO;

public class AdminsRequestReplyHandler implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        request.setAttribute("selectedMenuItem", "AdminsRequestBoard");
        
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        String[] userIdPart = userId.split("_");
        String userIdFront = userIdPart[0];
        int adminCon = Integer.parseInt(userIdPart[1]);

        String requestNum = request.getParameter("requestNum");
        String requestTitle = request.getParameter("requestTitle");
        String requestUserId = request.getParameter("requestUserId");
        
        String requestDate = AdminsDAO.getInstance().viewRequestAsDate(requestNum, requestUserId);
        
        if (adminCon == 1) {
        	request.setAttribute("requestNum", requestNum);
        	request.setAttribute("requestTitle", requestTitle);
        	request.setAttribute("requestUserId", requestUserId);
        	request.setAttribute("requestDate", requestDate);
        	
        	session.setAttribute("userId", userId);
        	
        	return "/MyPage/Admins/adminsRequestReply.jsp";
        } else {
            
        	return "/Member/Login/login.jsp";
        }
    }
}

