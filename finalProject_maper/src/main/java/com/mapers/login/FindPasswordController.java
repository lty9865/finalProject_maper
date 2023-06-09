package com.mapers.login;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapers.SignUp.MemberDTO;
//비밀번호 찾기 controller
@WebServlet("/Member/Login/findPassword.do")
public class FindPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/Member/Login/findPassword.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId");
		String licensekey = request.getParameter("licensekey");
		
		FindPasswordDAO fdao = FindPasswordDAO.getInstance();
		
		MemberDTO mDto = new MemberDTO();
		
		mDto.setUserId(userId);
		mDto.setLicenseKey(licensekey);
		
		String password = fdao.FindPassword(mDto);
		
		request.setAttribute("password", password);
		
		RequestDispatcher rd = request.getRequestDispatcher("/Member/Login/FindPasswordResult.jsp");
		rd.forward(request, response);
	}

}