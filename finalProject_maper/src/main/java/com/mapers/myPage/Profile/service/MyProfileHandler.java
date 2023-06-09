package com.mapers.myPage.Profile.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;
import com.mapers.myPage.Profile.model.ProfileDAO;
import com.mapers.myPage.Profile.model.ProfileDTO;

public class MyProfileHandler implements Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    request.setCharacterEncoding("UTF-8");

	    HttpSession session = request.getSession();
	    String userId = (String) session.getAttribute("userId");

	    ProfileDTO pDTO = ProfileDAO.getInstance().getProfile(userId);
	    String ofile = "basicProfile.png";
	   
	    request.setAttribute("userId", pDTO.getUserId());
	    request.setAttribute("password", pDTO.getPassword());
	    request.setAttribute("email", pDTO.getEmail());
	    request.setAttribute("birth", pDTO.getBirth());
	    
	    if (pDTO.getSfile() == null) {
	    	request.setAttribute("ofile", ofile);
	    
	    } else {
	    	request.setAttribute("sfile", pDTO.getSfile());
	    	
	    }
	    String relativeUrl = "${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyProfile";
	    request.setAttribute("url", relativeUrl);

	    request.setAttribute("selectedMenuItem", "MyProfile");
	    
	    return "/MyPage/Profile/profile.jsp";
	}
}