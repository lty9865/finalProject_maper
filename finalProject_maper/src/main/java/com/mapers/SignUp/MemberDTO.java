package com.mapers.SignUp;

public class MemberDTO {
	private String userId;
	private String password;
	private String confirmPassword;
	private String email;
	private String birth;
	private int admins;
	private String licenseKey;

	
	public MemberDTO() {
		super();
	}

	public MemberDTO(String userId, String password, String confirmPassword, String email, String birth, int admins,
			String licenseKey) {
		super();
		this.userId = userId;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.email = email;
		this.birth = birth;
		this.admins = admins;
		this.licenseKey = licenseKey;
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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

	public String getLicenseKey() {
		return licenseKey;
	}

	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}

}