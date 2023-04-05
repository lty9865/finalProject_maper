package com.mapers.myPage.Request.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;
import com.mapers.myPage.Request.model.RequestDAO;
import com.mapers.myPage.Request.model.RequestDTO;

public class MyRequestHandler implements Controller {
    private static final int RECORDS_PER_PAGE = 10;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDAO rDAO = RequestDAO.getInstance();

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        if (userId == null || userId.isEmpty()) {
            userId = "test1";
        }
        session.setAttribute("userId", userId);

        Integer totalPostCount = (Integer) session.getAttribute("totalPostCount");

        String pageStr = request.getParameter("page");
        int page = 1;

        if (pageStr != null && !pageStr.isEmpty()) {
            page = Integer.parseInt(pageStr);
        }

        int currentTotalCount = rDAO.getTotalPostCount();
        if (totalPostCount == null || totalPostCount != currentTotalCount) {
            session.setAttribute("totalPostCount", currentTotalCount);
        }

        int pageNo = page;
        int postsPerPage = RECORDS_PER_PAGE;

        List<RequestDTO> rDTO = rDAO.getAllList(pageNo, postsPerPage, userId);
        int totalPages = (int) Math.ceil((double) currentTotalCount / postsPerPage);
        
        request.setAttribute("requestList", rDTO);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("page", page);

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setHeader("Expires", "0"); // Proxies

        request.setAttribute("url", "${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyRequest");

        return "/MyPage/Request/requestList.jsp";
    }
}
