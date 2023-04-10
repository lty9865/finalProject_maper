package com.mapers.myPage.Admins.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;
import com.mapers.myPage.Admins.model.AdminsDAO;
import com.mapers.myPage.Request.model.RequestDTO;

public class AdminsRequestReplyEditProcessHandler implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        
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
        
        int requestNum = Integer.parseInt(request.getParameter("requestNum"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        RequestDTO rDTO = new RequestDTO();
        rDTO.setUserId(userIdFront);
        rDTO.setRequestNum(requestNum);
        rDTO.setTitle(title);
        rDTO.setContent(content);

        int result = AdminsDAO.getInstance().editRequest(rDTO);
        
        if (result > 0) {
            request.setAttribute("rDTO", rDTO);
            
            return "/MyPage/Admins/adminsRequestReplyView.jsp";
        } else {
            request.setAttribute("error", "문의 글에 대한 답변 글 작성 중 오류 발생.");
            request.setAttribute("rDTO", rDTO);
            
            return "/MyPage/Admins/adminsRequestReplyEdit.jsp";
        }
    }
}
