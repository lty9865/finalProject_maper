package com.mapers.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapers.dao.NoticeDAO;
import com.mapers.dto.NoticeDTO;


/**
 * Servlet implementation class WriteController
 */
@WebServlet("/noticewrite.do")
public class NoticeWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		NoticeDTO dto = new NoticeDTO();
		dto.setTitle(request.getParameter("title"));
		dto.setContents(request.getParameter("contents"));
		
		NoticeDAO dao = NoticeDAO.getInstance();
		int result = dao.insertNotice(dto);
		
		if(result == 1) {
			System.out.println("공지사항을 성공적으로 등록하였습니다.");
		}else {
			System.out.println("공지사항에 실패하였습니다.");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("./Boardlist/notice.jsp");
		dispatcher.forward(request, response);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/Boardwrite/noticewrite.jsp").forward(request, response);
	}
	
}
