package com.mapers.SignUp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SignUp.do")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/Member/SignUp/SignUp.jsp").forward(request, response);
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

		HttpSession session = request.getSession();

		int result = mDao.join(mVo);

		// 가입 성공
		if (result == 1) {
			session.setAttribute("userid", mVo.getUserid());
			request.setAttribute("licensekey", licensekey);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/Member/SignUp/successSignUp.jsp");
			dispatcher.forward(request, response);

		} else {
			request.setAttribute("falseMsg", "회원 가입에 실패했습니다.");

			RequestDispatcher dispatcher = request.getRequestDispatcher("/Member/SignUp/SignUp.jsp");
			dispatcher.forward(request, response);
		}

	}
}