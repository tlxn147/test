package kr.co.board.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ItBoard_Dto {
	
	private int postNo; //�۹�ȣ 
    private String postTitle; //���Ǳ����� 
    private String customerNickname; //�����г��� 
    private Date postDate; //�ۼ���¥ 
    private String contents; //�ۺ��� 
    private int commentsCount;  //�Ѵ�ۼ� 
    private String postCategory;   //�ۺз� 
    private int customerNo; //������ȣ' 

}