package com.mapers.page;

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

import com.mapers.util.FileUtil;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/Page/pageWrite.do")
public class PageWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/Page/pageWrite.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String saveDirectory = req.getServletContext().getRealPath("/Uploads/Page");

		ServletContext application = getServletContext();
		int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));

		MultipartRequest mr = FileUtil.uploadFile(req, saveDirectory, maxPostSize);
		if (mr == null) {
			System.out.println("페이지 파일 업로드 실패");
			return;
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
		
		if(result == 1) {
			resp.sendRedirect("../Page/pageList.do");
		}else {
			System.out.println("페이지 글 작성 실패");
			resp.sendRedirect("../Page/pageWrite.do");
		}

	}

}
