package com.mapers.myPageLike.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/front/MyLike")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		this.HandleRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		this.HandleRequest(request, response);
	}
	/*
	 * HandleRequest method 작동 방식
	 * 1. 에러 발생 시, 콘솔에 메세지 발생 경로 출력 후, error.jsp로 redirect 방식으로 view로 이동
	 * 2. 클라이언트가 전송한 command 정보를 받음
	 * 3. HandlerMapping 이용하여 개별 컨트롤러 객체를 컨트롤러 인터페이스 타입으로 리턴
	 * 4. 개별 컨트롤러를 실행
	 * 5. 실행 후 리턴된 String url 정보를 이용하여 forward or redirect 방식으로 view로 이동해 응답하게 함
	 */
	
	public void HandleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
