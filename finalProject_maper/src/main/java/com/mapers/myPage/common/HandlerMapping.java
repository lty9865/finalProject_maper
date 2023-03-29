package com.mapers.myPage.common;

import com.mapers.common.Controller;
import com.mapers.myPage.Like.service.LikedBookViewHandler;
import com.mapers.myPage.Like.service.MyLikeHandler;
import com.mapers.myPage.Profile.service.CheckAccountHandler;
import com.mapers.myPage.Profile.service.MyProfileHandler;
import com.mapers.myPage.Profile.service.ProfileDeleteHandler;
import com.mapers.myPage.Profile.service.ProfileEditHandler;
import com.mapers.myPage.Request.service.MyRequestHandler;
import com.mapers.myPage.Request.service.RequestDeleteHandler;
import com.mapers.myPage.Request.service.RequestEditHandler;
import com.mapers.myPage.Request.service.RequestPostDetailHandler;
import com.mapers.myPage.Request.service.RequestPostHandler;

// 개별 컨트롤러 객체 생성을 전담하는 팩토리 클래스 : 생성은 한 번만!
public class HandlerMapping {
	private HandlerMapping() {}

	private static HandlerMapping instance = new HandlerMapping();
	
	public static HandlerMapping getInstance() {
		return instance;
	}

	// create(command) method
	public Controller create(String command) {
	    Controller controller = null;
	    // 1. /MyPageFront?command=MyProfile.checkRealAccount
	    // 2. /MyPageFront?command=MyRequest.requestPostDetailView
		// 3. /MyPageFront?command=MyLike.likeDetailView
	    String[] parts = command.split("\\."); // split the command into parts
	    String mainCommand = parts[0];
	    String subCommand = parts.length > 1 ? parts[1] : null;

	    if (mainCommand.contentEquals("MyProfile")) {
	        controller = new MyProfileHandler();
	    } else if (mainCommand.contentEquals("MyRequest")) {
	        controller = new MyRequestHandler();
	    } else if (mainCommand.contentEquals("MyLike")) {
	        controller = new MyLikeHandler();
	    }

	    // Check if subcommand is specified
	    if (subCommand != null) {
	        if (mainCommand.contentEquals("MyProfile")) {
	            if (subCommand.contentEquals("profileEdit")) {
	                controller = new ProfileEditHandler();
	            } else if (subCommand.contentEquals("profileDelete")) {
	                controller = new ProfileDeleteHandler();
	            }  else if (subCommand.contentEquals("checkRealAccount")) {
	                controller = new CheckAccountHandler();
	            }
	        } else if (mainCommand.contentEquals("MyRequest")) {
	            if (subCommand.contentEquals("requestPost")) {
	                controller = new RequestPostHandler();
	            } else if (subCommand.contentEquals("requestEdit")) {
	                controller = new RequestEditHandler();
	            } else if (subCommand.contentEquals("requestDelete")) {
	                controller = new RequestDeleteHandler();
	            } else if (subCommand.contentEquals("requestPostDetail")) {
	                controller = new RequestPostDetailHandler();
	            }
	        } else if (mainCommand.contentEquals("MyLike")) {
	            if (subCommand.contentEquals("likedBookView")) {
	                controller = new LikedBookViewHandler();
	            }
	        } 
	    }

	    return controller;
	}
}
