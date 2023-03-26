package com.mapers.myPageLike.model;

import com.mapers.myPageProfile.model.ProfileDTO;

public class LikeDTO {
	private int requestNum;
	private String userId;
	private String title;
	private String content;
	private int status; // 0: 답변 완료, 1: 진행 중, default = 0;
	private String postDate;
	private String licenseKey;
	private ProfileDTO pDTO;
	
	public LikeDTO() {
		super();
	}
	
	public LikeDTO(int requestNum, String userId, String title, String content, int status, String postDate) {
		this.requestNum = requestNum;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.status = status;
		this.postDate = postDate;
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
	
	public ProfileDTO getProfileDTO() {
		return pDTO;
	}
	
	public void setProfileDTO(ProfileDTO pDTO) {
		this.pDTO = pDTO;
	}

	@Override
	public String toString() {
		return "RequestDTO [requestNum=" + requestNum + ", userId=" + userId + ", title=" + title + ", content="
				+ content + ", status=" + status + ", postDate=" + postDate + ", licenseKey=" + licenseKey + "]";
	}
}
