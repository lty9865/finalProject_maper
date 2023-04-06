package com.mapers.page.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapers.common.Controller;
import com.mapers.page.model.PageDAO;
import com.mapers.page.model.PageDTO;
import com.mapers.util.FileUtil;

public class PageDeleteController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idx = Integer.parseInt(request.getParameter("idx"));

			PageDAO dao = PageDAO.getInstance();
			PageDTO dto = dao.selectPageView(idx);
			int result = dao.deletePage(idx);

			if (result == 1) {
				String saveFileName = dto.getSfile();
				FileUtil.deleteFile(request, "/Uploads/Page", saveFileName);
				request.setAttribute("url", "/Page/page.do?command=pageList");
			}
			dao.close();

		return "/Page/page.do?command=pageList";
	}

}
