package com.mapers.myPage.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.SignUp.MemberDTO;
import com.mapers.common.Controller;
import com.mapers.myPage.Admins.model.AdminsDAO;
import com.mapers.myPage.Profile.model.ProfileDAO;
import com.mapers.myPage.Profile.model.ProfileDTO;

public class MyPageStarter implements Controller {
	private static final int RECORDS_PER_PAGE = 10;
	
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        String nextPage;
        if (userId.equals("admins_1")) {
        	request.setCharacterEncoding("UTF-8");

            AdminsDAO aDAO = AdminsDAO.getInstance();

            String[] userIdPart = userId.split("_");
            
            for (int i=0; i<userIdPart.length; i++) {
            	System.out.println(userIdPart[i] + " ");
            }
            
            session.setAttribute("userId", userId);

            Integer totalPostCount = (Integer) session.getAttribute("totalPostCount");

            String pageStr = request.getParameter("page");
            int page = 1;

            if (pageStr != null && !pageStr.isEmpty()) {
                page = Integer.parseInt(pageStr);
            }

            int currentTotalCount = aDAO.getMemberTotalPostCount();
            if (totalPostCount == null || totalPostCount != currentTotalCount) {
                session.setAttribute("totalPostCount", currentTotalCount);
            }

            int pageNo = page;
            int postsPerPage = RECORDS_PER_PAGE;

            List<MemberDTO> mDTO = aDAO.getAllMemberList(pageNo, postsPerPage);
            aDAO.closeAll();
            
            int totalPages = (int) Math.ceil((double) currentTotalCount / postsPerPage);
            
            request.setAttribute("memberBoard", mDTO);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("page", page);

            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0
            response.setHeader("Expires", "0"); // Proxies

            request.setAttribute("url", "${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins.memberBoard");

            request.setAttribute("selectedMenuItem", "AdminsMemberBoard");
        	
            nextPage = "/MyPage/Admins/adminsMemberList.jsp";
        
        } else {
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
        	
            nextPage = "/MyPage/Profile/profile.jsp";
        }

        return nextPage;
    }
}
