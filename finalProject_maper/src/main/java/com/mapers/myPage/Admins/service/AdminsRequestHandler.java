package com.mapers.myPage.Admins.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;
import com.mapers.myPage.Admins.model.AdminsDAO;
import com.mapers.myPage.Request.model.RequestDTO;

public class AdminsRequestHandler implements Controller {
	private static final int RECORDS_PER_PAGE = 10;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AdminsDAO aDAO = AdminsDAO.getInstance();

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        String[] userIdParte = userId.split("_");
        String userIdFront = userIdParte[0];
        if (userIdFront == null || userIdFront.isEmpty()) {
        	userIdFront = "admins";
        }
        int adminCon = Integer.parseInt(userIdParte[1]);
        if (adminCon != 1) {
        	adminCon = 1;
        }
        session.setAttribute("userId", userId);

        Integer totalPostCount = (Integer) session.getAttribute("totalPostCount");

        String pageStr = request.getParameter("page");
        int page = 1;

        if (pageStr != null && !pageStr.isEmpty()) {
            page = Integer.parseInt(pageStr);
        }

        int currentTotalCount = aDAO.getRequestTotalPostCount();
        if (totalPostCount == null || totalPostCount != currentTotalCount) {
            session.setAttribute("totalPostCount", currentTotalCount);
        }

        int pageNo = page;
        int postsPerPage = RECORDS_PER_PAGE;

        List<RequestDTO> rDTO = aDAO.getAllRequestList(pageNo, postsPerPage);
        int totalPages = (int) Math.ceil((double) currentTotalCount / postsPerPage);
        
        request.setAttribute("requestBoard", rDTO);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("page", page);

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setHeader("Expires", "0"); // Proxies

        request.setAttribute("url", "${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins.requestBoard");

        return "/MyPage/Admins/adminsRequestList.jsp";
    }
}
