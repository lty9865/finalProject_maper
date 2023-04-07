package com.mapers.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapers.SignUp.MemberVO;
//아이디 찾기 controller
@WebServlet("/findId.do")
public class FindIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/Member/Login/findID.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		request.setCharacterEncoding("UTF-8");
		
		String licensekey = request.getParameter("licensekey");
		
		FindIdDAO fdao = FindIdDAO.getInstance();
		
		MemberVO mVo = new MemberVO();
		
		mVo.setLicenseKey(licensekey);
		
		String userid = fdao.FindId(mVo);
		System.out.print(userid);
		
		if(userid != null) {
		
		request.setAttribute("userid", userid);
		
		RequestDispatcher rd = request.getRequestDispatcher("/Member/Login/FindIdResult.jsp");
		rd.forward(request, response);
		}
		else if(userid == null) {
			request.setAttribute("errMsg", "라이센스 키를 다시 확인해주세요.");
			
			RequestDispatcher rd = request.getRequestDispatcher("/Member/Login/FindIdResult.jsp");
			rd.forward(request, response);
			
		}
	}

}
