package com.mapers.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapers.util.FileUtil;

@WebServlet("/Page/pageDelete.do")
public class PageDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idx = req.getParameter("idx");
		String mode = req.getParameter("mode");

		if (mode.equals("delete")) {
			PageDAO dao = PageDAO.getInstance();
			PageDTO dto = dao.selectPageView(idx);
			int result = dao.deletePage(idx);
			dao.close();

			if (result == 1) {
				String saveFileName = dto.getSfile();
				FileUtil.deleteFile(req, "/Uploads/Page", saveFileName);
				resp.sendRedirect("../Page/pageList.do");
			}

		}
	}

}
