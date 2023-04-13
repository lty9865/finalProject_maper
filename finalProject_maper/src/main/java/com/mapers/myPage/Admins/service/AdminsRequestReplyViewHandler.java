package com.mapers.myPage.Admins.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;
import com.mapers.myPage.Admins.model.AdminsDAO;
import com.mapers.myPage.Request.model.RequestDAO;
import com.mapers.myPage.Request.model.RequestDTO;

public class AdminsRequestReplyViewHandler implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        session.setAttribute("userId", userId); // Set the userId only once

        if (userId.equals("admins_1")) {
            request.setAttribute("selectedMenuItem", "AdminsRequestBoard");

            String[] userIdPart = userId.split("_");
            String userIdFront = userIdPart[0];

            int requestNum = Integer.parseInt(request.getParameter("requestNum"));

            // 게시글 상세보기
            RequestDTO rDTO = RequestDAO.getInstance().viewRequest(requestNum, userIdFront);

            // 상세보기 정보 공유
            request.setAttribute("requestNum", requestNum);
            request.setAttribute("rDTO", rDTO);

        } else {
            request.setAttribute("selectedMenuItem", "MyRequest");

            String requestTitle = request.getParameter("requestTitle");
            String requestDate = request.getParameter("requestDate");
            System.out.println(requestTitle + " " + requestDate);
            
            requestTitle = "RE: " + requestTitle;

            RequestDTO rDTO = AdminsDAO.getInstance().matchRequest(requestDate, requestTitle, "admins");
            System.out.println(rDTO.getRequestNum());
            System.out.println(rDTO.getReplyDate());
            System.out.println(rDTO.getUserId());
            System.out.println(rDTO.getTitle());
            System.out.println(rDTO.getContent());
            System.out.println(rDTO.getStatus());
            System.out.println(rDTO.getPostDate());
            System.out.println(rDTO.getLicenseKey());
            request.setAttribute("rDTO", rDTO);

        }

        // url에 상세보기 페이지로 이동
        request.setAttribute("url", "${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins.requestReplyView");

        return "/MyPage/Admins/adminsRequestReplyView.jsp";
    }
}
