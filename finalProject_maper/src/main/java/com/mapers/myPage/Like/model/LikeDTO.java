package com.mapers.myPage.Like.model;

public class LikeDTO {
	private int bookNum;
	private String bookImg;
	private int bookPages;
	private String userId;
	private String title;
	private String content;
	private String postDate;
	
	public LikeDTO() {
		super();
	}
	
	public LikeDTO(String bookImg, int bookPages, String userId, String title, String content, String postDate) {
		super();
		this.bookImg = bookImg;
		this.bookPages = bookPages;
		this.userId = userId;
		this.title = title;
		this.content = content;
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

	public int getBookPages() {
		return bookPages;
	}

	public void setBookPages(int bookPages) {
		this.bookPages = bookPages;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	@Override
	public String toString() {
		return "LikeDTO [bookImg=" + bookImg + ", bookPages=" + bookPages + ", userId=" + userId + ", title=" + title
				+ ", content=" + content + ", postDate=" + postDate + "]";
	}
}
