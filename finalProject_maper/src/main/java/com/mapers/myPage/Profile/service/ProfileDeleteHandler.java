package com.mapers.myPage.Profile.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mapers.common.Controller;

public class ProfileDeleteHandler implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        
        request.setAttribute("selectedMenuItem", "MyProfile");

        return "/MyPage/Profile/profileDelete.jsp";
    }
}
