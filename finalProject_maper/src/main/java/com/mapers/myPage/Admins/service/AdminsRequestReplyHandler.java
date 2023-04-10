package com.mapers.myPage.Admins.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;

public class AdminsRequestReplyHandler implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        String[] userState = userId.split("_");
        String userIdf = userState[0];
        if (userIdf == null || userIdf.isEmpty()) {
        	userIdf = "admins";
        }
        int adminCon = Integer.parseInt(userState[1]);
        if (adminCon != 1) {
        	adminCon = 1;
        }

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

