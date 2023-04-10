package com.mapers.myPage.Request.model;

public class ConversationDTO {
	private int id;
    private String title;
    private String content;
    private int cycle;
    private int cycleId;
    
    public ConversationDTO() {
		super();
	}
    
	public ConversationDTO(int id, String title, String content, int cycle, int cycleId) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.cycle = cycle;
		this.cycleId = cycleId;
	}
	
    public int getId() {
		return id;
	}
	
    public void setId(int id) {
		this.id = id;
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
	
    public int getCycle() {
		return cycle;
	}
	
    public void setCycle(int cycle) {
		this.cycle = cycle;
	}
	
    public int getCycleId() {
		return cycleId;
	}
	
    public void setCycleId(int cycleId) {
		this.cycleId = cycleId;
	}
}

