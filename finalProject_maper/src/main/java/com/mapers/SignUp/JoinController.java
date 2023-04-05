package com.mapers.SignUp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.SignUp.MemberVO;
import com.mapers.SignUp.MemberDAO;

//회원가입
@WebServlet("/Join.do")
public class JoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/Member/SignUp/Join.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		LisencekeyCreate lc = new LisencekeyCreate();

		

		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String licensekey = lc.LicenceKey();
		String email = request.getParameter("email");
		String birth = request.getParameter("year") + "-" + request.getParameter("month") + "-"
				+ request.getParameter("day");

		MemberVO mVo = new MemberVO();

		mVo.setUserid(userid);
		mVo.setPassword(password);
		mVo.setConfirmPassword(confirmPassword);
		mVo.setEmail(email);
		mVo.setBirth(birth);
		mVo.setLicenseKey(licensekey);

		MemberDAO mDao = MemberDAO.getInstance();

		int result = mDao.join(mVo);

		HttpSession session = request.getSession();
		
		
		int resultUserId = mDao.confirmID(userid);
		
		if(resultUserId == 1) {
			request.setAttribute("userid", userid);
			request.setAttribute("resultUserId", resultUserId);
			
			request.setAttribute("message", "사용가능한 아이디입니다.");
			
		}else if(resultUserId==-1) {
			
			request.setAttribute("message", "사용중인 아이디입니다.");
		}

		// 가입 성공
		if (result == 1) {
			session.setAttribute("userid", mVo.getUserid());
			request.setAttribute("licensekey", licensekey);

			RequestDispatcher dispatcher = request.getRequestDispatcher("Member/SignUp/successSignUp.jsp");
			dispatcher.forward(request, response);

		} else {
			request.setAttribute("message", "회원 가입에 실패했습니다.");
		}

	}
}