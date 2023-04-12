package com.mapers.myPage.Like.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;

public class LikedBookViewHandler implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        session.setAttribute("userId", userId);
        
        // Get bookNum parameter from the request
        String idx = request.getParameter("idx");

        // Set the bookNum parameter for the request
        request.setAttribute("idx", idx);
        
        // Forward the request to the BookViewController1
        return "/Book/book.do?command=bookView";
    }
}
