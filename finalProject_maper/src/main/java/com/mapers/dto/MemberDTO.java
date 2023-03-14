package com.mapers.dto;

public class MemberDTO {
	
	private String userid;
	private String password;
	private String email;
	private String birth;
	private int admins;
	
	/* licencekey 추후 회의 후 추가
	private String lisencekey; */
	
	/* blob 은 추후 회의 진행 후 추가
	private blob */

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
	
}
