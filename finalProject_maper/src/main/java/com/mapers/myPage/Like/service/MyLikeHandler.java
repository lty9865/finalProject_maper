package com.mapers.myPage.Like.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;
import com.mapers.myPage.Like.model.LikeDAO;
import com.mapers.myPage.Like.model.LikeDTO;

public class MyLikeHandler implements Controller {

    private static final int RECORDS_PER_PAGE = 10;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LikeDAO kDAO = LikeDAO.getInstance();

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        session.setAttribute("userId", userId);

        int totalPostCount = LikeDAO.getInstance().getTotalPostCount(userId);

        String pageNoStr = request.getParameter("pageNo");
        int pageNo = 1;

        if (pageNoStr != null && !pageNoStr.isEmpty()) {
            pageNo = Integer.parseInt(pageNoStr);
        }
        int postsPerPage = RECORDS_PER_PAGE;

        ArrayList<LikeDTO> kList = kDAO.getAllList(pageNo, postsPerPage, userId);
        int totalPages = (int) Math.ceil((double) totalPostCount / postsPerPage);

        request.setAttribute("likeList", kList);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("page", pageNo);

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setHeader("Expires", "0"); // Proxies

        request.setAttribute("url", "${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyLike");

        request.setAttribute("selectedMenuItem", "MyLike");
        
        return "/MyPage/Like/likeList.jsp";
    }
}
