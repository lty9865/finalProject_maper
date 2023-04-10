package com.mapers.myPage.Admins.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;
import com.mapers.myPage.Admins.model.AdminsDAO;
import com.mapers.report.ReportDTO;

public class AdminsReportPostViewHandler implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
        String[] userIdPart = userId.split("_");
        
        session.setAttribute("userId", userId);
		
		int reportNum = Integer.parseInt(request.getParameter("reportNum"));
		
		// 게시글 상세보기
		ReportDTO wDTO = AdminsDAO.getInstance().viewReport(reportNum);
		
		// 상세보기 정보 공유
		request.setAttribute("reportNum", reportNum);
        request.setAttribute("wDTO", wDTO);
		// url에 상세보기 페이지로 이동
		request.setAttribute("url", "${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins.reportPostView");
		
		return "/MyPage/Admins/adminsReportPostView.jsp";
	}
}
