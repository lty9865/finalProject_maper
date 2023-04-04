package com.mapers.book.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapers.book.model.BookDAO;
import com.mapers.book.model.BookDTO;
import com.mapers.common.Controller;
import com.mapers.util.FileUtil;

public class BookDeleteController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("북 딜리트 컨트롤러 호출");
		
		String idx = (String)request.getSession().getAttribute("bookNum");
		System.out.println(idx);

		BookDAO dao = BookDAO.getInstance();

		BookDTO dto = dao.selectBook(idx);
		System.out.println("셀렉트 북 성공");
		int result = dao.deleteBook(idx);
		dao.close();
		if (result == 1) {
			String saveFileName = dto.getSfile();
			FileUtil.deleteFile(request, "/Uploads/Book", saveFileName);
		}
		System.out.println("북 삭제 성공");

		request.setAttribute("url", "/Book/book.do?command=bookList");
		return "redirect:../Book/book.do?command=bookList";
	}
}
