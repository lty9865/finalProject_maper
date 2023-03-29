package com.mapers.myPage.Profile.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;
import com.mapers.myPage.Profile.model.ProfileDAO;
import com.mapers.myPage.Profile.model.ProfileDTO;

public class CheckAccountHandler implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            return "redirect:index.jsp";
        }

        String userId = (String) session.getAttribute("userId");
        String password = request.getParameter("password");

        ProfileDTO pDTO = ProfileDAO.getInstance().getProfile(userId, password);
        if (pDTO == null) {
            request.setAttribute("msg", "비밀번호가 일치하지 않습니다.");
            request.setAttribute("url", "/MyPage/Profile/checkRealAccount.jsp");
            return "/MyPageCommon/message.jsp";
        } else {
            request.setAttribute("rDTO", pDTO);
            request.setAttribute("url", "/MyPageFront?command=MyProfile.profileEditForm");
            return "/MyPage/Profile/profileEdit.jsp";
        }
    }
}

