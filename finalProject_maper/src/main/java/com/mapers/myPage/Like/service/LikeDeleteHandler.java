package com.mapers.myPage.Like.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;
import com.mapers.myPage.Like.model.LikeDAO;

public class LikeDeleteHandler implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        session.setAttribute("userId", userId);

        int bookNum = Integer.parseInt(request.getParameter("bookNum"));

        LikeDAO kDAO = LikeDAO.getInstance();

        int result = kDAO.deleteLike(bookNum, userId);
        String nextPage = null;
        if (result > 0) {
            nextPage = "/MyPage/Like/likeList.jsp"; 
        } else {
            request.setAttribute("errorMsg", "좋아요를 취소하는 오류가 발생했습니다. 잠시 후 다시 시도해주시길 바랍니다.");
            nextPage = "/MyPage/Like/likeList.jsp"; 
        }

        return nextPage;
    }
}
