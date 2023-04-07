package com.mapers.notice.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapers.common.Controller;
import com.mapers.notice.model.NoticeDAO;
import com.mapers.notice.model.NoticeDTO;

public class NoticeWriteController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		NoticeDTO dto = new NoticeDTO();
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("contents"));
		
		NoticeDAO dao = NoticeDAO.getInstance();
		int result = dao.insertNotice(dto);
		
		if(result == 1) {
			System.out.println("공지사항을 성공적으로 등록하였습니다.");
			request.setAttribute("url", "/Notice/notice.do?command=list");
			return "/Notice/notice.do?command=list";
		}else {
			System.out.println("공지사항에 실패하였습니다.");
			request.setAttribute("url", "/Notice/notice.do?command=writeView");
			return "/Notice/noticeWrite.do?command=writeView";
		}
	}
}
