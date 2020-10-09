package kr.co.board.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class LanguageBoard_Dto {
	
	
	private int postNo; //글번호 
    private String postTitle; //문의글제목 
    private String customerNickname; //유저닉네임 
    private Date postDate; //작성날짜 
    private String contents; //글본문 
    private int commentsCount;  //총댓글수 
    private String postCategory;   //글분류 
    private int customerNo; //유저번호'

}
