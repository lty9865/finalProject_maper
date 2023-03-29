package com.mapers.myPage.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapers.common.Controller;

/**
 * Servlet implementation class DispatcherServletSample
 */
@WebServlet("/MyPageFront")
// 1. /MyPageFront?command=MyProfile.profileEdit
// 2. /MyPageFront?command=MyRequest.requestPostDetailView
// 3. /MyPageFront?command=MyLike.likeDetailView
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// handleRequest method 호출
		this.handleRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Post 방식 한글 처리
		request.setCharacterEncoding("UTF-8");
		
		// handlerRequest method 호출
		this.handleRequest(request, response);
	}
	
	/*
	 * handleRequest method 모든 클라이언트의 요청을 처리
	 */
	protected void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			// 1. /MyPageFront?command=MyProfile.profileEdit
			// 2. /MyPageFront?command=MyRequest.requestPostDetailView
			// 3. /MyPageFront?command=MyLike.likeDetailView
			String command = request.getParameter("command");
			if (command == null) {
				command = "MyProfile";
			}
			
			Controller controller = HandlerMapping.getInstance().create(command);
			String url = controller.execute(request, response).trim();
			
			// View로 화면 전송 (방법 선택)
			if (url.startsWith("redirect:")) { // redirect 방식이면 url 재할당
				url = url.substring(9);
				response.sendRedirect(url);
			} else { // forward 방식으로 전송
				request.getRequestDispatcher(url).forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("MyPage FrontController Error");
			response.sendRedirect("error.jsp");
		}
	}
}
