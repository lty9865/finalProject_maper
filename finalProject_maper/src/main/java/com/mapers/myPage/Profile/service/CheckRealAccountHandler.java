package com.mapers.myPage.Profile.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;
import com.mapers.myPage.Profile.model.ProfileDAO;
import com.mapers.myPage.Profile.model.ProfileDTO;

public class CheckRealAccountHandler implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        String userId = (String) session.getAttribute("userId");
        String password = request.getParameter("password");

        if (userId == null || userId.isEmpty()) {
            userId = "test1";
        }

        ProfileDTO pDTO = ProfileDAO.getInstance().getProfile(userId, password);
        if (pDTO == null) {
            request.setAttribute("errMsg", "비밀번호를 입력해주세요.");
            request.setAttribute("url", "${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyProfile.checkRealAccount");
            session.setAttribute("userId", userId);
            request.setAttribute("selectedMenuItem", "MyProfile");
            
            return "/MyPage/Profile/checkRealAccount.jsp";
            

        } else {
            session.setAttribute("userId", userId);
            request.setAttribute("password", password);
            request.setAttribute("email", pDTO.getEmail());
            request.setAttribute("birth", pDTO.getBirth());
            request.setAttribute("ofile", pDTO.getOfile());
            request.setAttribute("sfile", pDTO.getSfile());

            String relativeUrl = "${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyProfile.profileEdit";
            request.setAttribute("url", relativeUrl);
            request.setAttribute("selectedMenuItem", "MyProfile");
            
            return "/MyPage/Profile/profileEdit.jsp";
        }
    }
}
