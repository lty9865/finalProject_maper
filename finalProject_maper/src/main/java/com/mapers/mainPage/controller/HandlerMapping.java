package com.mapers.mainPage.controller;

import com.mapers.common.Controller;
import com.mapers.mainPage.service.MainPageController;

public class HandlerMapping {
	private static HandlerMapping instance = new HandlerMapping();

	private HandlerMapping() {
	};

	public static HandlerMapping getInstance() {
		return instance;
	}

	public Controller create(String command) {
		Controller controller = null;

		if (command.contentEquals("main"))
			// 게시물 리스트(command=home)
			controller = new MainPageController();

		return controller;
	}
}
