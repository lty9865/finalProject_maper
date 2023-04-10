package com.mapers.myPage.Profile.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;
import com.mapers.myPage.Profile.model.ProfileDAO;
import com.mapers.myPage.Profile.model.ProfileDTO;
import com.mapers.util.FileUtil;
import com.oreilly.servlet.MultipartRequest;

public class ProfileEditProcessHandler implements Controller {
	private static final int MAX_POST_SIZE = 1024 * 1024 * 10;
	
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        
	    HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        session.setAttribute("userId", userId);

        String password = request.getParameter("password");
		
	    // 1. Profile Image upload process
	    // set physical directory for upload
	    String saveDirectory = request.getServletContext().getRealPath("/Uploads/Profile");
	    
	    // initialize parameter to set the maxPostSize of upload file
	    int maxPostSize = MAX_POST_SIZE;
	    
	    // upload file
	    MultipartRequest mr = FileUtil.uploadFile(request, saveDirectory, maxPostSize);
	    if (mr == null) {
	    	// fail
	    	System.out.println("fail to upload file");
	    }
	    
	    // 2. Process except upload file
	    userId = mr.getParameter("userId");
	    password = mr.getParameter("password");
		String email = mr.getParameter("emailFront") + "@" + mr.getParameter("emailBack");
		String birth = mr.getParameter("birth");
		String prevOfile = mr.getParameter("prevOfile");
		String prevSfile = mr.getParameter("prevSfile");
		
		System.out.println(password + " " + email + " " + birth);
		
		ProfileDTO pDTO = new ProfileDTO();
		pDTO.setUserId(userId);
		pDTO.setPassword(password);
		pDTO.setEmail(email);
		pDTO.setBirth(birth);
		
		// set original file name and saved file name
	    String fileName = mr.getFilesystemName("ofile");
	    if (fileName != null) {
	    	// attached file exists
	    	String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
	    	String ext = fileName.substring(fileName.lastIndexOf("."));
	    	String newFileName = now + ext;
	    	
	    	// change file name
	    	File oldFile = new File(saveDirectory + File.separator + fileName);
	    	File newFile = new File(saveDirectory + File.separator + newFileName);
	    	oldFile.renameTo(newFile);
	    	
	    	pDTO.setOfile(fileName);
	    	pDTO.setSfile(newFileName);
	    	
	    	FileUtil.deleteFile(request, "/Uploads/Profile", prevSfile);
	    	
	    } else {
	    	pDTO.setOfile(prevOfile);
	    	pDTO.setSfile(prevSfile);
	    }
        
	    ProfileDAO pDAO = ProfileDAO.getInstance();
	    int result = pDAO.updateProfile(pDTO);
	    if (result > 0) {
	    	String url = "${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyProfile";
	    	request.setAttribute("url", url);
	    	request.setAttribute("msg", "정보가 수정되었습니다");
	    	
	    	return "/MyPage/Profile/profile.jsp";
	    	
	    } else {
	    	System.out.println("프로필 수정 중 Error");
	    	
	    	String url = "${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyProfile.profileEdit";
	    	request.setAttribute("url", url);
	    	
	    	return "/MyPage/Profile/profileEdit.jsp";
	    }
    }
}
