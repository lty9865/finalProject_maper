package com.mapers.SignUp;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapers.SignUp.MemberDAO;
import com.mapers.SignUp.MemberVO;
//중복 아이디 체크
@WebServlet("/Member/SignUp/IdCheck.do")
public class IdCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IdCheckMethod(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IdCheckMethod(request,response);
	}
	
	protected void IdCheckMethod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
String userid = request.getParameter("userid");
		
		MemberDAO mDao = MemberDAO.getInstance();
		int result = mDao.confirmID(userid);
		
		request.setAttribute("userid", userid);
		request.setAttribute("result", result);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Member/SignUp/IdCheck.jsp");

		
		dispatcher.forward(request, response);
	}

	}


