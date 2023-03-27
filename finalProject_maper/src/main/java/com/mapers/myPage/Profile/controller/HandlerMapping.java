package com.mapers.myPage.Profile.controller;

import com.mapers.myPage.Profile.service.HomeController;
import com.mapers.myPage.Profile.service.LogoutController;
import com.mapers.myPage.Profile.service.PostDetailNoHitsController;
import com.mapers.myPage.Profile.service.RemoveMemberController;
import com.mapers.myPage.Profile.service.UpdateProfileController;
import com.mapers.myPage.Profile.service.UpdateProfileFormController;

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
		else if (command.contentEquals("PostDetailNoHits"))
			// 게시물 상세보기: 본인의 게시물을 보았을 때, 조회수가 증가하지 않음(마이 페이지에선 조회수 미적용)
			controller = new PostDetailNoHitsController();
		else if (command.contentEquals("RemovePost"))
			controller = new RemoveMemberController();
		else if (command.contentEquals("UpdatePostForm"))
			controller = new UpdateProfileFormController();
		else if (command.contentEquals("UpdatePost"))
			controller = new UpdateProfileController();
		return controller;
	}
}
