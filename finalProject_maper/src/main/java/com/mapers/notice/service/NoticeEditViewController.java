package com.mapers.notice.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapers.common.Controller;
import com.mapers.notice.model.NoticeDAO;
import com.mapers.notice.model.NoticeDTO;

public class NoticeEditViewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		NoticeDAO dao = NoticeDAO.getInstance();
		NoticeDTO dto = dao.viewNotice(idx);
		request.setAttribute("dto", dto);
		request.setAttribute("url", "/Notice/notice.do?command=editView");
		
		dao.close();
		
		return "/Notice/noticeEdit.jsp";
	}

}
