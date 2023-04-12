package com.mapers.myPage.Profile.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;
import com.mapers.myPage.Profile.model.ProfileDAO;
import com.mapers.myPage.Profile.model.ProfileDTO;

public class ProfileEditHandler implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        if (userId == null || userId.isEmpty()) {
            userId = "test1";
        }
        
        String password = request.getParameter("password");
        if (password == null || password.isEmpty()) {
        	password = "pass";
        }

        ProfileDTO pDTO = ProfileDAO.getInstance().getProfile(userId);

        // Set the attributes for the JSP page
        request.setAttribute("userId", pDTO.getUserId());
        request.setAttribute("password", pDTO.getPassword());
        request.setAttribute("email", pDTO.getEmail());
        request.setAttribute("birth", pDTO.getBirth());
        if (pDTO.getOfile() != null) {
            request.setAttribute("ofile", pDTO.getOfile());
        } else {
            request.setAttribute("ofile", "/hardcover-Book.jpg");
        }
        request.setAttribute("sfile", pDTO.getSfile());

        String url = "${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyProfile.profileEdit";
        request.setAttribute("url", url);
        request.setAttribute("selectedMenuItem", "MyProfile");
        
        return "/MyPage/Profile/profileEdit.jsp"; // Show the profileEdit.jsp page
    }
}
