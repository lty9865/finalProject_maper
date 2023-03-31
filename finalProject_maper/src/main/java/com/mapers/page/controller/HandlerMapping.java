package com.mapers.page.controller;

import com.mapers.common.Controller;
import com.mapers.page.service.PageListController1;
import com.mapers.page.service.PageViewController1;

// 개별 컨트롤러 객체 생성을 전담하는 팩토리 클래스 : 생성은 한 번만!
public class HandlerMapping {
	private HandlerMapping() {
	}

	private static HandlerMapping instance = new HandlerMapping();

	public static HandlerMapping getInstance() {
		return instance;
	}

	// create(command) method
	public Controller create(String command) {
		Controller controller = null;

		// 게시물 리스트(command=home)
		if (command.contentEquals("pageList"))
			controller = new PageListController1();
		else if (command.contentEquals("pageWriteView")) {}
		else if (command.contentEquals("pageWrite")) {}
		else if (command.contentEquals("pageView"))
			controller = new PageViewController1();
		else if (command.contentEquals("pageEditView")) {}
		else if (command.contentEquals("pageEdit")) {}
		else if (command.contentEquals("pageDelete")) {}

		return controller;
	}
}
