package com.mapers.signUp;

public class MemberVO {
	private String userid;
	private String password;
	private String confirmPassword;
	private String email;
	private String birth;
	private int admins;
	private String licenseKey;
	
	
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
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