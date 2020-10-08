package kr.co.boardReply.dto;

import java.util.Date;

public class ItBoardReply_Dto {
	private int commentsNo; //댓글 번호
	private int postNo; //댓글 다는 글 번호
	private String customerNickname; //댓글 작성자
	private int customerNo; //작성자 고유 번호
	private String comments; //내용
	private Date postDate; //작성일
	public int getCommentsNo() {
		return commentsNo;
	}
	public void setCommentsNo(int commentsNo) {
		this.commentsNo = commentsNo;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public String getCustomerNickname() {
		return customerNickname;
	}
	public void setCustomerNickname(String customerNickname) {
		this.customerNickname = customerNickname;
	}
	public int getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(int customerNo) {
		this.customerNo = customerNo;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
}
