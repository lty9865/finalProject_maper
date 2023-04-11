package com.mapers.SignUp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Member/SignUp/VerifyEmailServlet")
public class EmailVerificationCodeSendController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String verificationCode = generateVerificationCode(); // Generate a random verification code
        try {

        	String message = "Your verification code is: " + verificationCode;
        	EmailUtil.sendEmail(email, "Email Verification", message);
        	
            request.getSession().setAttribute("verificationCode", verificationCode);
            response.getWriter().print("An email with the verification code has been sent to " + email);
        } catch (Exception e) { // Changed to catch a more general exception
            e.printStackTrace();
            response.getWriter().print("Error: Unable to send the verification email.");
        }
    }

    private String generateVerificationCode() {
        StringBuffer bf = new StringBuffer();
        
        for (int i = 0; i < 6; i++) {
            int n = (int) (Math.random() * 10);
            bf.append(n);
        }
        
        String randNum = bf.toString();
        return randNum;
    }
}
