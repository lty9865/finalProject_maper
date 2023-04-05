package com.mapers.myPage.Request.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mapers.common.Controller;
import com.mapers.myPage.Request.model.RequestDAO;
import com.mapers.myPage.Request.model.RequestDTO;

public class RequestPostProcessHandler implements Controller {
    private static final int RECORDS_PER_PAGE = 10;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        String sessionUserId = (String) session.getAttribute("userId");
        session.setAttribute("userId", sessionUserId);

        String title = request.getParameter("title");
        String userId = request.getParameter("userId");
        String content = request.getParameter("content");

        Date postDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
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

            ArrayList<RequestDTO> requestList = RequestDAO.getInstance().getAllList(pageNo, postsPerPage, sessionUserId);

            int totalPages = (int) Math.ceil((double) currentTotalCount / RECORDS_PER_PAGE);

            request.setAttribute("requestList", requestList);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("page", page);

            return "/MyPage/Request/requestList.jsp";
        } else {
            request.setAttribute("error", "문의가 올라가는 데 오류가 발생했습니다. 다시 시도주시길 바랍니다.");

            return "/MyPage/Request/requestPost.jsp";
        }
    }
}


