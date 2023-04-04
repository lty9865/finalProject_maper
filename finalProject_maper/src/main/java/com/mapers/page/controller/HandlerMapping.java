package com.mapers.page.controller;

import com.mapers.common.Controller;
import com.mapers.page.service.PageDeleteController;
import com.mapers.page.service.PageEditController;
import com.mapers.page.service.PageEditViewController;
import com.mapers.page.service.PageListController;
import com.mapers.page.service.PageViewController;
import com.mapers.page.service.PageWriteController;
import com.mapers.page.service.PageWriteViewController;

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
			controller = new PageListController();
		else if (command.contentEquals("pageWriteView"))
			controller = new PageWriteViewController();
		else if (command.contentEquals("pageWrite"))
			controller = new PageWriteController();
		else if (command.contentEquals("pageView"))
			controller = new PageViewController();
		else if (command.contentEquals("pageEditView"))
			controller = new PageEditViewController();
		else if (command.contentEquals("pageEdit"))
			controller = new PageEditController();
		else if (command.contentEquals("pageDelete"))
			controller = new PageDeleteController();

		return controller;
	}
}
