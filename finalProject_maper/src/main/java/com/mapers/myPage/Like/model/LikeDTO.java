package com.mapers.myPage.Like.model;

public class LikeDTO {
	private int bookNum;
	private String bookImg;
	private String userId;
	private String title;
	private String postDate;
	
	public LikeDTO() {
		super();
	}
	
	public LikeDTO(String bookImg, String userId, String title, String postDate) {
		super();
		this.bookImg = bookImg;
		this.userId = userId;
		this.title = title;
		this.postDate = postDate;
	}

	public int getBookNum() {
		return bookNum;
	}
	
	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}
	
	public String getBookImg() {
		return bookImg;
	}

	public void setBookImg(String bookImg) {
		this.bookImg = bookImg;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
}
