package com.mapers.myPage.Request.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapers.common.Controller;
import com.mapers.myPage.Request.model.ConversationDTO;
import com.mapers.myPage.Request.model.ConversationDAO;

public class ConversationHandler implements Controller {
	 
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
		
        String postId = request.getParameter("postId");
        ConversationDAO conversationDAO = ConversationDAO.getInstance();
        List<ConversationDTO> conversations = conversationDAO.getConversationsByPostId(postId);
        
        request.setAttribute("conversations", conversations);
        return "/MyPage/Request/requestConversation.jsp";
    }
}
