package com.project.happyhouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author us13579
 *
 */

@ApiModel(value = "Board (게시글정보)", description = "글번호, 제목, 내용, 작성일, 조회수, 작성아이디, 태그번호 가진   Domain Class")
public class BoardDto {
	@ApiModelProperty(value = "글번호")
	private int articleno;
	@ApiModelProperty(value = "제목")
	private String title;
	@ApiModelProperty(value = "내용")
	private String content;
	@ApiModelProperty(value = "작성일")
	private String registDate;
	@ApiModelProperty(value = "조회수")
	private int view;
	@ApiModelProperty(value = "작성아이디")
	private String userId;
	@ApiModelProperty(value = "태그번호")
	private String tagNo;
	
	
	public int getArticleno() {
		return articleno;
	}
	public void setArticleno(int articleno) {
		this.articleno = articleno;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTagNo() {
		return tagNo;
	}
	public void setTagNo(String tagNo) {
		this.tagNo = tagNo;
	}
	@Override
	public String toString() {
		return "BoardDto [articleno=" + articleno + ", title=" + title + ", content=" + content + ", registDate=" + registDate
				+ ", view=" + view + ", userId=" + userId + ", tagNo=" + tagNo + "]";
	}
	
}
