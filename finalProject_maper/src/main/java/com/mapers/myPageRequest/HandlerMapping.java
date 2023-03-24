package com.mapers.myPageRequest;

public class HandlerMapping {
	private static HandlerMapping instance = new HandlerMapping();
	private HandlerMapping() {};
	public static HandlerMapping getInstance() {
		return instance;
	}
	
	public Controller create(String command) {
		Controller controller = null;
		
		if (command.contentEquals("home"))
			// 게시물 리스트(command=home)
			controller = new HomeController();
		else if (command.contentEquals("logout"))
			controller = new LogoutController();
		else if (command.contentEquals("PostDetail"))
			// 게시물 상세보기: 본인 외의 게시물을 보았을 때, 조회수가 증가함(마이 페이지에선 조회수 미적용)
			controller = new PostDetailController();
		else if (command.contentEquals("PostDetailNoHits"))
			// 게시물 상세보기: 본인의 게시물을 보았을 때, 조회수가 증가하지 않음(마이 페이지에선 조회수 미적용)
			controller = new PostDetailNoHitsController();
		else if (command.contentEquals("WritePostForm"))
			controller = new WritePostFormController();
		else if (command.contentEquals("WritePost"))
			controller = new WritePostController();
		else if (command.contentEquals("RemovePost"))
			controller = new RemovePostController();
		else if (command.contentEquals("UpdatePostForm"))
			controller = new UpdatePostController();
		else if (command.contentEquals("UpdatePost"))
			controller = new UpdatePostController();
		return controller;
	}
}
