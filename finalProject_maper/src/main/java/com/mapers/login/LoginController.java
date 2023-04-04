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
import com.mapers.SignUp.MemberVO;


@WebServlet("/Member/Login/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/Webmain/mainpage.jsp";
		HttpSession session = request.getSession();
		
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		
		if(session.getAttribute("userid") != null) {
			url = "/Webmain/mainpage.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/Webmain/mainpage.jsp";

		String userid = request.getParameter("userid");
		String password = request.getParameter("password");

		MemberDAO mDao = MemberDAO.getInstance();
		
		int result = mDao.userCheck(userid, password);

		if (result == 1) {		//로그인 성공
			MemberVO mVo = mDao.getMember(userid);
			HttpSession session = request.getSession();
			session.setAttribute("userid", userid);
			url = "/Webmain/mainpage.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
			
		}else if(result == 0) {
			request.setAttribute("falseMsg", "아이디 및 비밀번호가 틉립니다. 다시 확인해주세요.");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Member/Login/login.jsp");
			dispatcher.forward(request, response);
			
		}else if(result == -1) {
			request.setAttribute("falseMsg", "없는 아이디입니다. 다시 확인해주세요.");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Member/Login/login.jsp");
			dispatcher.forward(request, response);
		}
		
			
		}

		
	}
