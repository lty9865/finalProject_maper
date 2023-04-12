package com.mapers.myPage.Like.model;

public class LikeDTO {
	private int listNum;
	private String userId;
	private int bookNum;
	private String title;
	private String ofileBook;
	private String sfileBook;
	private int pageNo;
	private String postDate;
	
	public LikeDTO() {
		super();
	}
	
	public LikeDTO(int listNum, String userId, int bookNum, String title, String ofileBook, String sfileBook, int pageNo, String postDate) {
		super();
		this.listNum = listNum;
		this.userId = userId;
		this.bookNum = bookNum;
		this.title = title;
		this.ofileBook = ofileBook;
		this.sfileBook = sfileBook;
		this.pageNo = pageNo;
		this.postDate = postDate;
	}

	public int getListNum() {
		return listNum;
	}

	public void setListNum(int listNum) {
		this.listNum = listNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getBookNum() {
		return bookNum;
	}

	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}

	public String getOfileBook() {
		return ofileBook;
	}

	public void setOfileBook(String ofileBook) {
		this.ofileBook = ofileBook;
	}

	public String getSfileBook() {
		return sfileBook;
	}

	public void setSfileBook(String sfileBook) {
		this.sfileBook = sfileBook;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	

}
