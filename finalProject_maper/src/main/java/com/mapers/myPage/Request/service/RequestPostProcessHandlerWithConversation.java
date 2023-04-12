package com.mapers.myPage.Request.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;
import com.mapers.myPage.Request.model.RequestDAO;
import com.mapers.myPage.Request.model.RequestDTO;

public class RequestPostProcessHandlerWithConversation implements Controller {
    private static final int RECORDS_PER_PAGE = 10;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();

        String userId = (String) session.getAttribute("userId");
        session.setAttribute("userId", userId);

        String title = request.getParameter("title");
        String originalTitle = request.getParameter("originalTitle");

        if (originalTitle != null && !originalTitle.isEmpty()) {
            if (originalTitle.startsWith("RE:")) {
                Pattern pattern = Pattern.compile("^RE(#[0-9]+)?: ");
                Matcher matcher = pattern.matcher(originalTitle);
                if (matcher.find()) {
                    title = matcher.replaceFirst("");
                } else {
                    title = originalTitle;
                }
                title = "RE: " + title;
            } else {
                title = originalTitle;
            }
        }
        
        String content = request.getParameter("content");

        Date postDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String postDateString = dateFormat.format(postDate);

        RequestDTO rDTO = new RequestDTO();
        rDTO.setTitle(title);
        rDTO.setContent(content);
        rDTO.setUserId(userId);
        rDTO.setPostDate(postDateString);

        int result = RequestDAO.getInstance().insertRequest(rDTO);

        if (result > 0) {
            request.setAttribute("message", "문의가 올라갔습니다.");

            int page = 1;

            RequestDAO rDAO = RequestDAO.getInstance();
            int currentTotalCount = rDAO.getTotalPostCount();
            session.setAttribute("totalPostCount", currentTotalCount);

            int pageNo = page;
            int postsPerPage = RECORDS_PER_PAGE;

            ArrayList<RequestDTO> requestList = RequestDAO.getInstance().getAllList(pageNo, postsPerPage, userId);

            int totalPages = (int) Math.ceil((double) currentTotalCount / RECORDS_PER_PAGE);

            request.setAttribute("requestList", requestList);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("page", page);
            request.setAttribute("selectedMenuItem", "MyRequest");
            
            return "/MyPage/Request/requestList.jsp";
        } else {
            request.setAttribute("error", "문의가 올라가는 데 오류가 발생했습니다. 다시 시도주시길 바랍니다.");
            request.setAttribute("selectedMenuItem", "MyRequest");
            
            return "/MyPage/Request/requestPost.jsp";
        }
    }
}
