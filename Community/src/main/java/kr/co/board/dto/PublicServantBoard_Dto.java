package kr.co.board.dto;

import java.util.Date;

public class PublicServantBoard_Dto {
	
	private int postNo; //글번호 
    private String postTitle; //문의글제목 
    private String customerNickname; //유저닉네임 
    private Date postDate; //작성날짜 
    private String contents; //글본문 
    private int commentsCount;  //총댓글수 
    private String postCategory;   //글분류 
    private int customerNo; //유저번호
    private int viewCount; //조회수
	private String postDateStr; //작성하고 시간 얼마나 지났는지 
    
    public String getPostDateStr() {
		return postDateStr;
	}
	public void setPostDateStr(String postDateStr) {
		this.postDateStr = postDateStr;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getCustomerNickname() {
		return customerNickname;
	}
	public void setCustomerNickname(String customerNickname) {
		this.customerNickname = customerNickname;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getCommentsCount() {
		return commentsCount;
	}
	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}
	public String getPostCategory() {
		return postCategory;
	}
	public void setPostCategory(String postCategory) {
		this.postCategory = postCategory;
	}
	public int getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(int customerNo) {
		this.customerNo = customerNo;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
    

}
