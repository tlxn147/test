package kr.co.boardReply.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItBoardReply_Dto {
	
	private int commentsNo; //��� ��ȣ
	private int postNo; //��� �ٴ� �� ��ȣ
	private String customerNickname; //��� �ۼ���
	private int customerNo; //�ۼ��� ���� ��ȣ
	private String comments; //����
	private Date postDate; //�ۼ���

}