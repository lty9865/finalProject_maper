package com.mapers.myPage.Request.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapers.common.Controller;

public class RequestTitleClickHandler implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String requestNum = request.getParameter("requestNum");
        String targetUrl;

        if (request.getServletContext().getResourceAsStream("/MyPage/Request/requestConversation.jsp") != null) {
            request.setAttribute("requestNum", requestNum);
            targetUrl = "/MyPage/Request/requestConversation.jsp";
        } else {
            targetUrl = "/MyPage/MyPageFront?command=MyRequest.requestPostView&requestNum=" + requestNum;
        }

        // Return the target URL instead of forwarding and returning null
        return targetUrl;
    }
}
