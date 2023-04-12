package com.mapers.myPage.Request.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;
import com.mapers.myPage.Request.model.RequestDAO;
import com.mapers.myPage.Request.model.RequestDTO;

public class RequestEditProcessHandler implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        session.setAttribute("userId", userId);
        
        int requestNum = Integer.parseInt(request.getParameter("requestNum"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        RequestDTO rDTO = new RequestDTO();
        rDTO.setUserId(userId);
        rDTO.setRequestNum(requestNum);
        rDTO.setTitle(title);
        rDTO.setContent(content);

        int result = RequestDAO.getInstance().editRequest(rDTO);
        
        if (result > 0) {
            request.setAttribute("rDTO", rDTO);
            request.setAttribute("selectedMenuItem", "MyRequest");
            
            return "/MyPage/Request/requestPostView.jsp";
        } else {
            request.setAttribute("error", "정보가 처리되는데 문제가 발생했습니다. 다시 시도해주세요.");
            request.setAttribute("rDTO", rDTO);
            request.setAttribute("selectedMenuItem", "MyRequest");
            
            return "/MyPage/Request/requestEdit.jsp";
        }
    }
}
