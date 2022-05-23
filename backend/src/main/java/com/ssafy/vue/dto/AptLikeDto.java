package com.ssafy.vue.dto;

public class AptLikeDto {
	int no;
	String user_id;
	String houseinfo_no;
	String registDate;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getHouseinfo_no() {
		return houseinfo_no;
	}
	public void setHouseinfo_no(String houseinfo_no) {
		this.houseinfo_no = houseinfo_no;
	}
	public String getRegistDate() {
		return registDate;
	}
	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}
	
	@Override
	public String toString() {
		return "AptLikeDto [no=" + no + ", user_id=" + user_id + ", houseinfo_no=" + houseinfo_no + ", registDate="
				+ registDate + "]";
	}
}
