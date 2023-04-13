package com.mapers.SignUp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Member/SignUp/EmailVerificationAction.do")
public class VerifyEmailController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
        String userInput = request.getParameter("verificationCode");
        String sessionCode = (String) request.getSession().getAttribute("verificationCode");

        if (userInput != null && userInput.equals(sessionCode)) {
            // Verification was successful
            response.getWriter().print("인증성공");
            // You can also redirect the user to another page or perform any other action you'd like.
        } else {
            // Verification failed
            response.getWriter().print("인증 실패, 코드를 잘못 입력하셨습니다.");
        }
    }
}
