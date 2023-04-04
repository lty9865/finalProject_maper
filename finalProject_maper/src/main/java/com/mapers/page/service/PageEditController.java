package com.mapers.page.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapers.book.model.BookDTO;
import com.mapers.common.Controller;
import com.mapers.page.model.PageDAO;
import com.mapers.page.model.PageDTO;
import com.mapers.util.FileUtil;
import com.oreilly.servlet.MultipartRequest;

public class PageEditController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String saveDirectory = request.getServletContext().getRealPath("/Uploads/Page");

		ServletContext application = request.getSession().getServletContext();
		int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));

		MultipartRequest mr = FileUtil.uploadFile(request, saveDirectory, maxPostSize);

		if (mr == null) {
			System.out.println("페이지 파일업로드 실패");
		}

		int idx = Integer.parseInt(mr.getParameter("pageNum"));
		BookDTO bookDTO = (BookDTO) request.getSession().getAttribute("bookDTO");
		int bookNum = bookDTO.getBookNum();
		String prevOfile = mr.getParameter("prevOfile");
		String prevSfile = mr.getParameter("prevSfile");

		String title = mr.getParameter("subTitle");
		String content = mr.getParameter("content");
		String postDate = mr.getParameter("postDate");
		int rate = Integer.parseInt(mr.getParameter("rate"));

		PageDTO dto = new PageDTO();
		dto.setPageNum(idx);
		dto.setSubTitle(title);
		dto.setContent(content);
		dto.setPostDate(postDate);
		dto.setRate(rate);

		String fileName = mr.getFilesystemName("ofile");
		if (fileName != null) {
			String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
			String ext = fileName.substring(fileName.lastIndexOf("."));
			String newFileName = now + ext;

			File oldFile = new File(saveDirectory + File.separator + fileName);
			File newFile = new File(saveDirectory + File.separator + newFileName);
			oldFile.renameTo(newFile);

			dto.setOfile(fileName);
			dto.setSfile(newFileName);

			FileUtil.deleteFile(request, "/Uploads/Page", prevSfile);
		} else {
			dto.setOfile(prevOfile);
			dto.setSfile(prevSfile);
		}

		PageDAO dao = PageDAO.getInstance();
		int result = dao.updatePage(dto);
		dao.close();

		if (result == 1) {
			request.setAttribute("url", "/Page/page.do?command=pageEdit&idx=" + bookNum);
			return "/Page/page.do?command=pageList&idx=" + bookNum;
		} else {
			request.setAttribute("url", "/Page/page.do?command=pageEditView&idx=" + idx);
			return "/Page/page.do?command=pageEditView&idx=" + idx;
		}
	}
}
