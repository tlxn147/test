package kr.co.boardReply.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class LanguageBoardReply_Dto {
	
	private int commentsNo; //댓글 번호
	private int postNo; //댓글 다는 글 번호
	private String customerNickname; //댓글 작성자
	private int customerNo; //작성자 고유 번호
	private String comments; //내용
	private Date postDate; //작성일

}
