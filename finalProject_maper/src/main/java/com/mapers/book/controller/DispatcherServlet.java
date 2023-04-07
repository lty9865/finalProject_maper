package com.mapers.book.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.book.model.BookDAO;
import com.mapers.book.model.BookDTO;
import com.mapers.common.Controller;

@WebServlet("/Book/book.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// handleRequest method 호출
		this.HandleRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Post 방식 한글 처리
		request.setCharacterEncoding("UTF-8");

		// 좋아요 기능
		if (request.getParameter("command").equals("like")) {
			HttpSession session = request.getSession();
			BookDTO bookDTO = (BookDTO) session.getAttribute("bookDTO");
			BookDAO dao = BookDAO.getInstance();
			if (bookDTO == null) {
				response.sendRedirect("/Common/logOutProcess.jsp");
			} else {

				int bookNum = bookDTO.getBookNum();
				String userId = (String) session.getAttribute("userId");
				int likeCheck = (int) session.getAttribute("likeCheck");

				int likeCount = bookDTO.getLikesCount();
				if (likeCheck == 0) {
					dao.insertLikeUser(bookNum, userId);
					likeCount += 1;
					response.getWriter().print(likeCount);
					session.removeAttribute("likeCheck");
					session.setAttribute("likeCheck", 1);
				} else {
					dao.deleteLikeUser(bookNum, userId);
					likeCount -= 1;
					response.getWriter().print(likeCount);
					session.removeAttribute("likeCheck");
					session.setAttribute("likeCheck", 0);
				}
				bookDTO.setLikesCount(likeCount);
			}

		} else {

			// handlerRequest method 호출
			this.HandleRequest(request, response);
		}
	}

	/*
	 * handleRequest method 모든 클라이언트의 요청을 처리
	 */
	public void HandleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		try {
			// view에서 보낸 command를 받음
			String command = request.getParameter("command");

			// HandleMapping을 이용해 개별 컨트롤러 객체를 컨트롤러 인터페이스 타입으로 리턴
			Controller controller = HandlerMapping.getInstance().create(command);

			// 개별 컨트롤러 실행
			String url = controller.execute(request, response);

			// 실행 후 리턴된 String url 정보를 이용하여,
			// forward 방식 or redirect 방식으로 view로 이동해 응답하게 함
			if (url.startsWith("redirect")) {
				response.sendRedirect(url.substring(9));
			} else {
				request.getRequestDispatcher(url).forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// error.jsp로 이동
			response.sendRedirect("error.jsp");
		}
	}
}
