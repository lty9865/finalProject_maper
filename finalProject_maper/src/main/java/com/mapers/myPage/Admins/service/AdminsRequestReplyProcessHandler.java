package com.mapers.myPage.Admins.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;
import com.mapers.myPage.Admins.model.AdminsDAO;
import com.mapers.myPage.Request.model.RequestDTO;

public class AdminsRequestReplyProcessHandler implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        AdminsDAO aDAO = AdminsDAO.getInstance();

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

        if (adminCon == 1) {
            String originalTitle = request.getParameter("originalTitle");
            String newTitle = "RE: " + originalTitle;
            String content = request.getParameter("content");

            Date postDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String postDateString = dateFormat.format(postDate);

            RequestDTO rDTO = new RequestDTO();
            rDTO.setTitle(newTitle);
            rDTO.setContent(content);
            rDTO.setUserId(userIdFront);
            rDTO.setPostDate(postDateString);

            int result = aDAO.insertRequest(rDTO);

            if (result > 0) {
                request.setAttribute("message", "The response to the inquiry has been processed.");

                return "/MyPage/Admins/adminsRequestReplyView.jsp";

            } else {
                request.setAttribute("error", "An error occurred while processing the response to the inquiry.");

                return "/MyPage/Admins/adminsRequestReply.jsp";
            }

        } else {

            return "/Member/Login/login.jsp";
        }
    }
}
