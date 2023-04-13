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
        int adminCon = Integer.parseInt(userIdPart[1]);
        
        session.setAttribute("userId", userId);

        if (adminCon == 1) {
        	int requestNum = Integer.parseInt(request.getParameter("requestNum"));
            String originalTitle = request.getParameter("title");
            if (originalTitle.startsWith("RE: ")) {
            	String[] titlePart = originalTitle.split(" ");
            	originalTitle = titlePart[titlePart.length - 1];
            }
            String title = "RE: " + originalTitle;
            String content = request.getParameter("replyContent");
            String requestDate = request.getParameter("requestDate");
            Date postDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String postDateString = dateFormat.format(postDate);

            RequestDTO rDTO = new RequestDTO();
            rDTO.setRequestNum(requestNum);
            rDTO.setTitle(title);
            rDTO.setContent(content);
            rDTO.setUserId(userIdPart[0]);
            rDTO.setPostDate(requestDate);
            rDTO.setReplyDate(postDateString);

            int result = aDAO.insertRequest(rDTO);
            
            request.setAttribute("selectedMenuItem", "AdminsRequestBoard");

            if (result > 0) {
                request.setAttribute("message", "The response to the inquiry has been processed.");
                request.setAttribute("rDTO", rDTO);
                
                aDAO.updateRequestStatus(requestNum);
                
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
