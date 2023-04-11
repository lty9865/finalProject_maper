package com.mapers.myPage.Admins.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;

public class AdminsRequestReplyHandler implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        request.setAttribute("selectedMenuItem", "AdminsRequestBoard");
        
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        String[] userIdPart = userId.split("_");
        String userIdFront = userIdPart[0];
        if (userIdFront == null || userIdFront.isEmpty()) {
        	userIdFront = "admins";
        }
        int adminCon = Integer.parseInt(userIdPart[1]);

        if (adminCon == 1) {
        	session.setAttribute("userId", userId);

            // Return the path to adminsRequestReply.jsp page
            return "/MyPage/Admins/adminsRequestReply.jsp";
        } else {
            // Return the path to /Member/Login/login.jsp page
            return "/Member/Login/login.jsp";
        }
    }
}

