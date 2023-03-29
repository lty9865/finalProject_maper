package com.mapers.logIn;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//아이디 찾기 controller
@WebServlet("/Member/FindId.do")
public class FindIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FindIdMethod(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FindIdMethod(request,response);
	}
	protected void FindIdMethod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String licensekey = request.getParameter("licensekey");
		
		FindIdDAO fdao = FindIdDAO.getInstance();
		
		MemberVO mVo = new MemberVO();
		
		mVo.setLicenseKey(licensekey);
		
		String userid = fdao.FindId(mVo);
		
		request.setAttribute("userid", userid);
		
		RequestDispatcher rd = request.getRequestDispatcher("../Member/FindIdResult.jsp");
		rd.forward(request, response);
	}

}
