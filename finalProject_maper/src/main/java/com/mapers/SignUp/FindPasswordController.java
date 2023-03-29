package com.mapers.SignUp;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//비밀번호 찾기 controller
@WebServlet("/Member/FindPassword.do")
public class FindPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FindPasswordMethod(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FindPasswordMethod(request,response);
	}
	protected void FindPasswordMethod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String userid = request.getParameter("userid");
		String licensekey = request.getParameter("licensekey");
		
		FindPasswordDAO fdao = FindPasswordDAO.getInstance();
		
		MemberVO mVo = new MemberVO();
		
		mVo.setUserid(userid);
		mVo.setLicenseKey(licensekey);
		
		String password = fdao.FindPassword(mVo);
		
		request.setAttribute("password", password);
		
		System.out.println(password);
		
		RequestDispatcher rd = request.getRequestDispatcher("../Member/FindPasswordResult.jsp");
		rd.forward(request, response);
	}

}