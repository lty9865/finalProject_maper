package com.mapers.notice.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapers.common.Controller;
import com.mapers.notice.model.NoticeDAO;

public class NoticeDeleteController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idx = Integer.parseInt(request.getParameter("idx"));

		NoticeDAO dao = NoticeDAO.getInstance();
		int result = dao.deleteNotice(idx);

		dao.close();
		if (result == 1) {
			System.out.println("공지사항 삭제를 성공하였습니다.");
			request.setAttribute("url", "/Notice/notice.do?command=list");
			return "redirect:../Notice/notice.do?command=list";
		} else {
			System.out.println("공지사항 삭제 실패");
			return "/Notice/notice.do?command=view&idx=" + idx;
		}
	}
}
