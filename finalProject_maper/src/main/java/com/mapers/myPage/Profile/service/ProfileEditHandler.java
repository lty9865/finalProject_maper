package com.mapers.myPage.Profile.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.mapers.common.Controller;
import com.mapers.myPage.Profile.model.ProfileDAO;
import com.mapers.myPage.Profile.model.ProfileDTO;

public class ProfileEditHandler implements Controller {

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        
        String userId = (String) session.getAttribute("userId");
        String password = request.getParameter("password");
        String imagePath = null;
        
        Part filePart = request.getPart("image"); // get uploaded file part
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = getFileName(filePart); // get uploaded file name
            String uploadPath = "C:/uploads/"; // set upload directory path
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs(); // create directory if it doesn't exist
            }
            filePart.write(uploadPath + fileName); // save the uploaded file to the upload directory
            imagePath = "/uploads/" + fileName; // set the image path to the uploaded file path
        }
        
        ProfileDTO pDTO = ProfileDAO.getInstance().getProfile(userId, password);
        
        request.setAttribute("url", "/MyPageFront?command=MyProfile.profileEdit");
        
        return "redirect:/MyPageCommon/layout.jsp";
    }

	// get uploaded file name
	private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] elements = contentDisposition.split(";");
        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.indexOf("=") + 1).trim().replace("\"", "");
            }
        }
		return contentDisposition;
	}
}
