package com.mapers.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.SignUp.MemberDAO;

@WebServlet("/Member/Login/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect("/Member/Login/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/Webmain/mainPage.jsp";

		String userId = request.getParameter("userId");
		String password = request.getParameter("password");

		MemberDAO mDao = MemberDAO.getInstance();

		int result = mDao.userCheck(userId, password);

		if (userId == null || userId.trim().equals("")) {
			request.setAttribute("falseMsg", "아이디를 입력해주세요.");

			RequestDispatcher dispatcher = request.getRequestDispatcher("/Member/Login/login.jsp");
			dispatcher.forward(request, response);

		} else if (password == null || password.trim().equals("")) {
			request.setAttribute("falseMsg", "비밀번호를 입력해주세요.");

			RequestDispatcher dispatcher = request.getRequestDispatcher("/Member/Login/login.jsp");
			dispatcher.forward(request, response);

		} else if (result == 1) { // 로그인 성공
			HttpSession session = request.getSession();

			session.setAttribute("userId", userId);
			url = "/Webmain/mainPage.do?command=main";

			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}

		else if (result == 0) {
			request.setAttribute("falseMsg", "아이디 및 비밀번호가 틉립니다. 다시 확인해주세요.");

			RequestDispatcher dispatcher = request.getRequestDispatcher("/Member/Login/login.jsp");
			dispatcher.forward(request, response);
		} else if (result == -1) {
			request.setAttribute("falseMsg", "없는 아이디입니다. 다시 확인해주세요.");

			RequestDispatcher dispatcher = request.getRequestDispatcher("/Member/Login/login.jsp");
			dispatcher.forward(request, response);
		}
	}
}
