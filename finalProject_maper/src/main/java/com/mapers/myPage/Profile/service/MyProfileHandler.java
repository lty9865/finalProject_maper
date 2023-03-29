package com.mapers.myPage.Profile.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;
import com.mapers.myPage.Profile.model.ProfileDAO;
import com.mapers.myPage.Profile.model.ProfileDTO;

public class MyProfileHandler implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        
        String userId = (String) session.getAttribute("userId");
        String password = request.getParameter("password");
        
        ProfileDTO pDTO = ProfileDAO.getInstance().getProfile(userId, password);
        
        request.setAttribute("userId", pDTO.getUserId());
        request.setAttribute("password", pDTO.getPassword());
        request.setAttribute("email", pDTO.getEmail());
        request.setAttribute("birth", pDTO.getBirth());
        request.setAttribute("image", pDTO.getProfileImg());
        
        request.setAttribute("url", "/MyPageFront?command=MyProfile");
        
        return "/MyPage/Profile/profile.jsp";
    }
}

