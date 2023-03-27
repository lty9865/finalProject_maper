package com.mapers.myPage.Profile.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.myPage.Profile.controller.Controller;
import com.mapers.myPage.Profile.model.ProfileDAO;
import com.mapers.myPage.Profile.model.ProfileDTO;


public class UpdateProfileController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
//		if (session == null || session.getAttribute("ProfileDTO") == null) {
//			return "redirect:index.jsp";
//		}
		
		String Img = request.getParameter("Img");
		String userId = request.getParameter("userId");
		String email = request.getParameter("email");
		String birth = request.getParameter("birth");
		
		ProfileDTO pDTO = new ProfileDTO();
		pDTO.setUserId(userId);
		pDTO.setEmail(email);
		pDTO.setBirth(birth);
		
		ProfileDAO.getInstance().updateProfile(pDTO);
		
		String path = "redirect:/front/MyProfile?command=home";
		
		return path;
	}

}
