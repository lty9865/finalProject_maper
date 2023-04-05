package com.mapers.myPage.Request.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;
import com.mapers.myPage.Request.model.RequestDTO;

public class RequestEditHandler implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        String method = request.getMethod();

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        session.setAttribute("userId", userId);

        int requestNum = Integer.parseInt(request.getParameter("requestNum"));

        if (method.equals("POST")) {
            // When '수정하기' button is clicked
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String postDate = request.getParameter("postDate");

            RequestDTO rDTO = new RequestDTO();
            rDTO.setUserId(userId);
            rDTO.setRequestNum(requestNum);
            rDTO.setTitle(title);
            rDTO.setContent(content);
            rDTO.setPostDate(postDate);
            request.setAttribute("rDTO", rDTO);
            request.setAttribute("url", "${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyRequest.requestEdit");
            
            return "/MyPage/Request/requestEdit.jsp";
        }
        request.setAttribute("url", "${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyRequest.requestPostView");

        return "/MyPage/Request/requestPostView.jsp";
    }
}
