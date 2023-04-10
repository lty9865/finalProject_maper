package com.mapers.report;

public class ReportDTO {
	private int reportNum;
	private int bookNum;
	private String userId;
	private int count;
	private String bookTitle;

	public ReportDTO() {
		super();
	}
	
	public ReportDTO(int reportNum, int bookNum, String userId, int count, String bookTitle) {
		super();
		this.reportNum = reportNum;
		this.bookNum = bookNum;
		this.userId = userId;
		this.count = count;
		this.bookTitle = bookTitle;
	}
	
	public int getReportNum() {
		return reportNum;
	}

	public void setReportNum(int reportNum) {
		this.reportNum = reportNum;
	}

	public int getBookNum() {
		return bookNum;
	}

	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

}
