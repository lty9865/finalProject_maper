package com.mapers.book.service;

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

import com.mapers.book.model.BookDAO;
import com.mapers.book.model.BookDTO;
import com.mapers.util.FileUtil;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/Book/bookWrite.do")
public class BookWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/Book/bookWrite.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.파일업로드 처리
		// 업로드 디렉터리의 물리적 경로 확인
		String saveDirectory = req.getServletContext().getRealPath("/Uploads/Book");

		// 초기화 매개 변수로 설정한 첨부 파일 최대 용량 확인
		ServletContext application = getServletContext();
		int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));

		// 파일 업로드
		MultipartRequest mr = FileUtil.uploadFile(req, saveDirectory, maxPostSize);
		if (mr == null) {
			// 실패
			return;
		}

		// 2.파일 업로드 외 처리
		// 폼값을 DTO에 저장
		BookDTO dto = new BookDTO();
		dto.setUserId(mr.getParameter("userId"));
		dto.setTitle(mr.getParameter("title"));
		dto.setPlace(mr.getParameter("country") + "/" + mr.getParameter("city"));
		dto.setBookDate(mr.getParameter("bookDate"));

		// 원본 파일명과 저장된 파일 이름 설정
		String fileName = mr.getFilesystemName("ofile");
		if (fileName != null) {
			// 첨부파일이 있는 경우
			String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
			String ext = fileName.substring(fileName.lastIndexOf("."));
			String newFileName = now + ext;

			// 파일명 변경
			File oldFile = new File(saveDirectory + File.separator + fileName);
			File newFile = new File(saveDirectory + File.separator + newFileName);
			oldFile.renameTo(newFile);

			dto.setOfile(fileName);
			dto.setSfile(newFileName);
		}

		BookDAO dao = BookDAO.getInstance();
		int result = dao.insertBook(dto);
		dao.close();

		if (result == 1) {
			resp.sendRedirect("../Book/bookList.do");
		} else {
			System.out.print("북 생성 컨트롤러 중 에러 발생");
			resp.sendRedirect("../Book/bookWrite.do");
		}
	}

}
