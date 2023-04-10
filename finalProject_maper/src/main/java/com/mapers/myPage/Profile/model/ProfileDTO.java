package com.mapers.myPage.Profile.model;

public class ProfileDTO {
	private String sfile;
	private String ofile;
    private String userId;
    private String password;
    private String email;
    private String birth;
    private int admins;
    private String licenseKey;

    public ProfileDTO() {
        super();
    }
    
	public ProfileDTO(String sfile, String ofile, String userId, String password, String email, String birth,
			int admins, String licenseKey) {
		super();
		this.sfile = sfile;
		this.ofile = ofile;
		this.userId = userId;
		this.password = password;
		this.email = email;
		this.birth = birth;
		this.admins = admins;
		this.licenseKey = licenseKey;
	}

    public ProfileDTO(String id, String password) {
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


	public String getSfile() {
		return sfile;
	}


	public void setSfile(String sfile) {
		this.sfile = sfile;
	}


	public String getOfile() {
		return ofile;
	}


	public void setOfile(String ofile) {
		this.ofile = ofile;
	}


	public String getLicenseKey() {
		return licenseKey;
	}
}
