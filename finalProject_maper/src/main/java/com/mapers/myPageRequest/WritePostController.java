package com.mapers.myPageRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WritePostController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		
		//로그인 여부 체크 + POST방식으로 form을 보냈는지 체크
		if(session==null||session.getAttribute("memberVO")==null ||
				request.getMethod().equals("POST")==false) {
			return "redirect:index.jsp";
		}
		
		/** 게시글 등록 **/
		// 1. requestPost.jsp에서 title과 content 받아옴
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// 2. RequestDTO에 새로 입력받은 글로 새로운 RequestDTO 만듦
		RequestDTO rDTO = new RequestDTO();
		rDTO.setTitle(title);
		rDTO.setContent(content);
		
		// 3. session에 있는 licenseKey도 rDTO에 넣어줌
		// (다운캐스팅 필요)
		rDTO.setLicenseKey((String)session.getAttribute("licenseKey")); 
		
		// 4. 로그인 정보와 글 정보를 담은 requestDTO를 작성
		// 이 과정에서 자동으로 no와 date가 requestDTO에 입력됨
		RequestDAO.getInstance().editRequest(rDTO);
		
		// 5. requestView-detail(문의사항 상세보기)에
		// RequestDTO의 requestNum 정보를 담아 보내기(redirect 방식으로)
		// -> 이유? 해당 게시물의 requestNum 정보를 받기 위해서
		// **path에 담아 return함 **
		String path = "redirect:front?command=PostDetailNoHits&requestNum" + rDTO.getRequestNum();
		
		return path;
	}

}
