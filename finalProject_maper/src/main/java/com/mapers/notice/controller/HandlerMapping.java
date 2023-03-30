package com.mapers.notice.controller;

import com.mapers.common.Controller;
import com.mapers.notice.service.NoticeDeleteController;
import com.mapers.notice.service.NoticeEditController;
import com.mapers.notice.service.NoticeEditViewController;
import com.mapers.notice.service.NoticeListController;
import com.mapers.notice.service.NoticeViewController;
import com.mapers.notice.service.NoticeWriteController;
import com.mapers.notice.service.NoticeWriteViewController;

public class HandlerMapping {
	private static HandlerMapping instance = new HandlerMapping();

	private HandlerMapping() {
	};

	public static HandlerMapping getInstance() {
		return instance;
	}

	public Controller create(String command) {
		Controller controller = null;

		if (command.contentEquals("list"))
			// 게시물 리스트(command=home)
			controller = new NoticeListController();
		else if (command.contentEquals("write"))
			controller = new NoticeWriteController();
		else if (command.contentEquals("writeView"))
			controller = new NoticeWriteViewController();
		else if (command.contentEquals("view"))
			controller = new NoticeViewController();
		else if (command.contentEquals("edit"))
			controller = new NoticeEditController();
		else if (command.contentEquals("editView"))
			controller = new NoticeEditViewController();
		else if (command.contentEquals("delete"))
			controller = new NoticeDeleteController();

		return controller;
	}
}
