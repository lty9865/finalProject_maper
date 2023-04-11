package com.mapers.myPage.common;

import com.mapers.common.Controller;
import com.mapers.myPage.Admins.service.AdminsMemberHandler;
import com.mapers.myPage.Admins.service.AdminsReportDeleteHandler;
import com.mapers.myPage.Admins.service.AdminsReportHandler;
import com.mapers.myPage.Admins.service.AdminsRequestHandler;
import com.mapers.myPage.Admins.service.AdminsRequestReplyDeleteHandler;
import com.mapers.myPage.Admins.service.AdminsRequestReplyEditHandler;
import com.mapers.myPage.Admins.service.AdminsRequestReplyEditProcessHandler;
import com.mapers.myPage.Admins.service.AdminsRequestReplyHandler;
import com.mapers.myPage.Admins.service.AdminsRequestReplyProcessHandler;
import com.mapers.myPage.Like.service.LikedBookViewHandler;
import com.mapers.myPage.Like.service.MyLikeHandler;
import com.mapers.myPage.Profile.service.CheckRealAccountHandler;
import com.mapers.myPage.Profile.service.MyProfileHandler;
import com.mapers.myPage.Profile.service.ProfileDeleteHandler;
import com.mapers.myPage.Profile.service.ProfileEditHandler;
import com.mapers.myPage.Profile.service.ProfileImageChoiceHandler;
import com.mapers.myPage.Request.service.MyRequestHandler;
import com.mapers.myPage.Request.service.RequestDeleteHandler;
import com.mapers.myPage.Request.service.RequestEditHandler;
import com.mapers.myPage.Request.service.RequestEditProcessHandler;
import com.mapers.myPage.Request.service.RequestPostHandler;
import com.mapers.myPage.Request.service.RequestPostProcessHandler;
import com.mapers.myPage.Request.service.RequestPostViewHandler;
import com.mapers.myPage.Request.service.RequestTitleClickHandler;

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

		String[] parts = command.split("\\."); // split the command into parts
		String mainCommand = parts[0];
		String subCommand = parts.length > 1 ? parts[1] : null;
		System.out.println(mainCommand + " " + subCommand);
		
		// Check if subcommand is specified
		if (subCommand != null) {
			if (mainCommand.contentEquals("MyProfile")) {
				if (subCommand.contentEquals("profileEdit")) {
					controller = new ProfileEditHandler();
				} else if (subCommand.contentEquals("profileDelete")) {
					controller = new ProfileDeleteHandler();
				} else if (subCommand.contentEquals("checkRealAccount")) {
					controller = new CheckRealAccountHandler();
				} else if (subCommand.contentEquals("profileImageChoice")) {
					controller = new ProfileImageChoiceHandler();
				} else {
					controller = new MyProfileHandler();
				}
			} else if (mainCommand.contentEquals("MyRequest")) {
				if (subCommand.contentEquals("requestPost")) {
					controller = new RequestPostHandler();
				} else if (subCommand.contentEquals("requestPostProcess")) {
					controller = new RequestPostProcessHandler();
				} else if (subCommand.contentEquals("requestEdit")) {
					controller = new RequestEditHandler();
				} else if (subCommand.contentEquals("requestEditProcess")) {
					controller = new RequestEditProcessHandler();
				} else if (subCommand.contentEquals("requestDelete")) {
					controller = new RequestDeleteHandler();
				} else if (subCommand.contentEquals("requestPostView")) {
					controller = new RequestPostViewHandler();
				} else if (subCommand.contentEquals("requestTitleClick")) {
					controller = new RequestTitleClickHandler();
				} else {
					controller = new MyRequestHandler();
				}
			} else if (mainCommand.contentEquals("MyLike")) {
				if (subCommand.contentEquals("likedBookView")) {
					controller = new LikedBookViewHandler();
				} else {
					controller = new MyLikeHandler();
				}
			} else if (mainCommand.contentEquals("Admins")) {
				if (subCommand.contentEquals("reportBoard")) {
					controller = new AdminsReportHandler();
				} else if (subCommand.contentEquals("reportDelete")) {
					controller = new AdminsReportDeleteHandler();
				} else if (subCommand.contentEquals("requestBoard")) {
					controller = new AdminsRequestHandler();
				} else if (subCommand.contentEquals("requestReply")) {
					controller = new AdminsRequestReplyHandler();
				} else if (subCommand.contentEquals("requestReplyProcess")) {
					controller = new AdminsRequestReplyProcessHandler();
				} else if (subCommand.contentEquals("requestReplyEdit")) {
					controller = new AdminsRequestReplyEditHandler();
				} else if (subCommand.contentEquals("requestReplyEditProcess")) {
					controller = new AdminsRequestReplyEditProcessHandler();
				} else if (subCommand.contentEquals("requestReplyDelete")) {
					controller = new AdminsRequestReplyDeleteHandler();
				} else {
					controller = new AdminsMemberHandler();
				}
			}
			
		} else {
			if (mainCommand.contentEquals("StartMyPage")) {
				controller = new MyPageStarter();
			} else if (mainCommand.contentEquals("Admin")) {
				controller = new AdminsMemberHandler();
			} else if (mainCommand.contentEquals("MyLike")) {
				controller = new MyLikeHandler();
			} else if (mainCommand.contentEquals("MyRequest")) {
				controller = new MyRequestHandler();
			} else {
				controller = new MyProfileHandler();
			}
		}

		return controller;
	}
}
