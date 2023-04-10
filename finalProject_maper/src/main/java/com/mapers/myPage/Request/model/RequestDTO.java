package com.mapers.myPage.Request.model;

public class RequestDTO {
	private int requestNum;
	private String userId;
	private String title;
	private String content;
	private int status; // 0: 답변 완료, 1: 진행 중, default = 1;
	private String postDate;
	private String licenseKey;
	
	public RequestDTO() {
		super();
	}
	
	public RequestDTO(int requestNum, String userId, String title, String content, int status, String postDate,
			String licenseKey) {
		super();
		this.requestNum = requestNum;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.status = status;
		this.postDate = postDate;
		this.licenseKey = licenseKey;
	}

	public int getRequestNum() {
		return requestNum;
	}

	public void setRequestNum(int requestNum) {
		this.requestNum = requestNum;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public String getLicenseKey() {
		return licenseKey;
	}

	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}
}
