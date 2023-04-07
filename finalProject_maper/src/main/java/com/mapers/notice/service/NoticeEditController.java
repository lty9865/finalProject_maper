package com.mapers.notice.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapers.common.Controller;
import com.mapers.notice.model.NoticeDAO;
import com.mapers.notice.model.NoticeDTO;

public class NoticeEditController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		int idx = Integer.parseInt(request.getParameter("idx"));
		String title = request.getParameter("title");
		String content = request.getParameter("contents");

		NoticeDTO dto = new NoticeDTO();
		dto.setIdx(idx);
		dto.setTitle(title);
		dto.setContent(content);

		NoticeDAO dao = NoticeDAO.getInstance();

		int result = dao.updateNotice(dto);
		if (result == 1) {
			System.out.println("공지사항 수정에 성공하였습니다.");
			request.setAttribute("url", "/Notice/notice.do?command=view&idx="+idx);
			return "/Notice/notice.do?command=view&idx="+idx;
		} else {
			System.out.println("공지사항 수정에 실패하였습니다.");
			request.setAttribute("url", "/Notice/notice.do?command=list");
			return "redirect../Notice/notice.do?command=list";
		}
	}

}
