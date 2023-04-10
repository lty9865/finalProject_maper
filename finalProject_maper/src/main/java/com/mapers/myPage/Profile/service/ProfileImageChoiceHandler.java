package com.mapers.myPage.Profile.service;

import java.io.File;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.mapers.common.Controller;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class ProfileImageChoiceHandler implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Handle basic image choice
        String basicImageUrl = request.getParameter("basicImageUrl");
        if (basicImageUrl != null && !basicImageUrl.isEmpty()) {
            // Save the basic image URL as the new profile image
            // You can implement this logic based on your application's requirements
            saveNewProfileImage(basicImageUrl);
            return "PROFILE_UPDATE_SUCCESS_PAGE";
        }

        // Handle file upload
        Part filePart = request.getPart("profileImage");
        if (filePart != null) {
            String fileName = getFileName(filePart);
            String uploadPath = "YOUR_UPLOAD_DIRECTORY";
            File file = new File(uploadPath, fileName);

            filePart.write(file.getAbsolutePath());

            // Save the uploaded image as the new profile image
            // You can implement this logic based on your application's requirements
            saveNewProfileImage(file.getAbsolutePath());
            return "PROFILE_UPDATE_SUCCESS_PAGE";
        }

        return "ERROR_PAGE";
    }

    private String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private void saveNewProfileImage(String imageUrl) {
        // Implement the logic to save the new profile image
        // This could involve updating the user's record in the database, etc.
    }
}
