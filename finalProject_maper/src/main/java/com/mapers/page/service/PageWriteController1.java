package com.mapers.page.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapers.common.Controller;
import com.mapers.page.model.PageDAO;
import com.mapers.page.model.PageDTO;
import com.mapers.util.FileUtil;
import com.oreilly.servlet.MultipartRequest;

public class PageWriteController1 implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String saveDirectory = request.getServletContext().getRealPath("/Uploads/Page");

		ServletContext application = request.getSession().getServletContext();
		int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));

		MultipartRequest mr = FileUtil.uploadFile(request, saveDirectory, maxPostSize);
		if (mr == null) {
			System.out.println("페이지 파일 업로드 실패");
		}

		PageDTO dto = new PageDTO();
		dto.setBookNum(Integer.parseInt(mr.getParameter("bookNum")));
		dto.setSubTitle(mr.getParameter("subTitle"));
		dto.setContent(mr.getParameter("content"));
		dto.setRate(Integer.parseInt(mr.getParameter("rate")));

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
		}

		PageDAO dao = PageDAO.getInstance();
		int result = dao.insertPage(dto);
		dao.close();

		int idx = dto.getBookNum();
		if (result == 1) {
			response.sendRedirect("../Page/pageList.do?idx=" + idx);
			request.setAttribute("url", "/Page/page.do?command=pageList");
			return "redirect:../Page/page.do?command=pageList";
		} else {
			System.out.println("페이지 글 작성 실패");
			request.setAttribute("url", "/Page/page.do?command=pageWrite");
			return "redirect:../Page/page.do?command=pageWrite";
		}
	}

}
