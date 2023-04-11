package com.mapers.notice.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapers.common.Controller;
import com.mapers.notice.model.NoticeDAO;
import com.mapers.notice.model.NoticeDTO;

public class NoticeViewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		NoticeDAO dao = NoticeDAO.getInstance();
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		dao.updateVisitCountNotice(idx);
		NoticeDTO dto = dao.viewNotice(idx);
		
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));
		
		request.setAttribute("dto", dto);
		request.setAttribute("url", "/Notice/notice.do?command=view");
		
		dao.close();

		return "/Notice/noticeView.jsp";
	}

}
