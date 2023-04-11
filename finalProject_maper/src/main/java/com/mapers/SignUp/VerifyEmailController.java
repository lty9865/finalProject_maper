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
        String userInput = request.getParameter("verificationCode");
        String sessionCode = (String) request.getSession().getAttribute("verificationCode");

        if (userInput != null && userInput.equals(sessionCode)) {
            // Verification was successful
            response.getWriter().print("Email verification successful!");
            // You can also redirect the user to another page or perform any other action you'd like.
        } else {
            // Verification failed
            response.getWriter().print("Email verification failed. The verification code does not match.");
        }
    }
}
