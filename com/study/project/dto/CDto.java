package com.study.project.dto;

import java.sql.Date;

public class CDto {
	private int cNum;        // 댓글 글번호
    private int bId;        // 게시글 번호
    private String cId;        // 댓글 작성자
    private Date cDate;        // 댓글 작성일
    private int cParent;        // 부모글
    private String cContent;    // 댓글 내용
    private int cLevel;        // 댓글- 답변글 깊이
    
	public CDto() {
		super();
	}
	public CDto(int cNum, int bId, String cId, Date cDate, int cParent, String cContent, int cLevel) {
		super();
		this.cNum = cNum;
		this.bId = bId;
		this.cId = cId;
		this.cDate = cDate;
		this.cParent = cParent;
		this.cContent = cContent;
		this.cLevel = cLevel;
	}
	public int getcNum() {
		return cNum;
	}
	public void setcNum(int cNum) {
		this.cNum = cNum;
	}
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public Date getcDate() {
		return cDate;
	}
	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}
	public int getcParent() {
		return cParent;
	}
	public void setcParent(int cParent) {
		this.cParent = cParent;
	}
	public String getcContent() {
		return cContent;
	}
	public void setcContent(String cContent) {
		this.cContent = cContent;
	}
	public int getcLevel() {
		return cLevel;
	}
	public void setcLevel(int cLevel) {
		this.cLevel = cLevel;
	}
}
