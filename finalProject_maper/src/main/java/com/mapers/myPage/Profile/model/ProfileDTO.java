package com.mapers.myPage.Profile.model;

public class ProfileDTO {
	private String profileImg;
	private String userId;
	private String password;
	private String email;
	private String birth;
	private int admins;
	private String licenseKey;

	public ProfileDTO() {
		super();
	}
	
	public ProfileDTO(String userId, String password, String email, String birth, int admins, String licenseKey) {
		super();
		this.userId = userId;
		this.password = password;
		this.email = email;
		this.birth = birth;
		this.admins = admins;
		this.licenseKey = licenseKey;
	}

	public ProfileDTO(String id, String password2) {
	}
	
	public ProfileDTO(String userId, String password, String email, String birth, String profileImg) {
	}

	public String getProfileImg() {
		return profileImg;
	}
	
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public int getAdmins() {
		return admins;
	}

	public void setAdmins(int admins) {
		this.admins = admins;
	}

	public String getLicensekey() {
		return licenseKey;
	}
	
	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}
}
