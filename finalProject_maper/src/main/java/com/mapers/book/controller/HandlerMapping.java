package com.mapers.book.controller;

import com.mapers.book.service.BookDeleteController1;
import com.mapers.book.service.BookEditController1;
import com.mapers.book.service.BookEditViewController;
import com.mapers.book.service.BookListController1;
import com.mapers.book.service.BookViewController1;
import com.mapers.book.service.BookWriteController1;
import com.mapers.book.service.BookWriteViewController;
import com.mapers.common.Controller;

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

		if (command.contentEquals("bookList"))
			// 게시물 리스트(command=home)
			controller = new BookListController1();
		else if (command.contentEquals("bookWriteView"))
			controller = new BookWriteViewController();
		else if (command.contentEquals("bookWrite"))
			controller = new BookWriteController1();
		else if (command.contentEquals("bookView"))
			controller = new BookViewController1();
		else if (command.contentEquals("bookEditView"))
			controller = new BookEditViewController();
		else if (command.contentEquals("bookEdit"))
			controller = new BookEditController1();
		else if (command.contentEquals("bookDelete"))
			controller = new BookDeleteController1();

		return controller;
	}
}
