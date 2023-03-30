package com.mapers.book.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapers.book.model.BookDAO;
import com.mapers.book.model.BookDTO;
import com.mapers.util.FileUtil;

@WebServlet("/Book/bookDelete.do")
public class BookDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idx = req.getParameter("idx");
		String mode = req.getParameter("mode");
		
		BookDAO dao = BookDAO.getInstance();
		
		if(mode.equals("delete")) {
			BookDTO dto = dao.selectBook(idx);
			int result = dao.deleteBook(idx);
			dao.close();
			if(result==1) {
				String saveFileName = dto.getSfile();
				FileUtil.deleteFile(req, "/Uploads/Book", saveFileName);
			}
			System.out.println("북 삭제 성공");
		}
	}

	
}
