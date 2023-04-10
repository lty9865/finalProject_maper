package com.mapers.myPage.Admins.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;
import com.mapers.myPage.Admins.model.AdminsDAO;
import com.mapers.report.ReportDTO;

public class AdminsReportHandler implements Controller {
	private static final int RECORDS_PER_PAGE = 10;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	request.setCharacterEncoding("UTF-8");

    	AdminsDAO aDAO = AdminsDAO.getInstance();

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

        Integer totalPostCount = (Integer) session.getAttribute("totalPostCount");

        String pageStr = request.getParameter("page");
        int page = 1;

        if (pageStr != null && !pageStr.isEmpty()) {
            page = Integer.parseInt(pageStr);
        }

        int currentTotalCount = aDAO.getReportTotalPostCount();
        if (totalPostCount == null || totalPostCount != currentTotalCount) {
            session.setAttribute("totalPostCount", currentTotalCount);
        }

        int pageNo = page;
        int postsPerPage = RECORDS_PER_PAGE;

        List<ReportDTO> wDTO = aDAO.getAllReportList(pageNo, postsPerPage);
        aDAO.closeAll();
        int totalPages = (int) Math.ceil((double) currentTotalCount / postsPerPage);

        int prevPage;
        int nextPage;

        if (page > 1) {
            prevPage = page - 1;
        } else {
            prevPage = 1;
        }

        if (page < totalPages) {
            nextPage = page + 1;
        } else {
            nextPage = totalPages;
        }

        request.setAttribute("reportBoard", wDTO);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("page", page);
        request.setAttribute("prevPage", prevPage);
        request.setAttribute("nextPage", nextPage);

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setHeader("Expires", "0"); // Proxies

        request.setAttribute("url", "${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins.reportBoard");

        return "/MyPage/Admins/adminsReportList.jsp";
    }
}
