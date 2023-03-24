package com.mapers.myPageLike;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdatePostController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("ProfileDTO") == null) {
			return "redirect:index.jsp";
		}
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		LikeDTO rDTO = new LikeDTO();
		rDTO.setTitle(title);
		rDTO.setContent(content);
		
		LikeDAO.getInstance().editRequest(rDTO);
		
		String path = "redirect:front?command=PostDetailNoHits&requestNum=" + rDTO.getRequestNum();
		
		return path;
	}

}
