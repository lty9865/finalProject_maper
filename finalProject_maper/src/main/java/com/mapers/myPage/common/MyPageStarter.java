package com.mapers.myPage.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;

public class MyPageStarter implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        String nextPage;
        if (userId.equals("admins_1")) {
            nextPage = "/MyPage/Admins/adminsMemberList.jsp";
        } else {
            nextPage = "/MyPage/Profile/profile.jsp";
        }

        return nextPage;
    }
}
