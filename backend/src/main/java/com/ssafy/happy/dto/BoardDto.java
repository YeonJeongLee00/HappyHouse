package com.ssafy.happy.dto;



public class BoardDto {
	
	private int no;
	private String title;
	private String content;
	private String user_id;
	private String registDate;
	private int view;
	private String tag_no;
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
	public String getRegistDate() {
		return registDate;
	}
	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String use_id) {
		this.user_id = use_id;
	}
	public String getTag_no() {
		return tag_no;
	}
	public void setTag_no(String tag_no) {
		this.tag_no = tag_no;
	}
	
	
}
