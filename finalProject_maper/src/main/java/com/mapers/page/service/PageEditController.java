package com.mapers.page.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapers.page.model.PageDAO;
import com.mapers.page.model.PageDTO;
import com.mapers.util.FileUtil;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/Page/pageEdit.do")
public class PageEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idx = req.getParameter("idx");
		PageDAO dao = PageDAO.getInstance();
		PageDTO dto = dao.selectPageView(idx);
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/Page/pageEdit.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String saveDirectory = req.getServletContext().getRealPath("/Uploads/Page");
		
		ServletContext application = getServletContext();
		int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));
		
		MultipartRequest mr = FileUtil.uploadFile(req, saveDirectory, maxPostSize);
		
		if(mr == null) {
			return;
		}
		
		int idx = Integer.parseInt(mr.getParameter("idx"));
		String prevOfile = mr.getParameter("prevOfile");
		String prevSfile = mr.getParameter("prevSfile");
		
		String title = mr.getParameter("title");
		String content =mr.getParameter("content");
		String postDate = mr.getParameter("postDate");
		int rate = Integer.parseInt(mr.getParameter("rate"));
		
		PageDTO dto = new PageDTO();
		dto.setPageNum(idx);
		dto.setSubTitle(title);
		dto.setContent(content);
		dto.setPostDate(postDate);
		dto.setRate(rate);
		
		String fileName = mr.getFilesystemName("ofile");
		if(fileName != null) {
			String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
			String ext = fileName.substring(fileName.lastIndexOf("."));
			String newFileName = now + ext;
			
			File oldFile = new File(saveDirectory + File.separator + fileName);
			File newFile = new File(saveDirectory + File.separator + newFileName);
			oldFile.renameTo(newFile);
			
			dto.setOfile(fileName);
			dto.setSfile(newFileName);
			
			FileUtil.deleteFile(req, "/Uploads/Page", prevSfile);
		}else {
			dto.setOfile(prevOfile);
			dto.setSfile(prevSfile);
		}
		
		PageDAO dao = PageDAO.getInstance();
		int result = dao.updatePage(dto);
		dao.close();
		
		if(result == 1) {
			resp.sendRedirect("../Page/pageView.do?idx=" + idx);
		}
	}
}
