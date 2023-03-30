package com.mapers.notice.model;

public class NoticeDTO {
	private int idx;
	private String title;
	private String content;
	private int visitCount;
	private String postDate;

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
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

	public int getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		postDate = postDate.substring(0, 10);
		this.postDate = postDate;
	}


}
