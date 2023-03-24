package com.mapers.myPageRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdatePostFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("ProfileDTO") == null) {
			return "redirect:index.jsp";
		}
		
		String requestNum = request.getParameter("no");
		String userId = (String) session.getAttribute("userId");
		RequestDTO rDTO = RequestDAO.getInstance().viewRequest(Integer.parseInt(requestNum), userId);
		
		request.setAttribute("rDTO", rDTO);
		request.setAttribute("url", "/MyPage.Request/requestEdit.jsp");
		
		return "/MyPage.Common/layout.jsp";
	}

}
